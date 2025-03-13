import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        long[] seg;
        int N, index;
        int left, right;
        long value;

        SegmentTree(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new long[size];
        }

        void init(int node, int start, int end){
            if(start == end){
                seg[node] = input[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        void updateInit(int index, long value){
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        void update(int node, int start, int end){
            if(index < start || index > end) return;

            if(start == end){
                seg[node] = value;
                input[start] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        long queryInit(int left, int right){
            this.left = left;
            this.right = right;
            return query(1, 0, N - 1);
        }

        long query(int node, int start, int end) {
            if(start > right || end < left) return 0;

            if(start >= left && end <= right) return seg[node];

            int mid = (start + end) / 2;
            return query(node * 2, start, mid)
                    + query(node * 2 + 1, mid + 1, end);
        }
    }

    static long[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        input = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Long.parseLong(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            if(left < right) sb.append(segmentTree.queryInit(left, right)).append("\n");
            else sb.append(segmentTree.queryInit(right, left)).append("\n");

            int index = Integer.parseInt(st.nextToken()) - 1;
            long value = Long.parseLong(st.nextToken());
            segmentTree.updateInit(index, value);
        }

        System.out.println(sb);

    }
}
