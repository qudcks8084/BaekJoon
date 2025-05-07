import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] origin = new int[26];
        String first = br.readLine();
        for (char c : first.toCharArray()) {
            origin[c-'A']++;
        }

        int firstLength = first.length();
        int answer = 0;

        for(int i = 1; i < N; i++) {
            int[] current = new int[26];
            String word = br.readLine();
            for (char c : word.toCharArray()) {
                current[c-'A']++;
            }

            // 길이 차이가 2 이상이면 비슷한 단어가 될 수 없음
            if (Math.abs(firstLength - word.length()) > 1) {
                continue;
            }

            int diff = 0;
            int diffSum = 0;

            for (int j = 0; j < 26; j++) {
                diff += Math.abs(origin[j] - current[j]);
                diffSum += (origin[j] - current[j]);
            }

            // 비슷한 단어 판별 조건
            // 1. 완전히 같은 경우 (diff == 0)
            // 2. 한 문자를 더하거나 빼는 경우 (diff == 1)
            // 3. 한 문자를 다른 문자로 바꾸는 경우 (diff == 2 && diffSum == 0)
            if (diff == 0 || diff == 1 || (diff == 2 && diffSum == 0)) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}