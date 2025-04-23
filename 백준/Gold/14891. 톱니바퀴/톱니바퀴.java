import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[] topPosition;
    static boolean[][] bracket;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = 4; // 톱니바퀴의 개수
        N = 8; // 톱니바퀴별 톱니의 개수

        bracket = new boolean[K][N];

        for(int i = 0 ; i < K ; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0 ; j < N ; j++){
                bracket[i][j] = line[j] == '1';
            }
        }

        topPosition = new int[K]; // 현재 위에 가리키고 있는 톱니바퀴의 인덱스를 저장

        int M = Integer.parseInt(br.readLine()); // 명령어의 개수

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start_index = Integer.parseInt(st.nextToken()) - 1; // 1부터 시작하는 인덱스를 0부터 시작하는 인덱스로 변환
            int clockwise = Integer.parseInt(st.nextToken());

            // 회전 전 각 톱니바퀴의 상태를 저장 (회전 전파를 위해)
            boolean[] willRotate = new boolean[K];
            willRotate[start_index] = true;

            // 오른쪽으로 전파 확인
            for(int j = start_index; j < K-1; j++) {
                boolean cur = bracket[j][(topPosition[j] + 2) % N]; // 현재 톱니바퀴의 오른쪽
                boolean next = bracket[j+1][(topPosition[j+1] + N - 2) % N]; // 다음 톱니바퀴의 왼쪽

                if(cur != next) {
                    willRotate[j+1] = true;
                } else {
                    break; // 더 이상 전파되지 않음
                }
            }

            // 왼쪽으로 전파 확인
            for(int j = start_index; j > 0; j--) {
                boolean cur = bracket[j][(topPosition[j] + N - 2) % N]; // 현재 톱니바퀴의 왼쪽
                boolean prev = bracket[j-1][(topPosition[j-1] + 2) % N]; // 이전 톱니바퀴의 오른쪽

                if(cur != prev) {
                    willRotate[j-1] = true;
                } else {
                    break; // 더 이상 전파되지 않음
                }
            }

            // 모든 톱니바퀴 회전 실행
            for(int j = 0; j < K; j++) {
                if(willRotate[j]) {
                    // 회전 방향: 시작점으로부터 홀수 거리면 반대로, 짝수 거리면 같은 방향
                    int rotateDirection = ((j - start_index) % 2 == 0) ? clockwise : -clockwise;
                    topPosition[j] = (topPosition[j] + N - rotateDirection) % N;
                }
            }
        }

        int sum = 0;
        for(int i = 0 ; i < K ; i++){
            if(bracket[i][topPosition[i]]) sum += (int) Math.pow(2, i);
        }
        System.out.println(sum);
    }
}