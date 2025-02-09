import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int H, W;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        H = 12;
        W = 6;

        map = new char[H][W];

        for(int c = 0 ; c < H ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < W ; r++){
                map[c][r] = line[r];
            }
        }


        int combo = 0;
        while (true){
//            print();
            // 사이클 시작 색깔 영역별로 4개 이상 뭉쳐있으면 터트린다.
            int numOfBomb = 0;
            visited = new boolean[H][W];
            for(int c = 0 ; c < H ; c++){
                for(int r = 0 ; r < W ; r++){
                    if(map[c][r] != '.'){ // 터트릴 수 있는지 확인
                        numOfBomb += bomb(c, r, map[c][r]);
                    }
                }
            }

            if(numOfBomb == 0){
                break;
            }

            // 아래 공간이 비어있는 줄을 재정렬
            gravity();
            combo++;
        }

        System.out.println(combo);
    }

    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    public static int bomb(int c, int r, char color){
        ArrayList<int[]> bombList = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        visited[c][r] = true;
        int[] start = new int[]{c, r};
        q.offer(start);
        bombList.add(start);
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0 ; i < 4 ; i++){
                int n_c = now[0] + dc[i];
                int n_r = now[1] + dr[i];
                if(n_c < 0 || n_c >= H || n_r < 0 || n_r >= W) continue;
                if(!visited[n_c][n_r] && map[n_c][n_r] == color){
                    visited[n_c][n_r] = true;
                    int[] next = new int[]{n_c, n_r};
                    bombList.add(next);
                    q.offer(next);
                }
            }
        }

        if(bombList.size() >= 4) { // 4개 이상 연결되어 있다면
            for (int[] ints : bombList) {
                map[ints[0]][ints[1]] = '.';
            }
            return bombList.size();
        }
        return 0;
    }

    public static void gravity(){
        for(int r = 0 ; r < W ; r++){
            LinkedList<Character> line = new LinkedList<>();
            for(int c = H-1 ; c >= 0 ; c--){ // 아래에서부터 현재 있는 유효 블록을 저장
                if(map[c][r] != '.'){ // 만약 시작이 비어있다면
                    line.add(map[c][r]);
                }
            }
            for(int c = H-1 ; c >= 0 ; c--){
                if(!line.isEmpty()){
                    map[c][r] = line.remove();
                }else{
                    map[c][r] = '.';
                }
            }
        }
    }

    public static void print(){
        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < H ; c++){
            for(int r = 0 ; r < W ; r++){
                sb.append(map[c][r]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.println(sb);
    }
}
