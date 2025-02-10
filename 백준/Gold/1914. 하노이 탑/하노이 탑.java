import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		int N = sc.nextInt();
		
		if(N <= 20) {
			sb.append((long)Math.pow(2, N)-1).append("\n");
			hanoi(N, 1, 2, 3);
			System.out.println(sb);
		}
		else {
			BigInteger two = new BigInteger("2");
			BigInteger result = two.pow(N).subtract(BigInteger.ONE);
			sb.append(result);
			System.out.println(sb);
		}
		
	}
	
	private static void hanoi(int n, int from, int temp, int to) {
		if(n == 0) return;
		hanoi(n-1, from, to, temp);
		sb.append(from).append(" ").append(to).append("\n");
		hanoi(n-1, temp, from, to);
	}

}
