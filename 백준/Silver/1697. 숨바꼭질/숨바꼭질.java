import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int max = 100000;
		boolean[] visited = new boolean[100001];
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		if(N == K) {
			System.out.println(0);
		}else {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.offer(N);
			visited[N] = true;
			int level = 1;
			while(!q.isEmpty()) {
				int length = q.size();
				for(int i = 0 ; i < length ; i++) {
					int tmp = q.poll();
					if(tmp + 1 == K || tmp - 1 == K || tmp * 2 == K) {
						System.out.println(level);
						return;
					}
					if(tmp + 1 <= max && !visited[tmp + 1]) {
						visited[tmp+1] = true;
						q.offer(tmp+1);
					} 
					if(tmp - 1 >= 0 && !visited[tmp-1]) {
						visited[tmp-1] = true;
						q.offer(tmp-1);
					}
					if(tmp * 2 <= max && !visited[tmp * 2]) {
						visited[2*tmp] = true;
						q.offer(2*tmp);
					}
				}
				level++;
			}

		}
		
	}

}
