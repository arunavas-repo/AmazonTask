package codingchallenge.amazon.solution.loader;

import codingchallenge.amazon.solution.model.Instance;

public interface DataLoader {
	public Instance loadData(String hostDetailsFile, String instanceTypesFile) throws Exception;
}
