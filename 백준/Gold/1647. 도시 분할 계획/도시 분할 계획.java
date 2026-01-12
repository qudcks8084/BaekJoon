import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        p = new int[N];
        for (int i = 0 ; i < N ; i++){
            p[i] = i;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->{
            return Integer.compare(o1[2], o2[2]);
        });

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{s, d, w});
        }

        int numOfCity = N - 1;
        int min_road = 0;
        while(!pq.isEmpty()){
            if(numOfCity == 1) break; // 2개의 도시가 된다면 중지
            int[] edge = pq.poll();
            int s = edge[0];
            int d = edge[1];
            int w = edge[2];
            if (Union(s, d)) { // true라면 둘이 달랐다는 것. -> 사이클 X
                numOfCity--;
                min_road += w;
            }
        }
        System.out.println(min_road);
    }

    public static int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }

    public static boolean Union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A == B) return false;
        else p[A] = B;
        return true;
    }
}
