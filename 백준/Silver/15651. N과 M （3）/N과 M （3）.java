import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n, m;
    static int[] arr, nums;
    static StringBuilder sb;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        nums = new int[m];

        for(int i = 0 ; i < n ; i++){
            arr[i] = i + 1;
        }

        sb = new StringBuilder();
        DFS(0);
        System.out.println(sb);
    }

    public static void DFS(int depth){
        if(depth == m){
            for (int num : nums) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }else{
            for(int i = 0 ; i < n ; i++){
                nums[depth] = arr[i];
                DFS(depth+1);
                nums[depth] = 0;
            }
        }

    }
}
