import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class P implements Comparable<P>{
		int pn; // 몇 번째 행성인지를 저장
		long position; // 차원별 위치를 저장
		
		public P(int pn, long position) {
			this.pn = pn;
			this.position = position;
		}

		@Override
		public int compareTo(P o) {
			// 좌표를 기준으로 오름차순으로 정렬
			return Long.compare(this.position, o.position);
		}
	}
	
	static class E implements Comparable<E>{
		int s, e; // 크루스칼을 위한 시작, 종료 좌표
		long w; // 행성간의 가중치 저장
		
		public E(int s, int e, long w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		@Override
		public int compareTo(E o) {
			return Long.compare(this.w, o.w);
		}
		
		
	}
	
	static int N;
	static P[] x_map, y_map, z_map;
	static int[] p; // Union&Find용
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		p = new int[N];
		for(int i = 0 ; i < N ; i++) {
			p[i] = i;
		}
		
		x_map = new P[N];
		y_map = new P[N];
		z_map = new P[N];
		
		for(int i = 0 ; i < N ; i++) {
			// 각 차원별로 행성의 인덱스와 좌표를 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			x_map[i] = new P(i, Long.parseLong(st.nextToken()));
			y_map[i] = new P(i, Long.parseLong(st.nextToken()));
			z_map[i] = new P(i, Long.parseLong(st.nextToken()));
		}
		
		// 행성간의 차원별 가장 가까운 간선 계산을 위해서 오름차순으로 정렬
		Arrays.sort(x_map);
		Arrays.sort(y_map);
		Arrays.sort(z_map);
		
		// 행성을 건너뛰는 길이는 필요가 없음, 가장 가까운 노드와의 거리를 계산해서 PQ에 추가
		PriorityQueue<E> pq = new PriorityQueue<>();
		
		P l_x = x_map[0];
		P l_y = y_map[0];
		P l_z = z_map[0];
		for(int i = 1 ; i < N ; i++) {
			P c_x = x_map[i];
			P c_y = y_map[i];
			P c_z = z_map[i];
			
			pq.offer(new E(l_x.pn, c_x.pn , c_x.position-l_x.position));
			pq.offer(new E(l_y.pn, c_y.pn , c_y.position-l_y.position));
			pq.offer(new E(l_z.pn, c_z.pn , c_z.position-l_z.position));
			
//			System.out.println("x | " + l_x.position + " -> " + c_x.position + " | w = " + (c_x.position-l_x.position));
//			System.out.println("y | " + l_y.position + " -> " + c_y.position + " | w = " + (c_y.position-l_y.position));
//			System.out.println("z | " + l_z.position + " -> " + c_z.position + " | w = " + (c_z.position-l_z.position));
			
			l_x = c_x;
			l_y = c_y;
			l_z = c_z;
			
		}
		
		long min_distance = 0;
		int numOfEdge = 0;
		// 크루스칼 시작
		while(!pq.isEmpty()) {
			E cur = pq.poll();
			if(union(cur.s, cur.e)) { // 너 사이클 안생기니?
				// 안생기면 추가해
				numOfEdge++;
				min_distance += cur.w;
			}
			
			// 간선의 개수가 행성의 총개수 -1 개가 되면 종료
			if(numOfEdge == N - 1) break;
		}
		
		System.out.println(min_distance);
		
	}
	
	public static int find(int x) {
		if(p[x] == x) return x;
		else return p[x] = find(p[x]);
	}
	
	public static boolean union(int a, int b) {
		int A = find(a);
		int B = find(b);
		if(A == B) return false;
		if(A > B) p[B] = A;
		else p[A] = B;
		return true;
	}
}
