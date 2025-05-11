import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class dict implements Comparable<dict>{
        String key;
        int value;

        @Override
        public int compareTo(dict o) {
            if(this.value == o.value){
                if(this.key.length() == o.key.length()) return this.key.compareTo(o.key);
                else return -Integer.compare(this.key.length(), o.key.length());
            }
            return -Integer.compare(this.value, o.value);
        }

        public dict(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i < N ; i++){
            String key = br.readLine();
            if(key.length() >= M) map.put(key, map.getOrDefault(key, 0) + 1);;
        }

        PriorityQueue<dict> pq = new PriorityQueue<>();
        for (String key : map.keySet()) {
            pq.add(new dict(key, map.get(key)));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll().key).append("\n");
        }
        System.out.println(sb);
    }
}
