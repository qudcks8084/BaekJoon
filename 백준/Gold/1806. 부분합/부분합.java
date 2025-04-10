import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		int[] p = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		int lp = 0, rp = 1;
		
		int answer = Integer.MAX_VALUE / 10;
		int sum = p[lp];
		while(lp <= rp && rp <= N) {
			if(sum >= S) {
				answer = Math.min(answer, rp - lp);
				sum -= p[lp++];
			}else {
				if(rp == N) break;
				sum += p[rp++];
			}
		}
		if(answer == Integer.MAX_VALUE / 10) System.out.println(0);
		else System.out.println(answer);

	}
}
