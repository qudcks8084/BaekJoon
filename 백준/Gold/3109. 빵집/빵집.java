import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	
	static char[][] map;
	static int R, C;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[C][R];
		
		for(int y = 0 ; y < R ; y++) {
			char[] input = br.readLine().toCharArray();
			for(int x = 0 ; x < C ; x++) {
				map[x][y] = input[x];
			}
		}
		
		answer = 0;
		
		for(int y = 0 ; y < R ; y++) {
			if(DFS(0, y)) answer++;
		}
		
		System.out.println(answer);
	}

	private static boolean DFS(int x, int y) {
		if(x+1 == C) {
			return true;
		}
		
		if( y-1 >= 0 && map[x+1][y-1] != 'x') {
			map[x+1][y-1] = 'x';
			if(DFS(x+1, y-1)) return true;
		} 
		
		if(map[x+1][y] != 'x') {
			map[x+1][y] = 'x';
			if(DFS(x+1, y)) return true;
		} 
		
		if( y+1 < R && map[x+1][y+1] != 'x') {
			map[x+1][y+1] = 'x';
			if(DFS(x+1, y+1)) return true;
		} 
		
		return false;
	}
	
}
