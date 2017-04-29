package algorithms;

import java.util.Arrays;

public class DP {
	
//	serling公司钢条价格表
//	长度i	1	2	3	4	5	6	7	8	9	10
//	价格p(i)	1 	5 	8 	9 	10	17	17	20	24	30
//  求len长度的钢条，切割后可以获得的最高售价,动态规划求解
	
	static int[] priceTag = {1,5,8,9,10,17,17,20,24,30};
	
	public static int[] findHighestPrice(int[] priceTag, int len) {
		
		int cutCount = priceTag.length;
		int[] high = new int[len];

		for (int i = 0; i < cutCount; i++) {

			int highestPrice = priceTag[i];

			for (int cut1 = 0; cut1 <= i/2; cut1++) {
				int cut2 = i - (cut1 + 1);
				if (cut2 >= cut1) {
					int tempP = high[cut1] + high[cut2];
					if (tempP > highestPrice) {
						highestPrice = tempP;
					}
				}
			}
		
			high[i] = highestPrice;
		}

		int baseHigh = high[cutCount-1];
		for (int i = cutCount; i < len; i++) {
			int p = i % cutCount;
			int times = i / cutCount;
			high[i] = times * baseHigh + high[p];
		}

		System.out.println("if the steal is " + len + " meters, the highest price is " + high[len-1]);
		return high;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(findHighestPrice(priceTag, 121)));
	}

}
