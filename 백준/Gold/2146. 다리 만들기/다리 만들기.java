import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 구분
        int islandId = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    markIsland(i, j, islandId++);
                }
            }
        }

        // 최단 다리 길이 계산
        int minBridge = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 1) {
                    int bridgeLen = findShortestBridge(i, j, map[i][j]);
                    minBridge = Math.min(minBridge, bridgeLen);
                }
            }
        }

        System.out.println(minBridge);
    }

    // 섬 구분 메서드
    static void markIsland(int x, int y, int islandId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        map[x][y] = islandId;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1) {
                    map[nx][ny] = islandId;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // 최단 다리 길이 찾기 메서드
    static int findShortestBridge(int x, int y, int currentIsland) {
        visited = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = curr[0] + dx[d];
                int ny = curr[1] + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        q.offer(new int[]{nx, ny, curr[2] + 1});
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] != currentIsland) {
                        return curr[2];
                    }
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}