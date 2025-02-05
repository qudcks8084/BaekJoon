import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = sc.nextInt();
		int max_in = 1;
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		for(int i = 0 ; i < N ; i++) {
			int target = sc.nextInt();
			
			// 만약 뽑으려는 값이 아직 넣지 않았다면
			if(target >= max_in) {
				while(target >= max_in) {
					sb.append("+\n");
					stack.push(max_in++);
				}
			}
			
			while(true) {
				if(stack.isEmpty()) {
					System.out.println("NO");
					return;
				}
				int tmp = stack.pop();
				sb.append("-\n");
				if(tmp == target) {
					break;
				}
			}
		}
		System.out.println(sb);
	}

}
