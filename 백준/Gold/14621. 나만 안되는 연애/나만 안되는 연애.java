import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Edge implements Comparable<Edge>{
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

    static int N, M;
    static boolean[] gender;
    static int[] p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N];
        for (int i = 0; i < N; i++) p[i] = i;

        gender = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++ ){
            gender[i] = st.nextToken().equals("M");
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            if (gender[s] != gender[e]) {
                pq.offer(new Edge(s, e, w));
            }
        }

        int minDistance = 0;
        int numOfEdge = 0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (union(cur.s, cur.e)) {
                minDistance += cur.w;
                numOfEdge++;
            }
        }

        if(numOfEdge == N-1) System.out.println(minDistance);
        else System.out.println(-1);

    }

    public static int find(int x) {
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }

    public static boolean union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A == B) return false;
        if(A > B) p[A] = B;
        else p[B] = A;
        return true;
    }
}
