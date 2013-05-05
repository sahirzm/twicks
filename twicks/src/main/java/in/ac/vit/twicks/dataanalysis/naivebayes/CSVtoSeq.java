package in.ac.vit.twicks.dataanalysis.naivebayes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.SequenceFile.Writer;
import org.apache.hadoop.io.Text;

public class CSVtoSeq {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err
					.println("Arguments: [input tsv file] [output sequence file]");
			return;
		}
		File base = new File(args[0]);
		String outputDirName = args[1];
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);

		List<File> files = new ArrayList<File>();
		for (File tweet : base.listFiles()) {
			files.add(tweet);
		}

		int count = 0;
		for (File inputFileName : files) {
			Writer writer = new SequenceFile.Writer(fs, configuration,
					new Path(outputDirName + "/chunk-"+count), Text.class,
					Text.class);

			BufferedReader reader = new BufferedReader(new FileReader(
					inputFileName));
			Text key = new Text();
			Text value = new Text();
			int lineCount = 0;
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				String[] tokens = line.split(",", 6);
				if (tokens.length != 6) {
					System.out.println("Skipping tweet : " + tokens[2]);
					continue;
				}
				String category = tokens[0];
				String id = tokens[1];
				String message = tokens[5];
				key.set("/" + category + "/" + id);
				value.set(message);
				writer.append(key, value);
				lineCount++;
			}
			writer.close();
			reader.close();
			count++;
			System.out.println("Wrote "+lineCount + " entries to chunk-" + count);
		}
		System.out.println("Wrote " + count + " Files.");

	}
}
