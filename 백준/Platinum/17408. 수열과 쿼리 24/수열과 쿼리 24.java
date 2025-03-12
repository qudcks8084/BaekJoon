import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class SegmentTree{
        int[][] seg;
        int N;
        int left, right;
        int index, value;

        SegmentTree(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new int[size][2];
        }

        void init(int node, int start, int end) {
            if(start == end){
                seg[node][0] = input[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);

            if (seg[node * 2][0] >= seg[node * 2 + 1][0]) {
                seg[node][0] = seg[node * 2][0];
                seg[node][1] = Math.max(seg[node * 2][1], seg[node * 2 + 1][0]);
            } else {
                seg[node][0] = seg[node * 2 + 1][0];
                seg[node][1] = Math.max(seg[node * 2 + 1][1], seg[node * 2][0]);
            }
        }

        void updateInit(int index, int value){
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        private void update(int node, int start, int end) {
            if(index < start || index > end) return;

            if(start == end){
                seg[node][0] = value;
                input[start] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);

            if (seg[node * 2][0] >= seg[node * 2 + 1][0]) {
                seg[node][0] = seg[node * 2][0];
                seg[node][1] = Math.max(seg[node * 2][1], seg[node * 2 + 1][0]);
            } else {
                seg[node][0] = seg[node * 2 + 1][0];
                seg[node][1] = Math.max(seg[node * 2 + 1][1], seg[node * 2][0]);
            }
        }

        int queryInit(int left, int right) {
            this.left = left;
            this.right = right;
            int[] answer = query(1, 0, N - 1);
            return answer[0] + answer[1];
        }

        int[] query(int node, int start, int end) {
            if(start > right || end < left) return new int[]{0, 0};

            if(start >= left && end <= right) return seg[node];

            int mid = (start + end) / 2;

            int[] a = query(node * 2, start, mid);
            int[] b = query(node * 2 + 1, mid + 1, end);

            int[] tmp = new int[2];
            if(a[0] >= b[0]){
                tmp[0] = a[0];
                tmp[1] = Math.max(b[0], a[1]);
            } else {
                tmp[0] = b[0];
                tmp[1] = Math.max(a[0], b[1]);
            }
            return tmp;
        }
    }

    static int[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            input[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(1, 0, N - 1);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            if (operator == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                segmentTree.updateInit(index, value);
            } else {
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                sb.append(segmentTree.queryInit(left, right)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
