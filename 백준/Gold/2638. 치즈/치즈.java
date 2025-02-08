import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static boolean[][] map, visited;
    static int[][] melt;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        H = Integer.parseInt(input.nextToken());
        W = Integer.parseInt(input.nextToken());

        map = new boolean[H][W];

        for(int c = 0 ; c < H ; c++){
            input = new StringTokenizer(br.readLine());
            for( int r = 0 ; r < W ; r++){
                map[c][r] = input.nextToken().equals("1");
            }
        }

        int time = 0;
        while (true){
            melt = new int[H][W];
            visited = new boolean[H][W];
            findTargetCheese(0, 0);
            int numOfMeltCheese = meltCheese();
            if(numOfMeltCheese == 0){
                break;
            }
            time++;
        }
        System.out.println(time);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static void findTargetCheese(int c, int r){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[c][r] = true;
        q.offer(new int[]{c, r});
        while(!q.isEmpty()){
            int[] arr = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int n_c = arr[0] + dc[i];
                int n_r = arr[1] + dr[i];
                // 범위를 벗어나거나, 방문을 한적이 있다면 Out
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W || visited[n_c][n_r]) continue;
                if(map[n_c][n_r]) { // 치즈인 경우
                    melt[n_c][n_r]++;
                }
                else{ // 공기인 경우
                    visited[n_c][n_r] = true;
                    q.offer(new int[]{n_c, n_r});
                }
            }
        }
    }

    public static int meltCheese(){
        int numOfMeltCheese = 0;
        for(int c = 0 ; c < H ; c++){
            for( int r = 0 ; r < W ; r++){
                if(melt[c][r] > 1) { // 공기와 2면이상 붙어있는 경우
                    map[c][r] = false;
                    numOfMeltCheese++;
                }
            }
        }
        return numOfMeltCheese;
    }
}
