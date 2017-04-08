import java.util.Arrays;

public class MatrixMutiply {
	
	
	
	public static void matrixMtiply(int[][] a, int[][] b) {
		int lenAR = a.length;
		int lenAC = a[0].length;
		
		int lenBR = b.length;
		int lenBC = b[0].length;
		
		int [][] matrix = getMatrix(lenAR, lenAC, lenBR, lenBC);
		if (null == matrix) {
			System.out.println("invalide input");
			return;
		}

		int x,i,j;  
		int count = 0;

        for(i = 0;i<a.length ;i++)  
        {  
            for(j = 0;j<b[0].length;j++)  
            {  
                int temp = 0;  
                for(x = 0;x<b.length;x++)  
                {  
                    temp+=a[i][x]*b[x][j];
                    count++;
                      
                }  
                matrix[i][j] = temp;  
                  
            }  
        }  
		
		
		

		
		System.out.println("count = " + count);
		for (int n = 0; n < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[n]));
		}


	}
	
	
	private static int[][] getMatrix(int lenAR, int lenAC, int lenBR, int lenBC) {

		if (lenAC == lenBR) {
			return new int[lenAR][lenBC];
		}

		return null;
	}
	

	public static void main(String[] args) {
		int[][] a = {
				{1,2,3},
				{2,3,4}
		};
		
		int[][] b = {
				{2,3},
				{4,5},
				{6,7}
		};
	
		
		matrixMtiply(a,b);
	}

}
