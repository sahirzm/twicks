package in.ac.vit.twicks.dataanalysis.naivebayes;

import in.ac.vit.twicks.search.statuses.Status;
import in.ac.vit.twicks.utils.Constants;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.mahout.classifier.naivebayes.BayesUtils;
import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.common.Pair;
import org.apache.mahout.common.iterator.sequencefile.SequenceFileIterable;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;
import org.apache.mahout.vectorizer.DefaultAnalyzer;
import org.apache.mahout.vectorizer.TFIDF;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;

@Singleton
public class NaiveBayesClassifier implements Classifier {

	@Inject
	private Constants constants;
	private transient Logger log = Logger.getLogger(this.getClass());
	private Map<String, Integer> dictionary;
	private int labelCount;
	private int documentCount;
	private Map<Integer, Long> documentFrequency;
	private StandardNaiveBayesClassifier classifier;
	private Map<Integer, String> labels;

	public NaiveBayesClassifier() {
	}

	public static Map<String, Integer> readDictionnary(Configuration conf,
			Path dictionnaryPath) {
		Map<String, Integer> dictionnary = new HashMap<>();
		for (Pair<Text, IntWritable> pair : new SequenceFileIterable<Text, IntWritable>(
				dictionnaryPath, true, conf)) {
			dictionnary.put(pair.getFirst().toString(), pair.getSecond().get());
		}
		return dictionnary;
	}

	public static Map<Integer, Long> readDocumentFrequency(Configuration conf,
			Path documentFrequencyPath) {
		Map<Integer, Long> documentFrequency = new HashMap<>();
		for (Pair<IntWritable, LongWritable> pair : new SequenceFileIterable<IntWritable, LongWritable>(
				documentFrequencyPath, true, conf)) {
			documentFrequency
					.put(pair.getFirst().get(), pair.getSecond().get());
		}
		return documentFrequency;
	}

	@Override
	public List<Status> classify(List<Status> statuses) {

		if (this.classifier == null) {
			this.constructClassifier();
		}

		for (Status status : statuses) {
			try {
				try (Analyzer analyzer = new DefaultAnalyzer()) {
					Multiset<String> words = ConcurrentHashMultiset.create();
					// extract words from tweet
					TokenStream ts = analyzer.reusableTokenStream("text",
							new StringReader(status.getText()));
					CharTermAttribute termAtt = ts
							.addAttribute(CharTermAttribute.class);
					ts.reset();
					while (ts.incrementToken()) {
						if (termAtt.length() > 0) {
							String s = ts.getAttribute(CharTermAttribute.class)
									.toString();
							words.add(s);
						}
					}

					// create vector wordId => weight using tfidf
					Vector vector = new RandomAccessSparseVector(10000);
					TFIDF tfidf = new TFIDF();
					for (Multiset.Entry<String> entry : words.entrySet()) {
						String word = entry.getElement();
						int count = entry.getCount();
						Integer wordId = this.dictionary.get(word);
						// if the word is not in the dictionary, skip it
						if (wordId != null) {
							Long freq = this.documentFrequency.get(wordId);
							vector.setQuick(wordId, tfidf.calculate(count,
									freq.intValue(), this.labelCount,
									this.documentCount));
						}
					}
					// With the classifier, we get one score for each label
					// The label with the highest score is the one the tweet is
					// more
					// likely to
					// be associated to
					Vector resultVector = this.classifier.classifyFull(vector);
					double bestScore = -Double.MAX_VALUE;
					int bestCategoryId = -1;
					for (Element element : resultVector) {
						int categoryId = element.index();
						double score = element.get();
						if (score > bestScore) {
							bestScore = score;
							bestCategoryId = categoryId;
						}

					}
					status.setScore(Integer.parseInt(this.labels
							.get(bestCategoryId)));
				}
			} catch (IOException e) {
			}
		}

		return statuses;
	}

	private synchronized void constructClassifier() {
		if (this.classifier != null) {
			this.log.info("classifier already constructed...");
			return;
		}
		this.log.info("Constructing classifier....");

		String modelPath = this.constants.getPath()
				+ "WEB-INF/classes/output/model";
		String labelIndexPath = this.constants.getPath()
				+ "WEB-INF/classes/output/labelindex";
		String dictionaryPath = this.constants.getPath()
				+ "WEB-INF/classes/output/dictionary.file-0";
		String documentFrequencyPath = this.constants.getPath()
				+ "WEB-INF/classes/output/df-count";

		Configuration configuration = new Configuration();
		try {
			// model is a matrix (wordId, labelId) => probability score
			NaiveBayesModel model = NaiveBayesModel.materialize(new Path(
					modelPath), configuration);

			this.classifier = new StandardNaiveBayesClassifier(model);

			// labels is a map label => classId
			this.labels = BayesUtils.readLabelIndex(configuration, new Path(
					labelIndexPath));
			this.dictionary = readDictionnary(configuration, new Path(
					dictionaryPath));
			this.documentFrequency = readDocumentFrequency(configuration,
					new Path(documentFrequencyPath));

			this.labelCount = this.labels.size();
			this.documentCount = this.documentFrequency.get(-1).intValue();
		} catch (IOException e) {
			this.log.error(e.getMessage());
		}
	}
}