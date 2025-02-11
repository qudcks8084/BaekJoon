import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int N, totalPopulation, answer;
	static int[][] connect;
	static int[] population;
	static boolean[] set;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		connect = new int[N][N];
		set = new boolean[N];
		totalPopulation = 0;
		
		population = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			population[i] = Integer.parseInt(st.nextToken());
			totalPopulation += population[i];
		}
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int numOfConnect = Integer.parseInt(st.nextToken());
			for(int index = 0 ; index < numOfConnect ; index++) {
				connect[i][index] = Integer.parseInt(st.nextToken())-1;
			}
		}
		
		answer = Integer.MAX_VALUE;
		subset(0, 0);
		if(answer == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(answer);
		
	}
	
	public static void subset(int depth, int numOfTrue) {
		if(depth == N) {
			if(numOfTrue == 0 || numOfTrue == N) return;
			visited = new boolean[N];
			int population_a = 0;
			int population_b = 0;
			for(int i = 0 ; i < N ; i++) { // 첫번째 A도시 진입
				if(set[i]) {
					population_a = findConnectedNumOfpolulation(i, true);
					break;
				}
			}
			
			for(int i = 0 ; i < N ; i++) {
				if(!set[i]) {
					population_b = findConnectedNumOfpolulation(i, false);
					break;
				}
			}
			
			if(population_a + population_b == totalPopulation) {
				answer = Math.min(answer, Math.abs(population_a - population_b));
			}
			
		}else {
			set[depth] = true;
			subset(depth + 1, numOfTrue + 1);
			set[depth] = false;
			subset(depth + 1, numOfTrue);
		}
	}
	
	public static int findConnectedNumOfpolulation(int n, boolean area) {
		int numOfPopulation = population[n];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[n] = true;
		q.offer(n);
		while(!q.isEmpty()) {
			int cur = q.poll();
			int[] nextList = connect[cur];
			for(int i = 0 ; i < N ; i++) {
				int next = nextList[i];
				if(!visited[next] && set[next] == area) { //방문할 수 있는 같은 구역이라면
					visited[next] = true;
					numOfPopulation += population[next];
					q.offer(next);
				}
			}
		}
		return numOfPopulation;
	}
}
