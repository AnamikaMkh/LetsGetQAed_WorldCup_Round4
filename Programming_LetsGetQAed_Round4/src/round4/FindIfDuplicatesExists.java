package round4;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FindIfDuplicatesExists {

	public static boolean isArrayContainingDupes(String[] arr) {

		// Assumption - if arr.length is 0 we return it does nto have any dupes
		if (arr.length == 0) {
			return false;
		}
		
		if(arr.length<=1 || arr.length>=105) {
			System.out.println("Length of array does not fit the limit");
			return false;
				}
				
		else {
		Set<String> uniqueSet = new HashSet();
		for (String num : arr) {
			uniqueSet.add(num);
		}

		if (uniqueSet.size() == arr.length) {
			return false;
		} else {
			return true;
		}
	
	}
	}
	
	public static void main(String args[]) {
		
		
		System.out.println("Please enter the array of integer numbers between -109 and 109 separated by comma");
		System.out.println("");
		Scanner myObj = new Scanner(System.in);
		String array = myObj.nextLine();
		String[] nums = array.split(",");
		
		//int[] input = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24 };
		boolean result = isArrayContainingDupes(nums);
		System.out.println(String.format("Is the array having duplicate integers - %b", result));
	}
}