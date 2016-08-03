package codingchallenge.amazon.solution;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import codingchallenge.amazon.solution.loader.DataLoader;
import codingchallenge.amazon.solution.loader.impl.FileDataLoder;
import codingchallenge.amazon.solution.model.Host;
import codingchallenge.amazon.solution.model.Instance;
import codingchallenge.amazon.solution.model.Result;
import codingchallenge.amazon.solution.util.FileUtil;

public class Solution {

	public void solve(String instanceTypeFile, String hostDataFile) {
		
		long startTime = System.currentTimeMillis();
		
		Instance instances = loadData(instanceTypeFile, hostDataFile);
		if (instances == null)
			return;
		
		long dataLoadingTime = System.currentTimeMillis() - startTime;
		long processingStartTime = System.currentTimeMillis();
		
		//Spawning threads - one instance per thread in the pool. if number of instances is greater than 10 then limiting the thread size to 10 in the pool
		CountDownLatch latch = new CountDownLatch(instances.getInstances().size());
		int POOL_SIZE = instances.getInstances().size() > 10 ? 10 : instances.getInstances().size();
		
		ExecutorService executorService = Executors.newFixedThreadPool(POOL_SIZE);
		List<Future<Result>> results = new ArrayList<Future<Result>>();
		
		Iterator<Entry<String, List<Host>>> iter = instances.getInstances().entrySet().iterator();
		Entry<String, List<Host>> entry = null;
		while (iter.hasNext()) {
			entry = iter.next();
			Future<Result> result = executorService.submit(new Processor(latch, entry.getKey(), entry.getValue()));
			results.add(result);
		}
		
		//Waiting for the results, once completed merging the results and writing to the file.
		processResult(latch, executorService, results, new File(hostDataFile).getParent() + "/Statistics.txt");

		long endTime = System.currentTimeMillis();
		long processingTime = endTime - processingStartTime;
		long totalTime = endTime - startTime;
		System.out.println(String.format("\nProcess Completed :)\n\\\n Time Taken to Load Data:\t%s.%s Seconds."
										+ "\n Time Taken to Process Data:\t%s.%s Seconds."
										+ "\n Total Time Taken:\t\t%s.%s Seconds."
										, (dataLoadingTime / 1000), (dataLoadingTime % 1000)
										, (processingTime / 1000), (processingTime % 1000)
										, (totalTime / 1000), (totalTime % 1000)));
		
	}
	
	private Instance loadData(String instanceTypeFile, String hostDataFile) {
		File file = new File(instanceTypeFile);
		if (!file.exists()) {
			System.out.println("Invalid Instance Type File Path. File - \"" + file.getPath() + "\" does not exits!");
			return null;
		}
		file = new File(hostDataFile);
		if (!file.exists()) {
			System.out.println("Invalid Host Details File Path. File - \"" + file.getPath() + "\" does not exits!");
			return null;
		}
		
		//Loading Data
		Instance instances = null;
		try {
			System.out.println("Loading Host Details & Instance Types");
			DataLoader dataLoader = new FileDataLoder();
			instances = dataLoader.loadData(hostDataFile, instanceTypeFile);
			System.out.println("Loaded Host Details & Instance Types");
		} catch (Exception e) {
			System.out.println("Could not load host details. Error: " + e.getMessage());
			return null;
		}
		
		return instances;
	}
	
	private void processResult(CountDownLatch latch, ExecutorService executorService, List<Future<Result>> results, String resultFileLocation) {
		try {
			latch.await();
			executorService.shutdown();
			
			StringBuilder empty = new StringBuilder();
			StringBuilder filled = new StringBuilder();
			StringBuilder mostFilled = new StringBuilder();
			
			for (Future<Result> future : results) {
				Result result = future.get();
				empty.append(" " + result.getInstanceType() + "=" + (result.getEmptyHostsCount() > 0 ? result.getEmptyHostsCount() : 0) + ";");
				filled.append(" " + result.getInstanceType() + "=" + (result.getFilledHostsCount() > 0 ? result.getFilledHostsCount() : 0) + ";");
				if (result.getMostFilledHostsCount() > 0)
					mostFilled.append(" " + result.getInstanceType() + "=" + result.getMostFilledHostsCount() + "," + result.getEmptySlots() + ";");
				else
					mostFilled.append(" " + result.getInstanceType() + "=0,0;");
			}
			
			System.out.println("Writing to file: " + resultFileLocation + "/Statistics.txt");
			new FileUtil().writeToFile(resultFileLocation, "EMPTY:" + empty.toString(), "FULL:" + filled.toString(), "MOST FILLED:" + mostFilled.toString());
			
		} catch (InterruptedException | ExecutionException | FileNotFoundException e) {
			System.err.println("An unexpected error occured: " + e.getMessage());
		}
	}
	
}
