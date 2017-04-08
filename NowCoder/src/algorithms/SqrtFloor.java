package algorithms;

public class SqrtFloor {

	
	
	public static int sqrtFloor(int n) {
		
		if (n < 0) {
			throw new IllegalArgumentException("pls input a number greater than 1");
		}
		
		if (n == 0 || n == 1) {
			return n;
		}
		
		for (int i = 0; i < n; i++) {
			if (i * i > n) 
				return i - 1;
		}
		
		return 1;

	}
	
	public static void main(String[] args) {
		int a = sqrtFloor(0);
		System.out.println(a);
	}

}
