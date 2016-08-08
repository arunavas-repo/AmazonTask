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
	public void incrementEmptyHostsCountByOne() {
		this.emptyHostsCount++;
	}
	public int getFilledHostsCount() {
		return filledHostsCount;
	}
	public void incrementFilledHostsCountByOne() {
		this.filledHostsCount++;
	}
	public int getMostFilledHostsCount() {
		return mostFilledHostsCount;
	}
	public void incrementMostFilledHostsCountByOne() {
		this.mostFilledHostsCount++;
	}
	public long getEmptySlots() {
		return emptySlots;
	}
	/*public void setEmptySlots(final long emptySlots) {
		this.emptySlots = emptySlots;
	}*/
	public void resetMostFilledHostsCount(final long emptySlots) {
		this.mostFilledHostsCount = 1;
		this.emptySlots = emptySlots;
	}
	
}
