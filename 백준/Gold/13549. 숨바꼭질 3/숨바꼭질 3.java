import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println("0");
		}else {
			boolean[] visited = new boolean[100001];
			ArrayDeque<Integer> q = new ArrayDeque<>();
			visited[N] = true;
			q.offer(N);
			
			int totalTime = 0;
			while(!q.isEmpty()) {
				int len = q.size();
				for(int i = 0 ; i < len ; i++) {
					int cur = q.poll();
					if(cur == K) {
						System.out.println(totalTime);
						return;
					}
					
					// *2부터 검사
					int next = cur * 2;
					while(true) {
						// 범위값을 넘어가는지 검사
						if(next == 0 || next > 100000) break;
						// 정답인지를 확인
						if(next == K) {
							System.out.println(totalTime);
							return;
						}
						// 이동한 구역의 -1, +1을 확인하고 큐에 추가하기
						int[] nexts = new int[] {next -1, next+1};
						for(int num : nexts) {
							if(num < 0 || num > 100000) continue;
							if(!visited[num]) {
								visited[num] = true;
								q.offer(num);
							}
						}
						next *= 2;
					}
					
					// 원래 위치의 -1, +1을 확인하고 큐에 추가하기
					int[] cur_nexts = new int[] {cur -1, cur +1};
					for(int cur_num : cur_nexts) {
						if(cur_num < 0 || cur_num > 100000) continue;
						if(!visited[cur_num]) {
							visited[cur_num] = true;
							q.offer(cur_num);
						}
					}
				}
				totalTime++;
			}
		}
	}
}
