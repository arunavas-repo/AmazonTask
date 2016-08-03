package codingchallenge.amazon.solution.main;

import codingchallenge.amazon.solution.Solution;

public class Main {

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("Insufficient Data.\nUsage: Solution.jar <instance type file location> <host data file location>");
			System.out.println("<instance type file location>\t- file containing instance types");
			System.out.println("<host data file location>\t- file containing host details");
			return;
		}
		Solution solution = new Solution();
		solution.solve(args[0], args[1]);
	}
	
}
