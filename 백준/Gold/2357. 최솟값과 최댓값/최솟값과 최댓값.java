import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class SegmentTree{
        int[] minSeg, maxSeg;
        int N;
        int left, right;

        SegmentTree(int N){
            this.N = N;
            int height = (int) Math.ceil(Math.log(N) / Math.log(2));
            int size = 2 << height;
            minSeg = new int[size];
            maxSeg = new int[size];
        }

        void init(int node, int start, int end){
            if(start == end){
                minSeg[node] = input[start];
                maxSeg[node] = input[start];
                return;
            }

            int mid = (start + end) / 2;
            init(node * 2, start, mid);
            init(node * 2 + 1, mid + 1, end);
            minSeg[node] = Math.min(minSeg[node * 2], minSeg[node * 2 + 1]);
            maxSeg[node] = Math.max(maxSeg[node * 2], maxSeg[node * 2 + 1]);
        }

        void query(int left, int right){
            this.left = left;
            this.right = right;
            sb.append(findMin(1, 0, N - 1));
            sb.append(" ");
            sb.append(findMax(1, 0, N - 1));
            sb.append("\n");
        }

        private int findMin(int node, int start, int end){
            if(start > right || end < left) return Integer.MAX_VALUE;

            if(start >= left && end <= right) return minSeg[node];

            int mid = (start + end) / 2;
            int l_min = findMin(node * 2, start, mid);
            int r_min = findMin(node * 2 + 1, mid + 1, end);
            return Math.min(l_min, r_min);
        }

        private int findMax(int node, int start, int end){
            if(start > right || end < left) return Integer.MIN_VALUE;

            if(start >= left && end <= right) return maxSeg[node];

            int mid = (start + end) / 2;
            int l_max = findMax(node * 2, start, mid);
            int r_max = findMax(node * 2 + 1, mid + 1, end);
            return Math.max(l_max, r_max);
        }
    }

    static int[] input;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(1, 0, N - 1);

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            segmentTree.query(left, right);
        }

        System.out.println(sb);
    }
}
