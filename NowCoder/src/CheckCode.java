import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CheckCode {

	public static void HaiMingCode(String src) {

		int k = src.length();
		/**
		 * r是输入数据所需要的校验码数量
		 */
		int r = getR(k);
		int CodeSize = k + r;
		String[] src1 = src.split("");
		String[] src2 = src.split("");
		Collections.reverse(Arrays.asList(src1));

		int[] result = new int[CodeSize];

		List<Integer> rList = new ArrayList<Integer>();
		int[][] HMCode = new int[r][3];
		/**
		 * 2维数组HMCode保存校验位，以及所有用到该校验位的有效数字0和1个数总和 比如[2,1,2] 表示校验码位置是2
		 * 用到该校验码的有效数字共有3个，其中有1个效数字是0,有2个有效数字是1。
		 */
		for (int i = 0; i < r; i++) {
			int HM = (int) Math.pow(2, (i));
			HMCode[i][0] = HM;
			rList.add(HM);
		}

		int count = 0;
		for (int i = 1; i <= CodeSize; i++) {
			if (!rList.contains(i)) {
				int kCode = Integer.parseInt(src1[count]);
				result[i - 1] = kCode;
				count++;

				String kHM = Integer.toBinaryString(i);
				int kHMLen = kHM.length();

				for (int j = kHMLen - 1; j >= 0; j--) {
					if (kHM.charAt(j) != '0') {
						HMCode[kHMLen - j - 1][kCode + 1]++;
					}
				}
			}
		}

		/**
		 * 如何计算1,2,4位的校验码值。 以[2,1,2]为例： 有一种讲法是计算1的数量，偶数取0，基数取1， 即位置2校验码的值= 0
		 * （偶数个1）；
		 * 
		 * 视频的讲法是将0和1每一个数字进行XOR计算 即位置2校验码的值= 0 ^ 1 ^ 1 = 0 (1个0,2个1）
		 * 
		 * 这2个算法应该算出来的值是一样的
		 */
		Iterator<Integer> it = rList.iterator();
		for (int i = 0; i < HMCode.length; i++) {
			int oneSize = HMCode[i][2];
			int HM = oneSize % 2;
			result[it.next() - 1] = HM;
		}

		// 输出
		for (int i = 0; i < HMCode.length; i++) {
			System.out.println(Arrays.toString(HMCode[i]));
		}

		String HaiMing = "";
		for (int i = 0; i < result.length; i++) {
			HaiMing += result[i];
		}

		System.out.println("SourceCode: " + src + "\r\nHaiMing: " + HaiMing);
	}

	private static int getR(int k) {
		for (int r = 0; r <= k; r++) {
			if (Math.pow(2, r) >= k + r + 1)
				return r;
		}
		return -1;
	}

	public static void main(String[] args) {
		HaiMingCode("1011"); // 1010101

		HaiMingCode("10011101");
		// 111000111101

	}

}
