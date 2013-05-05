package in.ac.vit.twicks.dataanalysis.naivebayes;

import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

public interface Classifier {

	List<Status> classify(List<Status> statuses);
}
