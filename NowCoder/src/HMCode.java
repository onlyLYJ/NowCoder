import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HMCode {

	public static void getHaiMingCode(String src) {

		int rLen = getRLen(src);
		String[] src1 = src.split("");
		List<String> src2 = new ArrayList<String>();
		for (String s : src1) {
			src2.add(s);
		}
		Collections.reverse(src2);

		int[][] chCode = new int[rLen][3];
		List<Integer> CheckNum = initialCheck(rLen, chCode);

		int[] result = new int[rLen + src.length() + 1];

		int count = 0;
		for (int i = 1; i < result.length; i++) {

			if (!CheckNum.contains(i)) {
				String biNum = Integer.toBinaryString(i);
				int srcNum = Integer.parseInt(src2.get(count++));

				result[i] = srcNum;
				updateCHCode(chCode, biNum, srcNum);

			}

		}

		for (int i = 0; i < chCode.length; i++) {
			int ck = chCode[i][2] % 2;
			result[chCode[i][0]] = ck;
		}

		// 以下均为输出
		for (int i = 0; i < chCode.length; i++) {
			System.out.println(Arrays.toString(chCode[i]));
		}

		String HaiMing = "";

		for (int i = 1; i < result.length; i++) {
			HaiMing += result[i];
		}

		System.out.println("Src: " + src + " HaiMing: " + HaiMing);

	}

	private static void updateCHCode(int[][] chCode, String biNum, int srcNum) {

		String[] bis = biNum.split("");

		for (int i = bis.length - 1, j = 0; i >= 0; i--, j++) {

			int num = Integer.parseInt(bis[i]);
			if (num != 0) {
				chCode[j][srcNum + 1]++;
			}

		}

	}

	private static List<Integer> initialCheck(int rLen, int[][] chCode) {

		List<Integer> CN = new ArrayList<Integer>();
		for (int i = 0; i < rLen; i++) {
			int ck = (int) Math.pow(2, i);
			CN.add(ck);
			chCode[i][0] = ck;
		}

		return CN;
	}

	private static int getRLen(String src) {
		int len = src.length();
		for (int i = 0; i < len; i++) {
			if (Math.pow(2, i) >= 1 + len + i) {
				return i;
			}

		}
		return 0;
	}

	public static void main(String[] args) {

		getHaiMingCode("1011");
		
	}

}
