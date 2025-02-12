import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N = 9;
    static int[] dwarfs;
    static int[] comb;
    static StringBuilder sb;
    static boolean find;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        dwarfs = new int[N];
        comb = new int[7];
        for(int i = 0 ; i < N ; i++){
            dwarfs[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(dwarfs);
        comb(0, 0, 0);
        System.out.println(sb);
    }

    public static void comb(int depth, int start ,int sum){
        if(sum > 100) return;
        if(find) return;
        if(depth == 7){
            if(sum == 100){
                for (int i : comb) {
                    sb.append(i).append("\n");
                }
                find = true;
            }
        }else{
            for(int i = start ; i < N ; i++){
                comb[depth] = dwarfs[i];
                comb(depth + 1, i + 1, sum + dwarfs[i]);
            }
        }
    }
}
