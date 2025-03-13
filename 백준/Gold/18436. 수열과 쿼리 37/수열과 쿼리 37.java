import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        int[][] seg;
        int N;
        int left, right;
        int index, value;

        SegmentTree(int N) {
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new int[size][2];
        }

        void init(int node, int start, int end) {
            if(start == end){
                // 짝수라면 0이 1 / 홀수라면 1이 0
                seg[node][input[start] % 2] = 1;
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            seg[node][0] = seg[node * 2][0] + seg[node * 2 + 1][0]; // 짝수 병합
            seg[node][1] = seg[node * 2][1] + seg[node * 2 + 1][1]; // 홀수 병합
        }

        void updateInit(int index, int value) {
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        private void update(int node, int start, int end) {
            if(index < start || index > end) return;

            if(start == end){
                // 기존 값을 초기화
                Arrays.fill(seg[node], 0);
                
                // 짝수라면 0이 1 / 홀수라면 1이 0
                seg[node][value % 2] = 1;
                input[start] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);
            seg[node][0] = seg[node * 2][0] + seg[node * 2 + 1][0]; // 짝수 병합
            seg[node][1] = seg[node * 2][1] + seg[node * 2 + 1][1]; // 홀수 병합
        }

        int queryInit(int left, int right, int mode){
            this.left = left;
            this.right = right;
            return query(1, 0, N - 1, mode);
        }

        private int query(int node, int start, int end, int mode) {
            if(start > right || end < left) return 0;

            if(start >= left && end <= right) return seg[node][mode];

            int mid = (start + end) / 2;
            return query(node * 2, start, mid, mode)
                    + query(node * 2 + 1, mid + 1, end, mode);
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


        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            if(operator == 1){ // 데이터 수정
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                segmentTree.updateInit(index, value);
            }else{ // 데이터 받기
                int mode = operator % 2;
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                sb.append(segmentTree.queryInit(left, right, mode)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
