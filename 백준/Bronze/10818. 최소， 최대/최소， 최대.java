import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> arr = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i ++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        StringBuilder sb = new StringBuilder();
        sb.append(arr.get(0)).append(" ").append(arr.get(arr.size()-1));
        System.out.println(sb);


    }
}
