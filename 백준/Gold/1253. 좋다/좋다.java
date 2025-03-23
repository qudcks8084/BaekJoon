import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(number);

        int goodCount = 0;

        // 각 숫자가 다른 두 수의 합으로 표현될 수 있는지 확인
        for(int i = 0; i < N; i++){
            int target = number[i];
            int left = 0;
            int right = N - 1;

            while(left < right){
                // 타겟 숫자 자체는 사용하지 않도록 건너뛰기
                if(left == i){
                    left++;
                    continue;
                }
                if(right == i){
                    right--;
                    continue;
                }

                int sum = number[left] + number[right];

                if(sum == target){
                    goodCount++;
                    break; // 이 타겟에 대한 유효한 쌍을 찾음
                } else if(sum < target){
                    left++;
                } else { // sum > target
                    right--;
                }
            }
        }

        System.out.println(goodCount);
    }
}