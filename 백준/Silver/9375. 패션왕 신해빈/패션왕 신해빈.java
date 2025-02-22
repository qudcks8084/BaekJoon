import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> clothesMap = new HashMap<>();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken(); // 옷 이름은 필요 없음
                String type = st.nextToken();

                clothesMap.put(type, clothesMap.getOrDefault(type, 0) + 1);
            }

            int result = 1;
            for (int count : clothesMap.values()) {
                result *= (count + 1); // 해당 종류의 옷을 안 입는 경우 포함
            }

            sb.append(result - 1).append("\n"); // 최소 한 개 이상 입는 경우만 고려
        }
        System.out.println(sb);
    }
}
