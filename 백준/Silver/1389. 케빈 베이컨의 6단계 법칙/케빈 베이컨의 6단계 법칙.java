import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for(int i = 0 ; i < N ; i++){
            Arrays.fill(map[i], Integer.MAX_VALUE / 10);
        }

        for(int i = 0 ; i < M ; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            map[a][b] = 1;
            map[b][a] = 1;
        }

        for(int k = 0 ; k < N ; k++){
            for(int i = 0 ; i < N ; i++){
                for(int j = 0 ; j < N ; j++){
                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }

        int max = Integer.MAX_VALUE / 10;
        int idx = -1;
        for(int i = 0 ; i < N ; i++){
            int sum = 0;
            for(int j = 0 ; j < N ; j++){
                if(i == j) continue;
                sum += map[i][j];
            }
            if(max > sum){
                max = sum;
                idx = i;
            }
        }
        System.out.println(idx + 1);
    }
}
