import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static boolean[][] map;
    static boolean[][] visited;

    static int numOfSection;
    static int[] section;
    static int[][] section_map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new boolean[H][W];
        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                map[c][r] = line[r] == '1';
            }
        }

        // Flood-Fill
        section_map = new int[H][W];
        for(int i = 0 ; i < H ; i++){
            Arrays.fill(section_map[i], -1);
        }
        visited = new boolean[H][W];

        numOfSection = 0;
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                if(!map[c][r] && !visited[c][r]){
                    FloodFill(c, r, numOfSection++);
                }
            }
        }

        // 각 영역의 개수
        section = new int[numOfSection];
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                if(!map[c][r]){
                    section[section_map[c][r]] ++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                // 벽이라면 값을 계산해서 받아옴
                if(map[c][r]) sb.append(checkWall(c, r));

                // 벽이아니라면 0을 출력
                else sb.append("0");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void FloodFill(int c, int r, int num){
        visited[c][r] = true;
        section_map[c][r] = num;
        for(int i = 0 ; i < 4 ; i++){
            int n_c = c + dc[i];
            int n_r = r + dr[i];
            if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
            if(!visited[n_c][n_r] && !map[c][r]) FloodFill(n_c, n_r, num);
        }
    }

    public static int checkWall(int c, int r){
        boolean[] section_visited = new boolean[numOfSection];
        int numOfMove = 1; // 자기 자신이 초기값
        for(int i = 0 ; i < 4 ; i++){
            int n_c = c + dc[i];
            int n_r = r + dr[i];
            if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W || map[n_c][n_r]) continue;
            // 해당 좌표의 section 번호를 받아옴
            int section_num = section_map[n_c][n_r];

            // 이미 방문한 섹션이라면 이동하지 않음
            if(section_visited[section_num]) continue;

            // 방문하지 않은 섹션이라면, 값을 더하고, 방문처리함
            numOfMove += section[section_num];
            section_visited[section_num] = true;
        }
        return numOfMove % 10;
    }
}
