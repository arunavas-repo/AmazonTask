package codingchallenge.amazon.solution.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public List<String> readFromFile(String fileName) throws FileNotFoundException, IOException {
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			for (String line; (line = br.readLine()) != null; ) {
				list.add(line);
			}
		}
		
		return list;
	}
	
	public void writeToFile(String fileName, String...data) throws FileNotFoundException {
		try (PrintWriter pw = new PrintWriter(fileName)) {
			for (String str : data) {
				pw.println(str);
			}
		}
	}
	
}
