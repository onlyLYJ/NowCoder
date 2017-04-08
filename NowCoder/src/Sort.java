import java.util.Arrays;



public class Sort {

	/**
	 * 希尔排序算法
	 */
	public static void shellSort(int[] p) {
		int len = p.length;
		int step = len;
		while (step != 1) {
			System.out.println(Arrays.toString(p));	
			step = getNewStep(step);
			System.out.println("new step = " + step);	
			for (int i = 0; (i + step) < len; i++) {
				if (p[i] > p[i+step]) {
					swap(p,i,i+step);
				}
			}
		}
		System.out.println(Arrays.toString(p));		
	}
	
	private static void swap(int[] p, int a1, int a2) {
		int temp = p[a1];
		p[a1] = p[a2];
		p[a2] = temp;
	}

	private static int getNewStep(int step) {
		return (step % 2 == 0) ? step/2 : step/2 + 1; 
	}

	/**
	 * 简单排序算法
	 */
	public static void easySort(int[] p) {
		int len = p.length;
		int fixed = 0;
		while(fixed < len - 1) {
			int minPos = getMinPos(p,fixed);
			if (minPos != fixed) {
				swap(p,fixed,minPos);
			}
			fixed++;
		}
		System.out.println(Arrays.toString(p));		
	}
	
	
	private static int getMinPos(int[] p, int fixed) {
		int minPos = fixed;
		for (int i = fixed + 1; i < p.length; i++) {
			if (p[i] < p[minPos] ) {
				minPos = i;
			}
		}
		return minPos;
	}
	
	
	/**
	 * 冒泡排序算法
	 */
	public static void popSort(int[] p) {
		int len = p.length;
		int fixed = 0;
		while(fixed < len - 1) {
			for(int i = len-1; (i - 1) >= fixed; i--) {
				if(p[i] < p[i-1]) {
					swap(p,i,i-1);
				}
			}
			fixed++;
		}
		System.out.println(Arrays.toString(p));		
	}
	
	/**
	 * 快速排序
	 */
	public static void quickSortFirstStep(int[] p, int begin, int end) {


		int lp = begin;
		int rp = end;
		int keyIndex = begin;
		if (begin > end)  {
			return;
		} else {
	
			int key = p[begin];
			if (keyIndex == lp) {
				if(key > p[rp]) {
					swap(p,keyIndex,rp);
					keyIndex = rp;
					lp++;
				} else {
					rp--;
				}
			}
			
			if (keyIndex == rp) {
				if(key < p[lp]) {
					swap(p,keyIndex,lp);
					keyIndex = lp;
					rp--;
				} else {
					lp++;
				}
			}
			System.out.println("key " + keyIndex + " lp " + lp + " rp " +rp);
	
		}

		quickSortFirstStep(p, lp, keyIndex-1);
		quickSortFirstStep(p, keyIndex + 1, rp);
		System.out.println(Arrays.toString(p));
	}
	

	public static void main(String[] args) {
		int[] p = {57,68,59,52,72,28,96,33,24,19};

		//shellSort(p);
		quickSortFirstStep(p,0,9);
		System.out.println(Arrays.toString(p));
		
	}

}
