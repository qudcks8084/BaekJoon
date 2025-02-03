import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int M, N, K, num;
    static boolean[][] map;
    static int[] nx = {-1, 0, 1, 0};
    static int[] ny = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new boolean[M][N];
        for(int i = 0 ; i < K ; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            for(int x = x1 ; x < x2 ; x++){
                for(int y = y1 ; y < y2 ; y++){
                    map[y][x] = true;
                }
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0 ; i < M ; i++){
            for(int j = 0 ; j < N ; j++){
                if(!map[i][j]){
                    map[i][j] = true;
                    num = 1;
                    section(i,j);
                    arr.add(num);
                }
            }
        }

        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        sb.append(arr.size()).append("\n");
        for (int i : arr) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static void section(int x, int y){
        for(int i = 0 ; i < 4; i++){
            int n_x = x + nx[i];
            int n_y = y + ny[i];
            if(n_x < 0 || n_y < 0 || n_x >= M || n_y >= N) continue;
            if(!map[n_x][n_y]){
                num++;
                map[n_x][n_y] = true;
                section(n_x, n_y);
            }
        }
    }
}
