
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// N과 M 9번
public class Main {

    static ArrayList<int[]> set;
    static int[] comb, input;
    static int N, K;
    static StringBuilder sb;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        set = new ArrayList<>();
        N = sc.nextInt();
        K = sc.nextInt();
        comb = new int[K];
        input = new int[N];
        visited = new boolean[N];
        sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++) {
            input[i] = sc.nextInt();
        }

        Arrays.sort(input);
        recur(0);
        System.out.println(sb);
    }

    private static void recur(int depth) {
        if(depth == K) {
            // 이미 만들어진 같은 내용의 순열이 있는지 검사
            boolean diff = true;
            for (int[] ints : set) {
                boolean same = true;
                for(int i = 0 ; i < K ; i++){
                    if(ints[i] != comb[i]){
                        same = false;
                        break;
                    }
                }
                if(same) diff = false;
            }

            // 새롭게 탄생한 순열이라면
            if(diff){
                set.add(Arrays.copyOf(comb, comb.length));
                for (int i : comb) {
                    sb.append(i).append(" ");
                }
                sb.append("\n");
            }
        }

        // K개로 구성된 조합 만들기
        else {
            for(int i = 0 ; i < N ; i++) {
                if(visited[i]) continue;
                comb[depth] = input[i];
                visited[i] = true;
                recur(depth + 1);
                visited[i] = false;
                comb[depth] = 0;
            }
        }

    }


}
