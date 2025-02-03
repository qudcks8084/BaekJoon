import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static int N, house;
	static boolean[][] map;
	static int[] nx = {0,1,0,-1};
	static int[] ny = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new boolean[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			char[] input = br.readLine().toCharArray();
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = input[j] == '1';
			}
		}
		
		ArrayList<Integer> answer = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j]) {
					house = 1;
					map[i][j] = false;
					village(i, j);
					answer.add(house);
				}
			}
		}
		Collections.sort(answer);
		StringBuilder sb = new StringBuilder();
		
		sb.append(answer.size()).append("\n");
		for(int num : answer) {
			sb.append(num).append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void village(int x, int y) {
		
		for(int i = 0 ; i < 4 ; i++) {
			int n_x = x + nx[i];
			int n_y = y + ny[i];
			if(n_x < 0 || n_x >= N || n_y <0 || n_y >= N) continue;
			if(map[n_x][n_y]) {
				map[n_x][n_y] = false;
				house++;
				village(n_x, n_y);
			}
		}
	}
}
