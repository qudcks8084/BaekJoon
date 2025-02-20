import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] first = new int[26];
		
		for(int i = 0 ; i < N ; i++) {
			first[br.readLine().charAt(0) - 'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for(int i = 0 ; i < 26 ; i++) {
			if(first[i] > 4) {
				flag = true;
				sb.append((char)(i + 'a'));
			}
		}
		if(!flag) System.out.println("PREDAJA");
		else System.out.println(sb);
	}
}
