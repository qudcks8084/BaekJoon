import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] stack = new int[26];
		
		char[] alpha = br.readLine().toCharArray();
		for(int i = 0 ; i < alpha.length ; i++) {
			stack[(alpha[i]-'a')]++;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int cnt : stack) {
			sb.append(cnt).append(" ");
		}
		System.out.println(sb);
	}

}
