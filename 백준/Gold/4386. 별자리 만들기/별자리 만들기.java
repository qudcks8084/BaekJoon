import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int PADDING = 1_000;
    static int N;
    static int[] p;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        p = new int[N];
        for(int i = 0 ; i < N ; i++) p[i] = i;

        // 일단 별들의 좌표를 전부 입력을 받아
        int[][] star = new int[N][2];
        for(int i = 0 ; i < N ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = (int)(Double.parseDouble(st.nextToken()) * PADDING);
            int y = (int)(Double.parseDouble(st.nextToken()) * PADDING);
            star[i][0] = x;
            star[i][1] = y;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            return Double.compare(o1[2], o2[2]);
        }));

        // 모든 별들간의 간선을 전부 집어넣어
        for(int i = 0 ; i < N ; i++){
            for(int j = i+1 ; j < N ; j++){
                pq.offer(new int[]{i, j, getDistance(star[i], star[j])});
            }
        }

        // 이제 KrusKal을 열심히 돌려
        int numOfLeftEdge = N - 1;
        int min_distance = 0;
        while (!pq.isEmpty()) {
            if(numOfLeftEdge == 0) break;
            int[] edge = pq.poll();
            int x = edge[0];
            int y = edge[1];
            int w = edge[2];
            if(Union(x, y)){ // True라면 사이클이 생기지 않는다는 뜻
                min_distance += w;
                numOfLeftEdge--;
            }
        }
        System.out.printf("%.2f",(double)min_distance/PADDING);

    }

    public static int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }

    public static boolean Union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A == B) return false;
        p[A] = B;
        return true;
    }


    public static int getDistance(int[] a, int[] b){
        return (int)Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }
}
