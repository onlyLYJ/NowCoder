package algorithms;

import java.util.Arrays;

public class KMP {

	static void findS_in_T(String t, String s) {

		int slen = s.length();
		int tlen = t.length();
		int currPos = 0;
		int machted = 0;
		int unmatched = slen;

		while (machted < slen && (currPos + unmatched) <= tlen) {

			machted = (t.charAt(currPos) == s.charAt(machted)) ? machted + 1 : 0;
			unmatched = slen - machted;
			currPos++;

		}

		if (machted == slen) {
			printMatch(t, s, currPos);
		} else {
			System.out.println("No S in T.");
		}

	}

	static int[] get_next(String a) {
		char[] s = stringToCharArr(a);
		int i = 0, j = -1;
		int ls = s.length;
		int[] next = new int[ls];
		next[0] = -1;

		while (i < ls-1) {
			
			if (j == -1 || s[i] == s[j]) {
				j++;
				i++;
				if (s[i] == s[j]) {
					next[i] = next[j];
				} else {
					next[i] = j;
				}
				
			} else {
				j = next[j];
			}

		}
		System.out.println("next array is " + Arrays.toString(next));
		return next;

	}

	static void kmp(int[] next, String b, String a) {

		char[] s = stringToCharArr(a);
		char[] t = stringToCharArr(b);
		int i = 0, j = 0;
		int ls = s.length;
		int lt = t.length;
		while (i < lt && j < ls) {
			if (j == -1 || t[i] == s[j]) {
				i++;
				j++;
			} else {
				j = next[j];
			}
		}

		if (j >= ls) {
			printMatch(b, a, i);
		} else {
			System.out.println("no s in t.");
		}

	}

	public static char[] stringToCharArr(String a) {

		char[] s = new char[a.length()];
		for (int i = 0; i < a.length(); i++) {
			s[i] = a.charAt(i);
		}

		return s;
	}

	public static void main(String[] args) {
		String a = "BABBC";
		String b = "BABBCBBABBCACD";

		System.out.println("KMP");
		int[] next = get_next(a);
		kmp(next, b, a);

		System.out.println("\r\nfind_S_in_T");
		findS_in_T(b, a);

	}

	private static void printMatch(String t, String s,int currPos) {
		int slen = s.length();
		int tlen = t.length();
		int beginIndex = currPos - slen;
		StringBuilder sb = new StringBuilder();
		sb.append("s is " + s + "\r\nt is " + t + "\r\n");
		sb.append("begin index is " + beginIndex + "\r\n");
		for (int i = 0; i < tlen; i++) {

			if (i == beginIndex) {
				sb.append("{s=");
			}

			sb.append(t.charAt(i));

			if (i == currPos - 1) {
				sb.append("}");
			}

		}
		System.out.println(sb.toString());
	}

}
