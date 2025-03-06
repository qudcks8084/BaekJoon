import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static ArrayList<int[]> chickens;
    static ArrayList<int[]> houses;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        // 치킨과 집들을 찾아 배열로 관리
        for(int c = 0 ; c < N ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                int now = Integer.parseInt(st.nextToken());
                if(now == 2) chickens.add(new int[]{c, r});
                if(now == 1) houses.add(new int[]{c, r});
            }
        }

        answer = Integer.MAX_VALUE;
        bitmaskCombination();
        System.out.println(answer);
    }

    public static void bitmaskCombination() {
        int chickenCount = chickens.size();

        // 비트마스크로 모든 조합 확인 (1 << chickenCount는 2^chickenCount)
        for (int i = 0; i < (1 << chickenCount); i++) {
            // 선택된 비트(1)의 수가 M과 같은 경우만 처리
            if (Integer.bitCount(i) == M) {
                // 선택된 치킨집 식별
                ArrayList<int[]> selectedChickens = new ArrayList<>();
                for (int j = 0; j < chickenCount; j++) {
                    // j번째 비트가 1인지 확인
                    if ((i & (1 << j)) != 0) {
                        selectedChickens.add(chickens.get(j));
                    }
                }

                // 치킨 거리 계산
                calculateChickenDistance(selectedChickens);
            }
        }
    }

    public static void calculateChickenDistance(ArrayList<int[]> selectedChickens) {
        int minChickenDistance = 0;

        for (int[] house : houses) {
            int min = Integer.MAX_VALUE;
            for (int[] chicken : selectedChickens) {
                min = Math.min(min, getDistance(house, chicken));
            }
            minChickenDistance += min;

            // 이미 최소값보다 크면 더 계산할 필요 없음
            if (minChickenDistance > answer) return;
        }

        answer = Math.min(answer, minChickenDistance);
    }

    public static int getDistance(int[] house, int[] chicken) {
        return Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
    }
}