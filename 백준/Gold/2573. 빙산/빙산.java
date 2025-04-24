import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int H, W;
    static int[][] map;
    static int[][] minus;
    static boolean[][] visited;
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W]; // 현재 빙산의 높이를 저장할 맵
        minus = new int[H][W]; // 각 시간별 감소를 받을 공격의 배열

        int numOfIceLand = 0;
        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp > 0){
                    map[c][r] = tmp;
                    numOfIceLand++;
                }
                else{ // 0인 경우에 minus Attack에 +1씩 등록
                    for(int i = 0 ; i < 4 ; i++){
                        int nc = c + dc[i];
                        int nr = r + dr[i];
                        if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                        minus[nc][nr]++;
                    }
                }
            }
        }

        int time = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        while(numOfIceLand > 0){ // 남은 얼음이 0보다 크다면 종료

            // 1. 현재 섬의 개수를 찾는다.
            int numOfIsland = 0;
            visited = new boolean[H][W];
            for(int c = 0 ; c < H ; c++){
                for(int r = 0 ; r < W ; r++){
                    if(visited[c][r] || map[c][r] == 0) continue;
                    numOfIsland++;

                    q.offer(new int[]{c, r});
                    while (!q.isEmpty()){
                        int[] cur = q.poll();
                        for(int i = 0 ; i < 4 ; i++){
                            int nc = cur[0] + dc[i];
                            int nr = cur[1] + dr[i];

                            if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                            if(map[nc][nr] == 0 || visited[nc][nr]) continue;
                            visited[nc][nr] = true;

                            q.offer(new int[]{nc, nr});
                        }
                    }
                }
            }

            // 만약 섬의 개수가 1개보다 많다면 종료
            if(numOfIsland > 1){
                System.out.println(time);
                return;
            }

            // 만약 섬의 개수가 1개라면 현재 섬들에 공격을 반영
            // 시간을 ++;
            int[][] after = new int[H][W];
            for(int c = 0 ; c < H ; c++){
                for(int r = 0 ; r < W ; r++){
                    if(map[c][r] == 0) continue;
                    map[c][r] -= minus[c][r];
                    if(map[c][r] < 1){
                        map[c][r] = 0;
                        for(int i = 0 ; i < 4 ; i++){
                            int nc = c + dc[i];
                            int nr = r + dr[i];
                            if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                            after[nc][nr]++;
                        }
                        numOfIceLand--;
                    }
                }
            }

            for(int c = 0 ; c < H ; c++) {
                for (int r = 0; r < W; r++) {
                    if(after[c][r] == 0) continue;
                    minus[c][r] += after[c][r];
                }
            }


            time++;
        }

        System.out.println(0);

    }
}
