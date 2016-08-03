package codingchallenge.amazon.solution.loader.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import codingchallenge.amazon.solution.loader.DataLoader;
import codingchallenge.amazon.solution.model.Host;
import codingchallenge.amazon.solution.model.Instance;
import codingchallenge.amazon.solution.util.FileUtil;
import codingchallenge.amazon.solution.validator.DataValidator;

public class FileDataLoder implements DataLoader {

	public Instance loadData(String hostDetailsFile, String instanceTypesFile) throws FileNotFoundException, IOException {
		Instance instances = new Instance();
		FileUtil fileUtil = new FileUtil();
		DataValidator dataValidator = new DataValidator();
		
		//Loading Instance Types from the specified file.
		System.out.println("Loading Instance Types");
		List<String> instanceTypes = fileUtil.readFromFile(instanceTypesFile);
		System.out.println("Loaded Instance Types: " + instanceTypes);
		
		List<String> entries = fileUtil.readFromFile(hostDetailsFile);
		for (String entry : entries) {
			entry = entry.replace(" ", "");
			if (dataValidator.validate(entry, instanceTypes)) {
				String[] elements = entry.split(",");
				Host host = getHost(elements);
				instances.addInstance(elements[1], host);
			}
		}
		
		return instances;
	}
	
	private Host getHost(String[] elements) {
		Host host = new Host();
		
		host.setHost(elements[0]);
		for (int i = 3; i < elements.length; i++) {
			host.addSlot(Integer.parseInt(elements[i]));
		}
		
		return host;
	}

}
