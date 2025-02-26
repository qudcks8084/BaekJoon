import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int H, W;
    static int numOfCctv, numOfSafeArea, minSafeArea;
    static int[][] cctvList;
    static int[] arrowOfCCTV;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        cctvList = new int[8][3];
        arrowOfCCTV = new int[8];
        numOfCctv = 0;
        numOfSafeArea = 0;
        minSafeArea = Integer.MAX_VALUE;

        for(int c = 0 ; c < H ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < W ; r++){
                map[c][r] = Integer.parseInt(st.nextToken());
                if(map[c][r] > 0 && map[c][r] < 6){ // cctv인 경우
                    cctvList[numOfCctv][0] = map[c][r];
                    cctvList[numOfCctv][1] = c;
                    cctvList[numOfCctv][2] = r;
                    numOfCctv++;
                }
                if(map[c][r] == 0)
                    numOfSafeArea++;
            }
        }

        comb(0);
        System.out.println(minSafeArea);

    }

    public static void comb(int depth){
        if(depth == numOfCctv){
            cctv();
        }else{
            if(cctvList[depth][0] == 5) {
                comb(depth + 1);
            } else if (cctvList[depth][0] == 2) {
                for(int i = 0 ; i < 2 ; i++){
                    arrowOfCCTV[depth] = i;
                    comb(depth+1);
                }
            } else {
                for(int i = 0 ; i < 4 ; i++){
                    arrowOfCCTV[depth] = i;
                    comb(depth+1);
                }
            }
        }
    }


    static int[] dc = {-1, 0, 1 ,0};
    static int[] dr = {0, 1, 0, -1};
    public static void cctv(){
        visited = new boolean[H][W];
        int tmpSafeArea = numOfSafeArea;
        // 일단 큐에 진행 방향별로 다 집어넣어.
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i = 0 ; i < numOfCctv ; i++){
            int[] cctvInfo = cctvList[i];
            if(cctvInfo[0] == 1){ // 단방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], arrowOfCCTV[i]});
            }else if(cctvInfo[0] == 2){ // 양방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], arrowOfCCTV[i]});
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 2) % 4}); // 반대 방향
            }else if(cctvInfo[0] == 3){ // 앞우
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], arrowOfCCTV[i]});
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 1) % 4}); // 오른쪽 방향
            }else if(cctvInfo[0] == 4){ // 왼앞우
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], arrowOfCCTV[i]});
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 1) % 4}); // 오른쪽 방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 3) % 4}); // 왼쪽 방향
            }else if(cctvInfo[0] == 5){ // 전방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], arrowOfCCTV[i]});
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 1) % 4}); // 오른쪽 방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 2) % 4}); // 반대 방향
                q.offer(new int[]{cctvInfo[1], cctvInfo[2], (arrowOfCCTV[i] + 3) % 4}); // 왼쪽 방향
            }
        }

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int n_c = cur[0] + dc[cur[2]];
            int n_r = cur[1] + dr[cur[2]];
            if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
            if(map[n_c][n_r] != 6)
                q.offer(new int[]{n_c, n_r, cur[2]});
            if(!visited[n_c][n_r] && map[n_c][n_r] == 0){
                visited[n_c][n_r] = true;
                tmpSafeArea--;
            }
        }
        minSafeArea = Math.min(minSafeArea, tmpSafeArea);
    }
}
