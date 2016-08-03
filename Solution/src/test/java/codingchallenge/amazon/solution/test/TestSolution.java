package codingchallenge.amazon.solution.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import codingchallenge.amazon.solution.Solution;
import codingchallenge.amazon.solution.loader.DataLoader;
import codingchallenge.amazon.solution.loader.impl.FileDataLoder;
import codingchallenge.amazon.solution.model.Instance;
import codingchallenge.amazon.solution.util.FileUtil;

public class TestSolution {

	@Test
	public void testDataLoader() {
		DataLoader dataLoader = new FileDataLoder();
		Instance instance = null;
		try {
			instance = dataLoader.loadData(getClass().getResource("/FleetState.txt").getFile(), getClass().getResource("/instances.txt").getFile());
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		assertNotNull(instance);
		assertEquals(instance.getInstances().size(), 3);
	}
	
	@Test
	public void testSolution() {
		Solution solution = new Solution();
		solution.solve(getClass().getResource("/instances.txt").getFile(), getClass().getResource("/FleetState.txt").getFile());
		List<String> result = null;
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("EMPTY: M1=2; M2=0; M3=0;");
		expectedResult.add("FULL: M1=1; M2=1; M3=0;");
		expectedResult.add("MOST FILLED: M1=5,1; M2=3,1; M3=2,3;");
		try {
			result = new FileUtil().readFromFile(getClass().getResource("/Statistics.txt").getFile());
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		assertNotEquals(result.size(), 0); //Test List is not empty
		assertEquals(result.size(), 3);
		assertEquals(result, expectedResult);
		
	}
	
}
