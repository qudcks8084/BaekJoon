import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int cut, sum;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[31];
		sum = 0;
		cut = (int)Math.round((double)N * 0.15);
		int num_of_opinion = N - 2 * cut;
		
		
		for(int i = 0 ; i < N ; i++) {
			int input = Integer.parseInt(br.readLine());
			arr[input]++;
			sum += input;
		}
		
		
		// left cut
		int left = cut; // 삭제해야하는 개수
		int x = 0; // 현재 좌표
		while(left > 0) {
			
			// 현재 위치의 값이 없으면 넘어감
			if(arr[x] == 0) {
				x++;
				continue;
			}
			
			// 현재 위치의 값이 삭제해야하는 남은 값보다 많음
			if(arr[x] > left) {
				sum -= left * x;
				arr[x] -= left;
				break;
			}else { // 현재 위치의 값이 삭제해야하는 남은 값보다 많음
				sum -= x * arr[x];
				left -= arr[x];
				arr[x] = 0;
			}
			
			x++;
		}
		
		// right cut
		int right = cut; // 삭제해야하는 개수
		x = 30; //현재 좌표
		while(right > 0) {
		
			// 현재 위치의 값이 없으면 넘어감
			if(arr[x] == 0) {
				x--;
				continue;
			}
			
			// 현재 위치의 값이 삭제해야하는 남은 값보다 많음
			if(arr[x] > right) {
				sum -= right * x;
				arr[x] -= right;
				break;
			}else { // 현재 위치의 값이 삭제해야하는 남은 값보다 많음
				sum -= x * arr[x];
				right -= arr[x];
				arr[x] = 0;
			}
			
			x--;
		}
		
		System.out.println(Math.round((double)sum/(double)num_of_opinion));
		
	}
}
