import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());

        // 현재 남아있는 위치를 저장할 TreeSet 생성
        TreeSet<Integer> empty = new TreeSet<>();
        for(int i = 1 ; i <= G ; i++){
            empty.add(i);
        }

        int cnt = 0;

        // 앞에서부터 담으면서 못담으면 끝나는거야.
        for(int p = 0 ; p < P ; p++){
            int cur = Integer.parseInt(br.readLine());

            Integer smaller = empty.floor(cur);

            // 못넣음.
            if(smaller == null) break;

            empty.remove(smaller);
            cnt++;
        }

        System.out.println(cnt);
    }
}
