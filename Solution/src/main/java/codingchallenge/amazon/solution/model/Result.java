package codingchallenge.amazon.solution.model;

public class Result {

	private String instanceType;
	private int emptyHostsCount;
	private int filledHostsCount;
	private int mostFilledHostsCount;
	private long emptySlots;
	
	@Override
	public String toString() {
		return "Result [instanceType=" + instanceType + ", emptyHostsCount="
				+ emptyHostsCount + ", filledHostsCount=" + filledHostsCount
				+ ", mostFilledHostsCount=" + mostFilledHostsCount
				+ ", emptySlots=" + emptySlots + "]";
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public int getEmptyHostsCount() {
		return emptyHostsCount;
	}
	public void setEmptyHostsCount(int emptyHostsCount) {
		this.emptyHostsCount = emptyHostsCount;
	}
	public int getFilledHostsCount() {
		return filledHostsCount;
	}
	public void setFilledHostsCount(int filledHostsCount) {
		this.filledHostsCount = filledHostsCount;
	}
	public int getMostFilledHostsCount() {
		return mostFilledHostsCount;
	}
	public void setMostFilledHostsCount(int mostFilledHostsCount) {
		this.mostFilledHostsCount = mostFilledHostsCount;
	}
	public long getEmptySlots() {
		return emptySlots;
	}
	public void setEmptySlots(long emptySlots) {
		this.emptySlots = emptySlots;
	}
	
}
