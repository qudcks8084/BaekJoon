import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        int[][] seg;
        int N;
        int index, value;

        SegmentTree(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new int[size][2];
            for(int i = 0 ; i < size ; i++){
                seg[i][0] = Integer.MAX_VALUE;
            }
        }

        void init(int node, int start, int end){
            if(start == end){
                seg[node][0] = input[start];
                seg[node][1] = start;
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);

            // 작은 것을 기준으로 값과 인덱스를 저장함
            int a = seg[node * 2][0];
            int b = seg[node * 2 + 1][0];
            if(a == b){
                seg[node][0] = a;
                seg[node][1] = Math.min(seg[node * 2][1], seg[node * 2 + 1][1]);
            } else if (a > b) { // b가 더 작음
                seg[node][0] = b;
                seg[node][1] = seg[node * 2 + 1][1];
            } else { // a가 더 작음
                seg[node][0] = a;
                seg[node][1] = seg[node * 2][1];
            }
        }

        void updateInit(int index, int value){
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        void update(int node, int start, int end) {
            if(index < start || index > end) return;

            if(start == end){
                seg[node][0] = value;
                input[start] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);

            // 작은 것을 기준으로 값과 인덱스를 저장함
            int a = seg[node * 2][0];
            int b = seg[node * 2 + 1][0];
            if(a == b){
                seg[node][0] = a;
                seg[node][1] = Math.min(seg[node * 2][1], seg[node * 2 + 1][1]);
            } else if (a > b) { // b가 더 작음
                seg[node][0] = b;
                seg[node][1] = seg[node * 2 + 1][1];
            } else { // a가 더 작음
                seg[node][0] = a;
                seg[node][1] = seg[node * 2][1];
            }
        }
    }

    static int[] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        input = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(1, 0, N - 1);

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            if(operator == 1){
                int index = Integer.parseInt(st.nextToken()) - 1;
                int value = Integer.parseInt(st.nextToken());
                segmentTree.updateInit(index, value);
            }else{
                sb.append(segmentTree.seg[1][1] + 1).append("\n");
            }
        }
        System.out.println(sb);
    }
}
