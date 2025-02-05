import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Doc{
		int docNum;
		int priority;
		
		public Doc(int docNum, int priority) {
			this.docNum = docNum;
			this.priority = priority;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase = 0 ; testcase < T ; testcase++) {
			
			int[] cnt = new int[10];
			int max_priority = 0;
			StringTokenizer st= new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			Queue<Doc> q = new ArrayDeque<>();
			
			StringTokenizer input = new StringTokenizer(br.readLine());
			for(int i = 0 ; i < N ; i++) {
				int priority = Integer.parseInt(input.nextToken());
				cnt[priority]++;
				max_priority = Math.max(max_priority, priority);
				q.offer(new Doc(i, priority));
			}
			
			int num_of_print = 1;
			
			while(true){
				Doc now = q.poll();
				if(now.docNum == target && now.priority == max_priority) {
					break;
				}
				if(now.priority == max_priority) { // 출력 조건 - 가장 높은 우선순위
					num_of_print++;
					cnt[max_priority]--;
					
					if(cnt[max_priority] == 0) { // 다음 높은 우선순위 설정
						for(int i = max_priority ; i > 0 ; i--) {
							if(cnt[i] > 0) {
								max_priority = i;
								break;
							}
						}
					}
				} else {
					q.offer(now);
				}
			}
			
			sb.append(num_of_print).append("\n");
		}
		System.out.println(sb);
	}

}
