import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, max, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] nx = {0,1,0,-1};
    static int[] ny = {-1,0,1,0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        N = Integer.parseInt(br.readLine());
        max = Integer.MIN_VALUE;
        answer = 1;
        map = new int[N][N];

        // 입력 받기 + 최대 높이 받기
        StringTokenizer st;
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        // 각 높이별 순회
        for(int i = 1 ; i < max ; i++){ // 높이
            visited = new boolean[N][N];
            int tmp = 0;
            // 좌표별 순회
            for(int j = 0 ; j < N ; j++){
                for(int k = 0 ; k < N ; k++){
                    if(map[j][k] > i && !visited[j][k]){
                        tmp++;
                        Union(j, k, i);
                    }
                }
            }
            
            answer = Math.max(answer, tmp);
        }

        System.out.println(answer);
    }

    private static void Union(int x, int y, int level) {
        visited[x][y] = true;
        for(int i = 0 ; i < 4; i++){
            int n_x = x + nx[i];
            int n_y = y + ny[i];
            if(n_x >= 0 && n_x < N && n_y >= 0 && n_y < N && map[n_x][n_y] > level && !visited[n_x][n_y]){
                Union(n_x, n_y, level);
            }
        }
    }
}
