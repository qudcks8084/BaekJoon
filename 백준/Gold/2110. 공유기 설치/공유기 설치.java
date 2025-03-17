import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int max;
	static int[] home;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		home = new int[N];
		
		for(int i = 0 ; i < N ; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		
		// 정렬
		Arrays.sort(home);
		
		max = 0; 
		find(0, 1_000_000_000);
		System.out.println(max);
	}
	
	public static void find(int start, int end) {
		if(start > end) return;
		
		int mid = start + (end - start) / 2;
		
		int count = 1;
		int lastPosition = home[0];
		
		for(int i = 1; i < N; i++) {
			int distance = home[i] - lastPosition;
			if(distance >= mid) {
				count++;
				lastPosition = home[i];
			}
		}
		
		if(count >= M) {
			// 유효한 거리를 찾았지만, 더 큰 값이 있을 수 있음
			max = mid;
			find(mid + 1, end);
		} else {
			// 거리가 너무 커서 공유기 개수가 부족함
			find(start, mid - 1);
		}
	}

}
