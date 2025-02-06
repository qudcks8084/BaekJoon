
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        if(N == 1) N++;
        int M = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for(int i = N ; i <= M ; i++) {
        	boolean flag = true;
        	for(int j = 2 ; j <= Math.sqrt(M) ; j++) {
        		if(i != j && i % j == 0) {
        			flag = false;
        			break;
        		} 
        	}
        	if(flag) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
