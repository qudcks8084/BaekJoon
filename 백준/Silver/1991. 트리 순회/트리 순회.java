import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb;
    static int[][] node;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        node = new int[N][2];

        StringTokenizer st;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int parent = st.nextToken().charAt(0)-'A';
            int left = st.nextToken().charAt(0)-'A';
            int right = st.nextToken().charAt(0)-'A';
            node[parent][0] = left;
            node[parent][1] = right;
        }

        sb = new StringBuilder();

        pre(0);
        sb.append("\n");

        in(0);
        sb.append("\n");

        post(0);
        sb.append("\n");

        System.out.println(sb);
    }

    public static void pre(int mid){
        sb.append((char)(mid + 'A'));
        if(node[mid][0] >=0) pre(node[mid][0]);
        if(node[mid][1] >=0) pre(node[mid][1]);
    }

    public static void in(int mid){
        if(node[mid][0] >=0) in(node[mid][0]);
        sb.append((char)(mid + 'A'));
        if(node[mid][1] >=0) in(node[mid][1]);
    }

    public static void post(int mid){
        if(node[mid][0] >=0) post(node[mid][0]);
        if(node[mid][1] >=0) post(node[mid][1]);
        sb.append((char)(mid + 'A'));
    }
}
