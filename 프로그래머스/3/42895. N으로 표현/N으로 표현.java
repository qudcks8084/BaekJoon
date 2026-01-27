/* 
    HashSet으로 푼다?
    N을 1번 쓴다면 N 뿐이다.
    
    여기에서 1번을 더쓰려면 더하기 빼기 나누기 곱하기를 하면된다?
*/

import java.util.*;
class Solution {
    public int solution(int N, int number) {
        // N과 number가 같으면 1번 만에 가능
        if (N == number) {
            return 1;
        }

        HashSet<Integer>[] dp = new HashSet[9];
        for (int i = 0; i <= 8; i++) {
            dp[i] = new HashSet<>();
        }

        // N을 1번~8번 사용했을 때의 연속된 숫자(5, 55, 555...) 미리 저장
        int num = N;
        for (int i = 1; i <= 8; i++) {
            dp[i].add(num);
            num = num * 10 + N;
        }

        for (int i = 2; i <= 8; i++) {
            // i번 사용해서 만들 수 있는 수는
            // (j번 사용 집합) 사칙연산 (i-j번 사용 집합) 의 결과들입니다.
            for (int j = 1; j < i; j++) {
                int k = i - j; // 나머지 횟수
                
                for (int num1 : dp[j]) {
                    for (int num2 : dp[k]) {
                        dp[i].add(num1 + num2);
                        dp[i].add(num1 - num2);
                        dp[i].add(num1 * num2);
                        if (num2 != 0) dp[i].add(num1 / num2);
                    }
                }
            }

            // 이번 횟수(i)로 number를 만들 수 있는지 확인
            if (dp[i].contains(number)) {
                return i;
            }
        }

        // 8번을 넘어가면 -1
        return -1;
    }
}