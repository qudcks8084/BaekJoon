import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W, R;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = Math.min(H/2, W/2);
		
		for(int num = 0 ; num < max ; num++) {
			rotate(num, num, num+1);
		}
		print();
		
	}
	
	public static void rotate(int c, int r, int k) {
		
		// 순서를 회전할 각각의 데이터를 수집
		ArrayDeque<Integer> q = new ArrayDeque<>();
		while(c < H-k) {
			q.offer(map[c++][r]);
		}
		while(r < W-k) {
			q.offer(map[c][r++]);
		}
		while(c >= k) {
			q.offer(map[c--][r]);
		}
		while(r >= k) {
			q.offer(map[c][r--]);
		}
		
		// 배열 돌리기
		for(int i = 0 ; i < R ; i++) {
			q.addFirst(q.removeLast());
		}
		
		// 다시 배열 저장하기 
		while(c < H-k) {
			map[c++][r] = q.poll();
		}
		while(r < W-k) {
			map[c][r++] = q.poll();
		}
		while(c >= k) {
			map[c--][r] = q.poll();
		}
		while(r >= k) {
			map[c][r--] = q.poll();
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
