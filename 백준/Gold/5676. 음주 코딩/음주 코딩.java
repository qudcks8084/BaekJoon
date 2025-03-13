import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        int[] seg;
        int N;
        int left, right;
        int index, value;

        SegmentTree(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new int[size];
            Arrays.fill(seg, 1);
        }

        void init(int node, int start, int end) {
            if (start == end) {
                if(input[start] == 0) seg[node] = 0;
                else if(input[start] < 0) seg[node] = -1;
                else seg[node] = 1;
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] * seg[node * 2 + 1];
        }

        void updateInit(int index, int value){
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        void update(int node, int start, int end) {
            if(index < start || index > end) return;

            if(start == end){
                if(value == 0) seg[node] = 0;
                else if(value < 0) seg[node] = -1;
                else seg[node] = 1;
                input[start] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] * seg[node * 2 + 1];
        }

        int queryInit(int left, int right) {
            this.left = left;
            this.right = right;
            return query(1, 0, N - 1);
        }

        int query(int node, int start, int end) {
            if(start > right || end < left) return 1;

            if(start >= left && end <= right) return seg[node];

            int mid = (start + end) / 2;
            return query(node * 2, start, mid)
                    * query(node * 2 + 1, mid + 1, end);
        }
    }

    static int[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            input = new int[N];
            for (int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            SegmentTree segmentTree = new SegmentTree(N);
            segmentTree.init(1, 0, N - 1);

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                char operator = st.nextToken().charAt(0);
                if(operator == 'C') {
                    int index = Integer.parseInt(st.nextToken()) - 1;
                    int value = Integer.parseInt(st.nextToken());
                    segmentTree.updateInit(index, value);
                } else {
                    int left = Integer.parseInt(st.nextToken()) - 1;
                    int right = Integer.parseInt(st.nextToken()) - 1;
                    int result = segmentTree.queryInit(left, right);
                    if(result > 0) sb.append("+");
                    else if(result < 0) sb.append("-");
                    else sb.append("0");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
