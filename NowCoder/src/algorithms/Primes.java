package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Primes {

	public static List<Integer> getPrimes(int n) {

		if (n < 2) {
			throw new IllegalArgumentException("pls input a number greater than 2");
		}

		int firstNum = 2;
		int[] tempArr = new int[n - firstNum + 1];

		for (int i = 0; i < tempArr.length; i++) {
			tempArr[i] = i + firstNum;
		}
		
		for (int i = 0; i < Math.floor(Math.sqrt(n)); i++) {
			int curr = tempArr[i];
			if (curr != 0) {
				int minCurrBeginner = curr * curr;
				while (minCurrBeginner <= n) {
					tempArr[minCurrBeginner - firstNum] = 0;
					minCurrBeginner = minCurrBeginner + curr;
				}
			}
		}

		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < tempArr.length; i++) {
			int num = tempArr[i];
			if (num != 0) {
				result.add(num);
			}
		}

		return result;
	}

	public static void main(String[] args) {
		List<Integer> a = getPrimes(1000);
		System.out.println(a.size());
	}

}
