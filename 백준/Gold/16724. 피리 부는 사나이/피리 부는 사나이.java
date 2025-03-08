import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] p;
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];
        p = new int[H * W];
        for(int i = 0 ; i < H * W ; i++){
            p[i] = i;
        }
        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                char tmp = line[r];
                if(tmp == 'U') map[c][r] = 0;
                else if(tmp == 'R') map[c][r] = 1;
                else if(tmp == 'D') map[c][r] = 2;
                else if(tmp == 'L') map[c][r] = 3;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                int d = map[c][r];
                int nc = c + dc[d];
                int nr = r + dr[d];
                union(c * W + r, nc * W + nr);
                sb.append(map[c][r]).append(" ");
            }
            sb.append("\n");
        }

        int cnt = 0;
        for(int i = 0 ; i < H * W ; i++){
           if(i == find(i)) cnt++;
        }

        System.out.println(cnt);


    }

    public static int find(int x){
        if(p[x] == x) return x;
        else return p[x] = find(p[x]);
    }

    public static void union(int a, int b){
        int A = find(a);
        int B = find(b);
        if(A > B) p[A] = B;
        if(B > A) p[B] = A;
    }
}
