import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static boolean[] visited;
	static char[] input;
	static int[] numbers;
	static int max, length;
	static boolean find;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().toCharArray();
		length = input.length;
		if(length < 10) {
			StringBuilder sb = new StringBuilder();
			for(char c : input) {
				sb.append(c).append(" ");
			}
			System.out.println(sb);
		}else {
			max = 9 + (length-9)/2;
			visited = new boolean[max+1]; 
			numbers = new int[max];
			backTracking(0, 0);
		}
	}
	
	public static void backTracking(int depth, int start) {
		if(depth == max) { // 순열이 완성된다면
			find = true;
			StringBuilder sb = new StringBuilder();
			for(int num : numbers) {
				sb.append(num).append(" ");
			}
			System.out.println(sb);
			return;
		}{
			if(find) return;
			// 2개를 넣을 수 있는 경우
			if(start + 2 <= length) {
				int nextnum = input[start] - '0';
				if(nextnum != 0) {
					int twonum = nextnum * 10 + (input[start+1] - '0');
					
					if(twonum <= max && !visited[twonum]) {
						visited[twonum] = true;
						numbers[depth] = twonum;
						backTracking(depth + 1, start + 2);
						numbers[depth] = 0;
						visited[twonum] = false;
					}
					
					if(nextnum <= max && !visited[nextnum]) {
						visited[nextnum] = true;
						numbers[depth] = nextnum;
						backTracking(depth + 1, start + 1);
						numbers[depth] = 0;
						visited[nextnum] = false;
					}
				}	
			}
						
			// 1개를 넣을 수 있는 경우
			else if(start+1 <= length) {
				int nextnum = input[start] - '0';
				if(nextnum != 0 && nextnum <= max && !visited[nextnum]) {
					visited[nextnum] = true;
					numbers[depth] = nextnum;
					backTracking(depth + 1, start + 1);
					visited[nextnum] = false;
				}
			}
			
			return;
			
		}
	}
}
