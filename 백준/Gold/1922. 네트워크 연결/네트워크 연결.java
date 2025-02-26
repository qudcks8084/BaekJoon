import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static public class Edge implements Comparable<Edge>{
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
    static int[] p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        p = new int[N + 1];
        for(int i = 1 ; i <= N ; i++){
            p[i] = i;
        }


        PriorityQueue<Edge> pq = new PriorityQueue<>();
        StringTokenizer st;
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            if(s != e) pq.offer(new Edge(s, e, w));
        }

        long result = 0;
        int numOfEdge = 0;
        while(numOfEdge != N-1){
            Edge cur = pq.poll();
            if (Union(cur.s, cur.e)) { // 추가할 수 있는 경우
                numOfEdge++;
                result += cur.w;
            }
        }
        System.out.println(result);

    }

    public static int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }

    public static boolean Union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A != B){
            p[B] = A;
            return true;
        }
        return false;
    }
}
