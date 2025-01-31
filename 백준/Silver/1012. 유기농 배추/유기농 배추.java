import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] nx = {0,1,0,-1};
    static int[] ny = {-1,0,1,0};
    static boolean[][] map;
    static int M, N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int testcase = 0 ; testcase < T ; testcase++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new boolean[M][N];

            for(int i = 0 ; i < K ; i++){
                st = new StringTokenizer(br.readLine());
                map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            int answer = 0;
            for(int i = 0 ; i < M ; i++){
                for(int j = 0 ; j < N ; j++){
                    if(map[i][j]){
                        answer++;
                        search(i, j);
                    }
                }
            }

            System.out.println(answer);
        }

    }

    static void search(int x, int y){
        for(int i = 0 ; i < 4 ; i++){
            int n_x = x + nx[i];
            int n_y = y + ny[i];
            if(n_x >= 0 && n_x < M && n_y >= 0 && n_y < N && map[n_x][n_y]){
                map[n_x][n_y] = false;
                search(n_x, n_y);
            }
        }
    }
}
