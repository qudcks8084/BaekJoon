import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < N ; i++) {
			long tmp = Long.parseLong(br.readLine());
			int num_of_five = 0;
			long five = 5;
			while(five <= tmp) {
				num_of_five+= tmp / five;
				five *= 5;
			}
			sb.append(num_of_five).append("\n");
		}
		System.out.println(sb);
	}
	
}
