import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> num = new HashMap<>();
        HashMap<Integer, Integer> order = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int tmp = Integer.parseInt(st.nextToken());
            if(!order.containsKey(tmp)) order.put(tmp, i);
            num.put(tmp, num.getOrDefault(tmp, 0) + 1);
        }

        ArrayList<int[]> arr = new ArrayList<>();

        for (int number : num.keySet()) {
            arr.add(new int[]{number, num.get(number)});
        }
        
        arr.sort((num1, num2) -> {
            if (num1[1] != num2[1]) return Integer.compare(num2[1], num1[1]); 
            return Integer.compare(order.get(num1[0]), order.get(num2[0])); 
        });
        
        StringBuilder sb = new StringBuilder();
        for (int[] pair : arr) {
            int number = pair[0];
            int frequency = pair[1];
            sb.append((number + " ").repeat(frequency));
        }

        System.out.println(sb.toString().trim());
    }
}
