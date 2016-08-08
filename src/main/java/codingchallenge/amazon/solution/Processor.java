package codingchallenge.amazon.solution;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import codingchallenge.amazon.solution.model.Host;
import codingchallenge.amazon.solution.model.Result;

public class Processor implements Callable<Result> {

	private CountDownLatch latch = null;
	private String instanceType = null;
	private List<Host> hosts = null;
	
	public Processor(CountDownLatch latch, String instanceType, List<Host> hosts) {
		this.latch = latch;
		this.instanceType = instanceType;
		this.hosts = hosts;
	}

	@Override
	public Result call() throws Exception {
		System.out.println("Started Processing Instance: " + instanceType);
		final Result result = new Result();
		result.setInstanceType(instanceType);
		
		hosts.stream().parallel().forEach(host -> {
			/* getting the number of zeros and number of ones in the list.
			*  If number of zero is same as the size of total slots then the host is empty.
			*  If number of ones is same as the size of total slots then the host is full.
			*  If number of zero is same as existing empty slots then one more most filled hosts
			*  If number of zero is less than existing empty slots or existing empty slots count is 0 (i.e. first time entry)
			*     then new most filled host - considering the new empty slot count as min empty slot count and mostfilled host count as 1
			*/
			long zeros = host.getSlots().stream().parallel().filter(x -> x == 0).count();
			long ones = host.getSlots().stream().parallel().filter(x -> x == 1).count();
			
			if (zeros == host.getSlots().size()) result.incrementEmptyHostsCountByOne();
			else if (ones == host.getSlots().size()) result.incrementFilledHostsCountByOne();
			else {
				synchronized (result) {
					if (zeros == result.getEmptySlots()) result.incrementMostFilledHostsCountByOne();
					else if (result.getEmptySlots() == 0 || zeros < result.getEmptySlots()) result.resetMostFilledHostsCount(zeros);
				}
			}
		});
		latch.countDown();
		System.out.println("Completed processing Instance Type: " + instanceType);
		return result;
	}
	
}
