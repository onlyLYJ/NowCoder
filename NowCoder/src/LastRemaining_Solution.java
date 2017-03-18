//每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,从他的下一个小朋友开始,继续0...m-1报数....这样下去....直到剩下最后一个小朋友,可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
import static util.Print.*;
import java.util.Arrays;

public class LastRemaining_Solution {

	
	public int LastRemaining_Solution(int n, int m) {
		
		boolean children[] = new boolean[n*m];
		//println(Arrays.toString(children));
		
		for (int i = 0; i < children.length; i++) {
			int j = m-1;
			for(j = m-1; j < children.length; j = j + m-1) {
				children[j] = true;
			}
		}
		
		for (int i = children.length -1 ; i > 0; i--) {
			if (children[i] == true)
				return i - m * (n-1) - 1;
			
		}
		return 0;

    }
	
	public int LastRemaining_Solution1(int n, int m) {
		
        if(n == 0)  {
            return -1;  
        } else if( n == 1) {
            return 0;  
        }  else {
            return (LastRemaining_Solution1(n-1,m)+m)%n;  
        }
    }    

	public static void main(String[] args) {
		LastRemaining_Solution c = new LastRemaining_Solution();
		println(c.LastRemaining_Solution(5,2));
		println(c.LastRemaining_Solution1(5,2));
	}

}
