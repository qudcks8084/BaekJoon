import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for(int i = 0 ; i < N ; i++){
            set.add(br.readLine());
        }

        int answer = 0;
        LinkedList<String> arr = new LinkedList<>();
        for(int i = 0 ; i < M ; i++){
            String tmp = br.readLine();
            if(set.contains(tmp)){
                arr.add(tmp);
                answer++;
            }
        }
        StringBuilder sb = new StringBuilder();
        Collections.sort(arr);
        sb.append(answer).append("\n");
        for (String s : arr) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
