import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    static int N;
    static int[][] dice;
    static int[] opposite = {5, 3, 4, 1, 2, 0}; // 주사위 각 면의 반대편 인덱스
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[N][6];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 6; j++){
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        // 첫 번째 주사위의 윗면을 선택
        for(int firstTop = 0; firstTop < 6; firstTop++) {
            int firstBottom = opposite[firstTop];
            // 최댓값 찾기
            int sum = getMaxSide(0, firstTop, firstBottom);
            // 현재 윗면 값 (다음 주사위의 아랫면과 일치해야 함)
            int currentTop = dice[0][firstTop];
            // 두 번째 주사위부터 쌓기
            for(int i = 1; i < N; i++) {
                int bottomIdx = findFaceIndex(i, currentTop);
                int topIdx = opposite[bottomIdx];
                sum += getMaxSide(i, topIdx, bottomIdx);
                currentTop = dice[i][topIdx];
            }
            // 최댓값 갱신
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }

    public static int getMaxSide(int diceIdx, int topIdx, int bottomIdx) {
        int topValue = dice[diceIdx][topIdx];
        int bottomValue = dice[diceIdx][bottomIdx];

        // 6부터 1까지 내림차순으로 검사
        for(int value = 6; value >= 1; value--) {
            // 현재 value가 top이나 bottom의 값이 아니면 즉시 반환
            if(value != topValue && value != bottomValue) {
                return value;
            }
        }
        return 0;
    }

    // 주사위에서 특정 값을 가진 면의 인덱스 찾기
    public static int findFaceIndex(int diceIdx, int value) {
        for(int i = 0; i < 6; i++) {
            if(dice[diceIdx][i] == value) {
                return i;
            }
        }
        return -1;
    }
}