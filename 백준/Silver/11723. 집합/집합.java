import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] set = new boolean[21];
		
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			String operator = st.nextToken();
			if(operator.equals("add")) {
				int target = Integer.parseInt(st.nextToken());
				set[target] = true;
				continue;
			}
			if(operator.equals("check")) {
				int target = Integer.parseInt(st.nextToken());
				if(set[target]) sb.append("1\n");
				else sb.append("0\n");
				
				continue;
			}
			if(operator.equals("remove")) {
				int target = Integer.parseInt(st.nextToken());
				set[target] = false;
				continue;
			}
			if(operator.equals("toggle")) {
				int target = Integer.parseInt(st.nextToken());
				set[target] = !set[target];
				continue;
			}
			if(operator.equals("all")) {
				Arrays.fill(set, true);
				continue;
			}
			if(operator.equals("empty")) {
				Arrays.fill(set, false);
				continue;
			}
		}
		System.out.println(sb);
	}

}
