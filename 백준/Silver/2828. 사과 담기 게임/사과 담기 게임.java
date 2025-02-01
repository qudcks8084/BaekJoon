import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int A = sc.nextInt();

        int answer = 0;

        // 바구니의 크기가 1인 경우
        if(M == 1){
            int basket = 1; // 바구니 초기값 = 0;
            for(int i = 0 ; i < A ; i++){
                int apple = sc.nextInt();
                answer += Math.abs(apple - basket); // 사과 위치로 이동
                basket = apple; // 사과 위치에 바구니 위치를 초기화
            }
        }
        else { // 바구니의 크기가 1 이 아닌 경우
            int left = 1;
            int right = left + M - 1;
            for(int i = 0 ; i < A ; i++){
                int apple = sc.nextInt();
                if (apple > right) { // 바구니를 오른쪽으로 이동해야하는 경우
                    int gap = Math.abs(apple - right);
                    answer += gap; // 사과 위치로 이동
                    right += gap;
                    left += gap;
                } else if (apple < left) { // 바구니를 왼쪽으로 이동해야하는 경우
                    int gap = Math.abs(apple - left);
                    answer += gap; // 사과 위치로 이동
                    right -= gap;
                    left -= gap;
                }
            }
        }

        System.out.println(answer);
    }
}
