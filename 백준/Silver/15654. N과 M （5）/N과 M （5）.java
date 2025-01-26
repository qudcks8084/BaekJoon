import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr, nums;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        arr = new int[n];
        visited = new boolean[n];
        nums = new int[m];

        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

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
                if(visited[i]) continue;
                visited[i] = true;
                nums[depth] = arr[i];
                DFS(depth+1);
                nums[depth] = 0;
                visited[i] = false;
            }
        }

    }
}
