package algorithms;

import java.util.Arrays;

public class BackTrack {
	
	
	static int n = 3;
	static int m = 3;
	static int cc =4;
	static int w[][] = {{1,2,3},{3,2,1},{2,2,2}};
	static int c[][] = {{1,2,3},{3,2,1},{2,2,2}};
	
	static int bestW = 8;
	static int bestC = 0;
	static int bestX[] = new int[3];
	static int cw = 0;
	static int cp = 0;
	static int[] x = {0,0,0};

	
	static int backtrack(int i) {
		
		int j = 0;
		int found = 0;
		if (i > n-1) {
			bestW = cw;
			bestC = cp;
			for (j = 0; j < n; j++) {
				bestX[j] = x[j];
			}
			
			return 1;
			
		}
		
		if (cp <= cc) {
			found = 1;
		}
		
		for (j =0; j < m; j++) {
			x[i] = j;
			cw = cw + w[i][j];
			cp = cp + c[i][j];
			
			if(cp <= cc && cw < bestW) {
				if (backtrack(i+1) == 1) {
					found = 1;
				}
				
				cw = cw - w[i][j];
				cp = cp - c[i][j];
			}
		}
		return found;
	
	}
	

	public static void main(String[] args) {
		backtrack(1);
		System.out.println(Arrays.toString(bestX));
		System.out.println(bestW);
		System.out.println(bestC);
	}

}
