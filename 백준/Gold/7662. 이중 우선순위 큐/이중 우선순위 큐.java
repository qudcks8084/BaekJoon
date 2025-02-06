import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int testcase = 1 ; testcase <= T ; testcase++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int target = Integer.parseInt(st.nextToken());
				if(command.equals("I")) {
					map.put(target, map.getOrDefault(target, 0) + 1);
				}
				if (command.equals("D")) {
				    if (map.isEmpty()) continue;
				    if (target == -1) { 
				        int tmp = map.firstKey();
				        if (map.get(tmp) == 1) map.remove(tmp);
				        else map.put(tmp, map.get(tmp) - 1);
				    }
				    if (target == 1) {
				        int tmp = map.lastKey();
				        if (map.get(tmp) == 1) map.remove(tmp);
				        else map.put(tmp, map.get(tmp) - 1); 
				    }
				}
			}
			if(map.isEmpty()) {
				sb.append("EMPTY\n");
			}else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}
