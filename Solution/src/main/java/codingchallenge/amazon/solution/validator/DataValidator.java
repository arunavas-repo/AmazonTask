package codingchallenge.amazon.solution.validator;

import java.util.List;

public class DataValidator {

	public boolean validate(String entry, List<String> instanceTypes) {
		
		String[] elementArr = entry.split(",");
		// Checking if the entry has atleast HostId, InstanceType and TotalSlotsCount
		if (elementArr.length < 4) {
			System.out.println("Invalid Entry: \"" + entry + "\" - Entry should be in [HostId,InstanceType,TotalSlotsCount, <atleast one slot>] format - Skipping ...");
			return false;
		}
		//Checking if the entry has a valid InstanceType
		if(!instanceTypes.contains(elementArr[1])) {
			System.out.println("Invalid Entry: \"" + entry + "\" - InstanceType must be among " + instanceTypes + " - Skipping ...");
		}
		//Checking if the TotalSlotsCount is a Number
		int totalSlots = 0;
		try {
			totalSlots = Integer.parseInt(elementArr[2]);
		}
		catch (Exception ex) {
			System.out.println("Invalid Entry: \"" + entry + "\" - TotalSlotsCount must be a number (Should not have decimal points) - Skipping ...");
			return false;
		}
		if (totalSlots < 1) {
			System.out.println("Invalid Entry: \"" + entry + "\" - TotalSlotsCount must be a non zero postive number - Skipping ...");
			return false;
		}
		//Checking if the entry has the same number of slots as TotalSlotsCount
		if (elementArr.length != (3 + totalSlots)) {
			System.out.println("Invalid Entry: \"" + entry + "\" - Entry should be in [HostId,InstanceType,TotalSlotsCount,slot_1,slot_2...slot_N] format where N is total number of slots - Skipping ...");
			return false;
		}
		//Checking if the slots are 0 or 1
		for (int i = 3; i < elementArr.length; i++) {
			if (!elementArr[i].equals("0") && !elementArr[i].equals("1")) {
				System.out.println("Invalid Entry: \"" + entry + "\" - slots must be either 0 or 1 - Skipping ...");
				return false;
			}
		}
		
		return true;
	}
	
}
