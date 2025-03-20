import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N = 100;
    static int P = 10;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());

        boolean[][] paper = new boolean[N][N];

        int size = 0;
        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            for(int c = H ; c < H + P ; c++){
                for (int r = W; r < W + P; r++) {
                    if(paper[c][r]) continue;
                    paper[c][r] = true;
                    size++;
                }
            }
        }

        System.out.println(size);
    }
}
