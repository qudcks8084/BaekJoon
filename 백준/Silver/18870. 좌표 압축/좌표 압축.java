import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] input = new int[N];
		int[] order = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			input[i] = tmp;
			order[i] = tmp;
		}
		
		Arrays.sort(order);
		
		HashMap<Integer, Integer> map = new HashMap<>();
		int last = 0;
		int index = 0;
		for(int num : order) {
			if(num != last) {
				map.put(num, index++);
				last = num;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int num : input) {
			sb.append(map.get(num)).append(" ");
		}
		System.out.println(sb);
	}
}
