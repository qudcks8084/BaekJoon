import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> num_map = new HashMap<>();
        HashMap<String, Integer> str_map = new HashMap<>();

        for(int i = 1 ; i <= N ; i++){
            String name = br.readLine();
            num_map.put(i, name);
            str_map.put(name, i);
        }

        StringBuilder sb = new StringBuilder();
        for(int j = 0 ; j < M ; j++){
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                sb.append(num_map.get(Integer.parseInt(input)));
            }else{
                sb.append(str_map.get(input));
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
