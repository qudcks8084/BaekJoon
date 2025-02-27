import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[][] copy;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		
		for(int c = 0 ; c < H ; c++) {
			st = new StringTokenizer(br.readLine());
			for(int r = 0 ; r < W ; r++) {
				map[c][r] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < R ; i++) {
			int next = Integer.parseInt(st.nextToken());
			if(next == 1) one();
			if(next == 2) two();
			if(next == 3) three();
			if(next == 4) four();
			if(next == 5) five();
			if(next == 6) six();
		}
		
		print();
	}
	
	public static void one() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			System.arraycopy(map[H-1-c], 0, copy[c], 0, W); 
		}
		map = copy;
	}
	
	public static void two() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[H][W];
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				copy[c][r] = map[c][W-1-r];
			}
		}
		map = copy;
	}
	
	public static void three() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[W][H];
		for(int c = 0 ; c < W ; c++) {
			for(int r = 0 ; r < H ; r++) {
				copy[c][r] = map[H-1-r][c];
			}
		}
		map = copy;
	}
	
	public static void four() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[W][H];
		for(int c = 0 ; c < W ; c++) {
			for(int r = 0 ; r < H ; r++) {
				copy[c][r] = map[r][W-1-c];
			}
		}
		map = copy;
	}
	
	public static void five() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[H][W];
		
		int half_c = H/2;
		int half_r = W/2;
	
		// 1번영역
		for(int c = 0 ; c < half_c ; c++) {
			for(int r = 0 ; r < half_r ; r++) {
				copy[c][r] = map[c+half_c][r];
			}
		}
		
		// 2번영역
		for(int c = 0 ; c < half_c ; c++) {
			for(int r = half_r ; r < W ; r++) {
				copy[c][r] = map[c][r-half_r];
			}
		}
		
		// 3번영역
		for(int c = half_c ; c < H ; c++) {
			for(int r = half_r ; r < W ; r++) {
				copy[c][r] = map[c-half_c][r];
			}
		}
		
		// 4번영역
		for(int c = half_c ; c < H ; c++) {
			for(int r = 0 ; r < half_r ; r++) {
				copy[c][r] = map[c][r+half_r];
			}
		}
		map = copy;
	}
	
	public static void six() {
		int H = map.length;
		int W = map[0].length;
		copy = new int[H][W];
		
		int half_c = H/2;
		int half_r = W/2;
	
		// 1번영역
		for(int c = 0 ; c < half_c ; c++) {
			for(int r = 0 ; r < half_r ; r++) {
				copy[c][r] = map[c][r+half_r];
			}
		}
		
		// 2번영역
		for(int c = 0 ; c < half_c ; c++) {
			for(int r = half_r ; r < W ; r++) {
				copy[c][r] = map[c+half_c][r];
			}
		}
		
		// 3번영역
		for(int c = half_c ; c < H ; c++) {
			for(int r = half_r ; r < W ; r++) {
				copy[c][r] = map[c][r-half_r];
			}
		}
		
		// 4번영역
		for(int c = half_c ; c < H ; c++) {
			for(int r = 0 ; r < half_r ; r++) {
				copy[c][r] = map[c-half_c][r];
			}
		}
		map = copy;
	}
	
	public static void print() {
		StringBuilder sb = new StringBuilder();
		int H = map.length;
		int W = map[0].length;
		for(int c = 0 ; c < H ; c++) {
			for(int r = 0 ; r < W ; r++) {
				sb.append(map[c][r]).append(" ");	
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
