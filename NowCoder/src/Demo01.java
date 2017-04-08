public class Demo01  {  
  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // TODO Auto-generated method stub  
        int[][] a={{1,2},{3,4},{5,6}};//自己定义矩阵  
        int[][] b={{1,2,3},{4,5,6}};//自己定义矩阵  
        printMatrix(a,b);  
    }  
    static void printMatrix(int[][] a,int[][] b){  
      
          
      
  
        int c[][] = new int[a.length][b[0].length];  
          
 
        for(int i = 0;i<a.length ;i++)  
        {  
            for(int j = 0;j<b[0].length;j++)  
            {  
                int temp = 0;  
                for(int x = 0;x<b.length;x++)  
                {  
                    temp+=a[i][x]*b[x][j];  
                      
                }  
                c[i][j] = temp;  
                  
            }  
        }  
        System.out.println("矩阵相乘后结果为");  
        for(int m = 0;m<a.length;m++)  
        {  
            for(int n = 0;n<b[0].length;n++)  
            {  
                System.out.print(c[m][n]+"\t");  
            }  
            System.out.println();  
        }  
    }  
      
      
}  