import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegTree{
        int[] seg;
        int N;
        int left, right;

        SegTree(int N){
            this.N = N;
            // SegmentTree 에서 사용할 1차원 배열을 생성
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new int[size];
        }

        void init(int node, int start, int end){
            // Leaf Node 도착 여부
            if(start == end){
                seg[node] = input[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            seg[node] = Math.min(seg[node * 2], seg[node * 2 + 1]);
        }

        int queryInit(int left, int right){
            this.left = left;
            this.right = right;
            return query(1, 0, N - 1);
        }

        private int query(int node, int start, int end) {
            // 영역을 벗어나는지 탐색
            if(start > right || end < left) return Integer.MAX_VALUE;
            if(start >= left && end <= right) return seg[node];

            int mid = (start + end) / 2;
            return Math.min(query(node * 2, start, mid), query(node * 2 + 1, mid + 1, end));
        }
    }

    static int[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        input = new int[N];
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(br.readLine());
        }

        SegTree segTree = new SegTree(N);
        segTree.init(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            sb.append(segTree.queryInit(left, right)).append("\n");
        }
        System.out.println(sb);
    }
}
