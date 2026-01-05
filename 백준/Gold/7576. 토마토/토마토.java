import java.util.*;
import java.io.*;
public class Main {

    static int H, W;
    static int[][] farm;

    static int[] dc = {0, 1, 0, -1};
    static int[] dr = {1, 0, -1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        farm = new int[H][W];

        // 시작 토마토를 담아둘 큐를 초기화
        ArrayDeque<int[]> q = new ArrayDeque<>();

        int numOfNotGoodTomato = 0;
        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                int tmp = Integer.parseInt(st.nextToken());
                farm[c][r] = tmp;
                if(tmp == 1){
                    q.offer(new int[]{c, r});
                }else if(tmp == 0) {
                    numOfNotGoodTomato++;
                }
            }
        }

        int time = 0;
        while(!q.isEmpty()){
            if(numOfNotGoodTomato == 0) break;
            int len = q.size();
            for(int k = 0 ; k < len ; k++){
                int[] cur = q.poll();
                for(int i = 0 ; i < 4 ; i++){
                    int nc = cur[0] + dc[i];
                    int nr = cur[1] + dr[i];

                    if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                    if(farm[nc][nr] == 0){
                        numOfNotGoodTomato--;
                        farm[nc][nr] = 1;
                        q.offer(new int[]{nc, nr});
                    }
                }
            }
            time++;
        }

        if(numOfNotGoodTomato == 0){
            System.out.println(time);
        }else{
            System.out.println(-1);
        }
    }
}
