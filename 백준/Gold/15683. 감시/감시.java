import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int H, W;
    static int minSafeArea;
    static List<CCTV> cctvList;
    static int initialSafeArea;
    
    // 방향 벡터
    static int[] dc = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dr = {0, 1, 0, -1};
    
    static class CCTV {
        int type;
        int row;
        int col;
        
        public CCTV(int type, int row, int col) {
            this.type = type;
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        cctvList = new ArrayList<>();
        initialSafeArea = 0;
        minSafeArea = Integer.MAX_VALUE;

        // 맵 입력 및 CCTV 정보 저장
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0 && map[i][j] < 6) { // CCTV인 경우
                    cctvList.add(new CCTV(map[i][j], i, j));
                }
                if (map[i][j] == 0) {
                    initialSafeArea++;
                }
            }
        }

        // CCTV 방향 조합 탐색
        int[] directions = new int[cctvList.size()];
        findMinSafeArea(0, directions);
        
        System.out.println(minSafeArea);
    }

    // DFS로 모든 CCTV 방향 조합 탐색
    public static void findMinSafeArea(int index, int[] directions) {
        if (index == cctvList.size()) {
            // 모든 CCTV 방향이 결정됨, 감시 영역 계산
            minSafeArea = Math.min(minSafeArea, calculateSafeArea(directions));
            return;
        }
        
        CCTV cctv = cctvList.get(index);
        int dirLimit;
        
        // CCTV 유형에 따른 가능한 방향 수 결정
        switch (cctv.type) {
            case 5: // 5번 CCTV는 방향이 의미 없음 (4방향 모두 감시)
                directions[index] = 0;
                findMinSafeArea(index + 1, directions);
                break;
            case 2: // 2번 CCTV는 2가지 방향만 의미 있음 (수평, 수직)
                dirLimit = 2;
                for (int dir = 0; dir < dirLimit; dir++) {
                    directions[index] = dir;
                    findMinSafeArea(index + 1, directions);
                }
                break;
            default: // 1, 3, 4번 CCTV는 4가지 방향 모두 고려
                dirLimit = 4;
                for (int dir = 0; dir < dirLimit; dir++) {
                    directions[index] = dir;
                    findMinSafeArea(index + 1, directions);
                }
        }
    }

    // 주어진 CCTV 방향 조합으로 사각지대 크기 계산
    public static int calculateSafeArea(int[] directions) {
        boolean[][] watched = new boolean[H][W];
        int watchedCount = 0;
        
        // 각 CCTV별로 감시 영역 표시
        for (int i = 0; i < cctvList.size(); i++) {
            CCTV cctv = cctvList.get(i);
            int dir = directions[i];
            
            switch (cctv.type) {
                case 1: // 1방향
                    watchedCount += markWatched(cctv.row, cctv.col, dir, watched);
                    break;
                case 2: // 양방향 (수평 또는 수직)
                    watchedCount += markWatched(cctv.row, cctv.col, dir, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, (dir + 2) % 4, watched);
                    break;
                case 3: // 직각 방향 (상우, 우하, 하좌, 좌상)
                    watchedCount += markWatched(cctv.row, cctv.col, dir, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, (dir + 1) % 4, watched);
                    break;
                case 4: // 3방향
                    watchedCount += markWatched(cctv.row, cctv.col, dir, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, (dir + 1) % 4, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, (dir + 3) % 4, watched);
                    break;
                case 5: // 4방향
                    watchedCount += markWatched(cctv.row, cctv.col, 0, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, 1, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, 2, watched);
                    watchedCount += markWatched(cctv.row, cctv.col, 3, watched);
                    break;
            }
        }
        
        return initialSafeArea - watchedCount;
    }

    // 특정 방향으로 감시 영역 표시 및 새로 감시된 빈 칸 수 반환
    public static int markWatched(int row, int col, int direction, boolean[][] watched) {
        int count = 0;
        int nr = row;
        int nc = col;
        
        while (true) {
            nr += dc[direction];
            nc += dr[direction];
            
            // 맵 범위 체크
            if (nr < 0 || nr >= H || nc < 0 || nc >= W) break;
            
            // 벽을 만나면 중단
            if (map[nr][nc] == 6) break;
            
            // 빈 칸이고 아직 감시되지 않은 경우 카운트 증가
            if (map[nr][nc] == 0 && !watched[nr][nc]) {
                watched[nr][nc] = true;
                count++;
            }
            
            // CCTV가 있는 칸은 통과
        }
        
        return count;
    }
}