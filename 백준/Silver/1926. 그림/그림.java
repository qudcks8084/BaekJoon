import java.util.*;
import java.io.*;

public class Main {

    static int W, H;
    static int NumOfPicture, Max;
    static boolean [][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new boolean[H][W];

        for(int c = 0 ; c < H ; c++) {
            st = new StringTokenizer(br.readLine());
            for (int r = 0; r < W; r++) {
                map[c][r] = Integer.parseInt(st.nextToken()) == 1;
            }
        }

        // 최대 넒이 및 그림의 개수 생성
        Max = 0;
        NumOfPicture = 0;

        // 0,0부터 탐험하면서 BFS 동작

        for(int c = 0 ; c < H ; c++) {
            for(int r = 0 ; r < W ; r++) {
                // 만약 아직 탐험이 되지 않은 영역이라면 탐색 시작
                if(map[c][r]){
                    NumOfPicture++;
                    search(c,r);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(NumOfPicture).append("\n").append(Max);
        System.out.println(sb);


    }

    static int[] dc = {-1, 0, 1 ,0};
    static int[] dr = {0, 1, 0, -1};
    public static void search(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{c,r});
        map[c][r] = false;
        int size = 1;

        // 그림의 크기를 측정하는 변수
        while(!q.isEmpty()){
            int[] cur = q.poll();

            // 다음 위치를 탐색
            for(int i = 0 ; i < 4 ; i++){
                int nc = cur[0] + dc[i];
                int nr = cur[1] + dr[i];
                if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                if(map[nc][nr]){
                    // 꺼낸 위치는 방문 처리 및 개수 증가 ++;
                    q.offer(new int[]{nc, nr});
                    map[nc][nr] = false;
                    size++;

                }
            }
        }

        Max = Math.max(Max, size);
    }
}
