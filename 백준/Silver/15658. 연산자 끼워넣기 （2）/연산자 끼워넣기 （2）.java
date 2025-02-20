import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long max, min;
	static int[] number;
	static int[] operator;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 연산 대상 숫자 입력받기
		number = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		// 연산자 횟수 입력받기
		operator = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		calculate(1, number[0]);
		
		StringBuilder sb = new StringBuilder();
		sb.append(max).append("\n").append(min);
		System.out.println(sb);
	}
	
	public static void calculate(int depth, long result) {
		if(depth == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			return;
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			if(operator[i] == 0) continue;
			operator[i]--;
			if(i == 0) calculate(depth + 1, result + number[depth]);
			if(i == 1) calculate(depth + 1, result - number[depth]);
			if(i == 2) calculate(depth + 1, result * number[depth]);
			if(i == 3) calculate(depth + 1, result / number[depth]);
			operator[i]++;
		}
	}

}
