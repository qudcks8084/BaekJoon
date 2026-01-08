import java.util.*;
import java.io.*;

public class Main {
    static int H, W;
    static boolean[][] map;
    static boolean[][] visited;

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void main(String[] args) throws Exception {

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new boolean[H][W];

        int numOfCheese = 0 ;

        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                boolean isCheese = st.nextToken().equals("1");
                if(isCheese) numOfCheese++;
                map[c][r] = isCheese;
            }
        }

        int time = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        ArrayDeque<int[]> melt = new ArrayDeque<>();

        visited = new boolean[H][W];

//        print();

        q.offer(new int[]{0, 0});
        visited[0][0] = true;

        while(true){

            while(!q.isEmpty()){
                int[] cur = q.poll();

                for(int i = 0 ; i < 4 ; i++){
                    int nc = cur[0] + dc[i];
                    int nr = cur[1] + dr[i];

                    if(nc < 0 || nc >= H || nr < 0 || nr >= W) continue;
                    if(visited[nc][nr]) continue;

                    visited[nc][nr] = true;

                    // 만약 치즈인 경우
                    if(map[nc][nr]){
                        melt.offer(new int[]{nc, nr});
                    }else{
                        q.offer(new int[]{nc, nr});
                    }
                }
            }

            // 만약 이번턴에 전부 종료되는 경우
            if(numOfCheese == melt.size()){
                StringBuilder sb = new StringBuilder();
                sb.append(time + 1).append("\n").append(numOfCheese);
                System.out.println(sb);
                break;
            }

            // 아닌 경우
            numOfCheese -= melt.size();
            while(!melt.isEmpty()){
                int[] cur = melt.poll();
                map[cur[0]][cur[1]] = false;
                q.offer(cur);
            }

//            print();

            time++;

        }
    }

    public static void print(){
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                sb.append(map[c][r] ? "x" : "-").append(" ");
            }

            sb.append("\t");

            for(int r = 0 ; r < W ; r++){
                sb.append(visited[c][r] ? "x" : "-").append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
