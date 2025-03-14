import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class LazySegment{
        long[] seg, lazy;
        int N, index;
        int left, right;
        long value;

        LazySegment(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            seg = new long[size];
            lazy = new long[size];
        }

        void init(int node, int start, int end) {
            if(start == end){
                seg[node] = input[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        void propagate(int node, int start, int end){
            if(lazy[node] == 0) return;

            // 범위까지의 추가로 더해주어야하는 lazy값을 계산해서 부모에 넣기
            seg[node] += (end - start + 1) * lazy[node];

            // Leaf-Node가 아니라면 자식에 lazy값을 부여
            if(start != end){
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            // 전파 이후 lazy값 초기화
            lazy[node] = 0;
        }

        void updateInit(int index, long value) {
            this.index = index;
            this.value = value;
            update(1, 0, N - 1);
        }

        void update(int node, int start, int end) {
            // lazy값을 전파
            propagate(node, start, end);

            if(index < start || index > end) return;

            if(start == end){
                seg[node] = value;
                return;
            }

            int mid = (start + end) / 2;
            update(node * 2, start, mid);
            update(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        void updateRangeInit(int left, int right, long value){
            this.left = left;
            this.right = right;
            this.value = value;
            updateRange(1, 0, N - 1);
        }

        void updateRange(int node, int start, int end) {
            propagate(node, start, end);

            if(start > right || end < left) return;

            if(start >= left && end <= right){

                // 먼저 부모 노드가 범위 만큼의 값을 전부 다 받음
                seg[node] += (end - start + 1) * value;

                // 리프 노드가 아니라면 자식 노드에 또 전파
                if(start != end){
                    lazy[node * 2] += value;
                    lazy[node * 2 + 1] += value;
                }

                return;
            }

            int mid = (start + end) / 2;
            updateRange(node * 2, start, mid);
            updateRange(node * 2 + 1, mid + 1, end);
            seg[node] = seg[node * 2] + seg[node * 2 + 1];
        }

        long queryInit(int left, int right) {
            this.left = left;
            this.right = right;
            return query(1, 0, N - 1);
        }

        long query(int node, int start, int end) {
            // 방문한 경우 lazy가 있는 경우 처리
            propagate(node, start, end);

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
        int M = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());

        input = new long[N];
        for(int i = 0 ; i < N ; i++){
            input[i] = Long.parseLong(br.readLine());
        }

        LazySegment lst = new LazySegment(N);
        lst.init(1, 0, N - 1);

        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int operator = Integer.parseInt(st.nextToken());
            if(operator == 1){ // 범위 변경
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                long value = Long.parseLong(st.nextToken());
                if(left < right) lst.updateRangeInit(left, right, value);
                else lst.updateRangeInit(right, left, value);
            }else{ // 값 추출
                int left = Integer.parseInt(st.nextToken()) - 1;
                int right = Integer.parseInt(st.nextToken()) - 1;
                if(left < right) sb.append(lst.queryInit(left, right)).append("\n");
                else sb.append(lst.queryInit(right, left)).append("\n");
                
            }
        }
        System.out.println(sb);
    }
}
