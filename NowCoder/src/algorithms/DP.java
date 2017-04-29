package algorithms;

import java.util.ArrayList;
import java.util.List;

public class DP {
	
//	serling公司钢条价格表
//	长度i	1	2	3	4	5	6	7	8	9	10
//	价格p(i)	1 	5 	8 	9 	10	17	17	20	24	30
	
	static int[] priceTag = {1,5,8,9,10,17,17,20,24,30};
	
	public static List<Integer> findHighestPrice(int[] priceTag, int len) {
		
		int cutCount = priceTag.length;
		int[] high = new int[cutCount];
		List<Integer> result = new ArrayList<>();
		
		for (int i = 0; i < cutCount; i++) {

			int highPrice = priceTag[i];

			for (int cut1 = 0; cut1 <= i/2; cut1++) {
				int cut2 = i - (cut1 + 1);
				if (cut2 >= cut1) {
					int tempP = high[cut1] + high[cut2];
					if (tempP > highPrice) {
						highPrice = tempP;
					}
				}
			}
		
			high[i] = highPrice;
			result.add(high[i]);		
		}
		
		int baseHigh = high[cutCount-1];
		for (int i = cutCount; i < len; i++) {
			int p = i % cutCount;
			int times = i / cutCount;
			result.add(times * baseHigh + high[p]);
		}
		
		System.out.println("if the steal is " + len + " meters, the highest price is " + result.get(result.size()-1));
		return result;
	}
	
	
	public static void main(String[] args) {
		System.out.println(findHighestPrice(priceTag, 21));
	}

}
