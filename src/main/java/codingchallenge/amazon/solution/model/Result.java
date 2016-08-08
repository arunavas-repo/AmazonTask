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
	public synchronized void incrementEmptyHostsCountByOne() {
		this.emptyHostsCount++;
	}
	public int getFilledHostsCount() {
		return filledHostsCount;
	}
	public synchronized void incrementFilledHostsCountByOne() {
		this.filledHostsCount++;
	}
	public int getMostFilledHostsCount() {
		return mostFilledHostsCount;
	}
	public synchronized void incrementMostFilledHostsCountByOne() {
		this.mostFilledHostsCount++;
	}
	public long getEmptySlots() {
		return emptySlots;
	}
	/*public void setEmptySlots(final long emptySlots) {
		this.emptySlots = emptySlots;
	}*/
	public synchronized void resetMostFilledHostsCount(final long emptySlots) {
		this.mostFilledHostsCount = 1;
		this.emptySlots = emptySlots;
	}
	
}
