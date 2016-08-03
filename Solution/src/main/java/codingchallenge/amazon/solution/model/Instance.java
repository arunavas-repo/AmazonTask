package codingchallenge.amazon.solution.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instance {

	private Map<String, List<Host>> instances = new HashMap<String, List<Host>>();
	
	@Override
	public String toString() {
		return String.format("Instances: %s", instances);
	}
	
	public void addInstance(String instance, List<Host> hosts) {
		instances.put(instance, hosts);
	}
	
	public void addInstance(String instance, Host host) {
		if (instances.containsKey(instance))
			instances.get(instance).add(host);
		else {
			List<Host> list = new ArrayList<Host>();
			list.add(host);
			this.addInstance(instance, list);
		}
	}
	
	public List<Host> getHosts(String instance) {
		return instances.get(instance);
	}
	
	public Map<String, List<Host>> getInstances() {
		return instances;
	}
	
}
