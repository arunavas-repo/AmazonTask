package codingchallenge.amazon.solution.model;

import java.util.ArrayList;
import java.util.List;

public class Host {

	private String hostId;
	private List<Integer> slots = new ArrayList<Integer>();
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("HostId: %s | Slots -> %s", hostId, slots);
	}
	
	public void addSlot(Integer slot) {
		slots.add(slot);
	}
	
	public void addSlots(List<Integer> slots) {
		slots.addAll(slots);
	}
	
	public String getHostId() {
		return hostId;
	}
	
	public void setHost(String hostId) {
		this.hostId = hostId;
	}
	
	public List<Integer> getSlots() {
		return slots;
	}
	
}
