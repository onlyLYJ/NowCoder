package algorithms;

public class gcd {


	static int check = -1;
	public static void getGcd(int a, int b) {

		if (check == -1) {
			check = Math.min(a, b);
			if (check <= 0) {
				throw new IllegalArgumentException("pls input a number greater than 0");
			}
		}
		
		if (b == 0) {
			System.out.println("gcd is " + a);
			return;
		}

		int r = a % b;
		a = r;
		getGcd(b,r);
		
	}
	
	public static void getGcd1(int a, int b) {
		
		if(a <= 0 || b <= 0) {
			throw new IllegalArgumentException("pls input a number greater than 0");
		}
	
		int r = a % b;
		while(r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		
		System.out.println("gcd1 is " + b);

	}
	
	
	public static void main(String[] args) {
		
		getGcd(31415, 14142);
		getGcd1(31415, 14142);
	}

}
