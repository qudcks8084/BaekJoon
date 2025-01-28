import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] distance;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // y
        M = Integer.parseInt(st.nextToken()); // x

        map = new int[M][N];
        distance = new int[M][N];

        for(int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[j][i] = line[j] - '0';
                distance[j][i] = -1;
            }
        }

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        distance[0][0] = 0;

        while(!queue.isEmpty()) {
            Point current = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < M && ny >= 0 && ny < N &&
                        map[nx][ny] == 1 && distance[nx][ny] == -1) {
                    distance[nx][ny] = distance[current.x][current.y] + 1;
                    queue.offer(new Point(nx, ny));
                }
            }
        }

        System.out.println(distance[M-1][N-1] + 1);
    }

}