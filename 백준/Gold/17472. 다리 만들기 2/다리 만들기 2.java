import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;
	static int[][] map;
	static int[] p;
	static boolean[][] visited;
	static ArrayList<int[]>[] adjList;
	static int[] dc = {-1, 0, 1, 0};
	static int[] dr = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		// 최대 6개의 섬
		adjList = new ArrayList[7];
		for(int i = 0 ; i < 7 ; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		map = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[c][r] = tmp;
			}
		}
		
		// 이제 각 섬의 넘버링을 하고 adjList에 저장
		visited = new boolean[H][W];
		int numOfIsland = 0;
		for(int c = 0 ; c < H ; c++) {
			for(int r= 0 ; r < W ; r++) {
				if(map[c][r] == 1 && !visited[c][r]) {
					island(c, r, numOfIsland);
					numOfIsland++;
				}
			}
		}
		
		// 각 섬을 기준으로 탐색하면서 PriorityQueue에 Edge 추가
		PriorityQueue<int[]> pq = new PriorityQueue<>(
				(o1, o2) -> (Integer.compare(o1[2], o2[2])));
		
		for(int i = 0 ; i < numOfIsland ; i++) {
			for(int[] node : adjList[i]) {
				int c = node[0];
				int r = node[1];
				
				// 4방향으로 다리 길이 측정
				for(int d = 0 ; d < 4 ; d++) {
					int bridgeLength = 0;
					int nc = c + dc[d];
					int nr = r + dr[d];
					
					// 같은 섬이 아닌 다른 지점을 찾아 다리 길이 측정
					while(nc >= 0 && nc < H && nr >= 0 && nr < W) {
						// 같은 섬을 만나면 중단
						if(map[nc][nr] == i + 1) break;
						
						// 바다를 만나면 다리 길이 증가
						if(map[nc][nr] == 0) {
							bridgeLength++;
						}
						// 다른 섬을 만난 경우
						else if(map[nc][nr] > 0 && map[nc][nr] != i + 1) {
							// 다리 길이가 1 이상인 경우에만 추가
							if(bridgeLength > 1) {
								pq.offer(new int[] {i, map[nc][nr] - 1, bridgeLength});
							}
							break;
						}
						
						nc += dc[d];
						nr += dr[d];
					}
				}
			}
		}
		
		// Kruskal 알고리즘 시작
		p = new int[numOfIsland];
		for(int i = 0 ; i < numOfIsland ; i++) {
			p[i] = i;
		}
		
		int numOfEdge = 0;
		int min_len = 0;
		while(!pq.isEmpty()) {
			int[] edge = pq.poll();
			int s = edge[0];
			int e = edge[1];
			int w = edge[2];
			if(Union(s,e)) {
				min_len += w;
				numOfEdge++;
			}
		}
		
		if(numOfEdge == numOfIsland - 1) {
			System.out.println(min_len);
		}else {
			System.out.println("-1");
		}
	}
	
	public static boolean Union(int x, int y) {
		int X = find(x);
		int Y = find(y);
		if(X == Y) return false;
		else {
			if(X > Y) p[X] = Y;
			else p[Y] = X;		
		}
		return true;
	}
	
	public static int find(int x) {
		if(p[x] == x) return x;
		else return p[x] = find(p[x]);
	}
	
	public static void island(int sc, int sr, int islandId) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		visited[sc][sr] = true;
		map[sc][sr] = islandId + 1;
		adjList[islandId].add(new int[] {sc, sr});
		q.offer(new int[] {sc, sr});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int c = cur[0];
			int r = cur[1];
			for(int i = 0 ; i < 4 ; i++) {
				int n_c = c + dc[i];
				int n_r = r + dr[i];
				if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
				if(visited[n_c][n_r] || map[n_c][n_r] != 1) continue;
				visited[n_c][n_r] = true;			
				map[n_c][n_r] = islandId + 1;
				q.offer(new int[] {n_c, n_r});
				adjList[islandId].add(new int[] {n_c, n_r});
			}
		}
	}
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				sb.append(map[c][r]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}