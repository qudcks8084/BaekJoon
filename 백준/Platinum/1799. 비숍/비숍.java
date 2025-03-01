import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] answer; // 흑/백 각각의 최대 비숍 수
    static boolean[] visited; // 기울기 1 방문 여부
    static boolean[] r_visited; // 기울기 -1 방문 여부

    // 흑/백 각각의 빈 칸 위치 저장
    static ArrayList<int[]> blackCells;
    static ArrayList<int[]> whiteCells;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        answer = new int[2]; // [0]: 흑, [1]: 백
        visited = new boolean[(N - 1) * 2 + 1];
        r_visited = new boolean[(N - 1) * 2 + 1];

        blackCells = new ArrayList<>();
        whiteCells = new ArrayList<>();

        // 입력 받으면서 흑/백 구분해서 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());

                if (value == 1) {
                    // (i + j) % 2 == 0이면 흑, 아니면 백
                    if ((i + j) % 2 == 0) {
                        blackCells.add(new int[]{i, j});
                    } else {
                        whiteCells.add(new int[]{i, j});
                    }
                }
            }
        }

        // 흑 칸에 대해 백트래킹
        bishop(0, 0, 0, blackCells);

        // 백 칸에 대해 백트래킹
        visited = new boolean[(N - 1) * 2 + 1];
        r_visited = new boolean[(N - 1) * 2 + 1];
        bishop(0, 0, 1, whiteCells);

        // 결과 출력
        System.out.println(answer[0] + answer[1]);
    }

    // color: 0 - 흑, 1 - 백
    public static void bishop(int depth, int numOfBishop, int color, ArrayList<int[]> cells) {
        // 가지치기: 남은 모든 칸에 비숍을 놓아도 현재 최대값을 넘을 수 없는 경우
        if (numOfBishop + cells.size() - depth < answer[color]) return;

        // 모든 칸을 확인했으면 결과 갱신
        if (depth == cells.size()) {
            answer[color] = Math.max(answer[color], numOfBishop);
            return;
        }

        // 현재 위치 정보
        int[] curr = cells.get(depth);
        int r = curr[0];
        int c = curr[1];

        // 대각선 기울기 계산
        int diag1 = r + c;            // 기울기 1 대각선
        int diag2 = r - c + (N - 1);  // 기울기 -1 대각선 (음수 방지를 위해 N-1 더함)

        // 비숍을 놓을 수 있는 경우
        if (!visited[diag1] && !r_visited[diag2]) {
            visited[diag1] = true;
            r_visited[diag2] = true;
            bishop(depth + 1, numOfBishop + 1, color, cells);
            visited[diag1] = false;
            r_visited[diag2] = false;
        }

        // 비숍을 놓지 않는 경우
        bishop(depth + 1, numOfBishop, color, cells);
    }
}