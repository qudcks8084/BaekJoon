import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if(N == 1) N++;
        int M = sc.nextInt();
        int len = M - N + 1;

        int[] arr = new int[len];

        for(int i = 0 ; i < len ; i++){
            arr[i] = N + i;
        }
        
        for(int i = 2 ; i <= Math.sqrt(M) ; i++){
            for(int j = 0 ; j < len ; j++){
                if(arr[j] == 0) continue;
                if(arr[j] != i && arr[j] % i == 0) arr[j] = 0;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i : arr){
            if(i != 0) sb.append(i).append("\n");
        }
        System.out.println(sb);
    }
}
