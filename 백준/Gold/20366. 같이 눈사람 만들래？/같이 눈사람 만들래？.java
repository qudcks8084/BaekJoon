import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class snowman implements Comparable<snowman>{
        int ballA;
        int ballB;
        int gap;

        public snowman(int ballA, int ballB, int gap) {
            this.ballA = ballA;
            this.ballB = ballB;
            this.gap = gap;
        }

        @Override
        public int compareTo(snowman o) {
            return Integer.compare(this.gap, o.gap);
        }

        @Override
        public String toString() {
            return "ballA=" + ballA + ", ballB=" + ballB + ", gap=" + gap;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] height = new int[N];

        // 눈사람의 지름별 개수를 저장할 배열 생성
        HashMap<Integer, Integer> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            height[i] = x;
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        // 모든 가능한 눈사람 조합을 생성하고 gap으로 정렬
        ArrayList<snowman> dp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                dp.add(new snowman(height[i], height[j], Math.abs(height[i] - height[j])));
            }
        }

        Collections.sort(dp);

        int min = Integer.MAX_VALUE;
        int left = 0;

        // 슬라이딩 윈도우
        for(int right = 1; right < dp.size(); right++) {
            snowman rightSnowman = dp.get(right);

            // 윈도우 크기 조정: 현재 차이가 최소값보다 크거나 같으면 left를 이동
            while(left < right) {
                snowman leftSnowman = dp.get(left);
                int currentDiff = Math.abs(rightSnowman.gap - leftSnowman.gap);

                if(currentDiff >= min) {
                    left++;
                } else {
                    break;
                }
            }

            // left부터 right-1까지의 모든 조합 확인
            for(int i = left; i < right; i++) {
                snowman leftSnowman = dp.get(i);
                int currentDiff = Math.abs(rightSnowman.gap - leftSnowman.gap);

                // 이미 최소값보다 크거나 같으면 더 이상 확인할 필요 없음
                if(currentDiff >= min) {
                    break;
                }

                // 검증 로직: 4개의 공이 모두 사용 가능한지 확인
                HashMap<Integer, Integer> tmp = new HashMap<>();
                tmp.put(leftSnowman.ballA, tmp.getOrDefault(leftSnowman.ballA, 0) + 1);
                tmp.put(leftSnowman.ballB, tmp.getOrDefault(leftSnowman.ballB, 0) + 1);
                tmp.put(rightSnowman.ballA, tmp.getOrDefault(rightSnowman.ballA, 0) + 1);
                tmp.put(rightSnowman.ballB, tmp.getOrDefault(rightSnowman.ballB, 0) + 1);

                boolean possible = true;
                for(int k : tmp.keySet()){
                    if(map.get(k) < tmp.get(k)){
                        possible = false;
                        break;
                    }
                }

                if(possible) {
                    min = currentDiff;
                    if(min == 0) {
                        // 최소값이 0이면 더 이상 찾을 필요 없음
                        System.out.println(min);
                        return;
                    }
                }
            }
        }

        System.out.println(min);
    }
}