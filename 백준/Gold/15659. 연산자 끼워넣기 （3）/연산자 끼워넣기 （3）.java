import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long max, min;
	static long[] number;
	static long[] operator;
	static long[] order;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 연산 대상 숫자 입력받기
		number = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			number[i] = Long.parseLong(st.nextToken());
		}
		
		// 연산자 횟수 입력받기
		operator = new long[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 4 ; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		order = new long[N-1];
		comb(0);
		
		StringBuilder sb = new StringBuilder();
		sb.append(max).append("\n").append(min);
		System.out.println(sb);
	}
	
	public static void comb(int depth) {
		if(depth == N-1) {
			calculate();
			return;
		}
		
		for(int i = 0 ; i < 4 ; i++) {
			if(operator[i] == 0) continue;
			operator[i]--;
			order[depth] = i;
			comb(depth + 1);
			operator[i]++;
		}
	}
	
	public static void calculate() {

		ArrayDeque<Long> stack = new ArrayDeque<>();
		stack.push(number[0]);
		for(int i = 1 ; i < N ; i++) {
			// 다음 숫자를 가져와
			long num_tmp = number[i];
			
			// 연산자가 있다면 연산자를 가져와
			if(i-1 < N-1) {
				long operator_tmp = order[i-1];
				if(operator_tmp == 0) { // 더하기야
					stack.push(num_tmp);
				}
				if(operator_tmp == 1) { // 빼기야
					stack.push( -1 * num_tmp);
				}
				if(operator_tmp == 2) { // 곱하기야
					stack.push(stack.pop() * num_tmp);
				}
				if(operator_tmp == 3) { // 나누기야
					stack.push(stack.pop() / num_tmp);
				}
			}
		}
		
		long result = 0;
		for(long num : stack) {
			result += num;
		}
		
		max = Math.max(max, result);
		min = Math.min(min, result);

	}
	
	public static int operator_strong(int operator) {
		if(operator == 1 || operator == 0) return 1;
		else return 2;
	}

}
