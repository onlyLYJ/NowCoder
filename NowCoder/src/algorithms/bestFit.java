package algorithms;

import java.util.Arrays;

public class bestFit {

	public static void load(int box, int[] cargos) {
		
		int cargoNum = cargos.length;
		int[] loaded = new int[cargoNum];
		for (int i = 0; i < cargoNum; i++) {
			loaded[i] = 0;
		}
		
		int used = 1;
		for (int i = 0; i < cargoNum; i++) {
			int cargoWeight = cargos[i];
		
			int minLeft = box;
			int minIndex = 0;
			for(int j = 0; j <= used; j++) {
				int left = box - loaded[j] - cargoWeight;
				if (left >= 0 && left < minLeft) {
					minLeft = left;
					minIndex = j;
				}
			}
			
			loaded[minIndex] = loaded[minIndex] + cargoWeight;
			System.out.println("cargo " + i + " weigth " + cargoWeight + 
					" has been loaded to box " + minIndex);
			used = used > (minIndex + 1) ? used : minIndex + 1;
		}

		System.out.println(Arrays.toString(loaded));
	}
	
	
	public static void main(String[] args) {
		int box = 10;
		int[] cargos = {4,2,7,3,5,4,2,3,6,2};
		load(box,cargos);
	}

}
