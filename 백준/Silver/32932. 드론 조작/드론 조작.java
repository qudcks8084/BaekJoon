import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};

    static boolean[][] map;
    static int M = 1001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new boolean[M][M];

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) + M / 2;
            int y = Integer.parseInt(st.nextToken()) + M / 2;
            map[x][y] = true;
        }

        int sx = 500;
        int sy = 500;

        char[] command = br.readLine().toCharArray();

        for(char c : command){
            if(c == 'U'){
                int n_y = sy + 1;
                if(n_y >= M || map[sx][n_y]) continue;
                sy = n_y;
            }
            else if(c == 'D'){
                int n_y = sy - 1;
                if(n_y < 0 || map[sx][n_y]) continue;
                sy = n_y;
            }
            else if(c == 'R'){
                int n_x = sx + 1;
                if(n_x >= M || map[n_x][sy]) continue;
                sx = n_x;
            }
            else {
                int n_x = sx - 1;
                if(n_x < 0 || map[n_x][sy]) continue;
                sx = n_x;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sx-500).append(" ").append(sy-500);
        System.out.println(sb);


    }

}
