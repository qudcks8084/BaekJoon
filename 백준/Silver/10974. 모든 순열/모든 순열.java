import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        perm(0, 0, new StringBuilder());

    }

    public static void perm(int depth, int flag , StringBuilder sb){

        if(N == depth){
            System.out.println(sb);
            return;
        }

        for(int i = 0 ; i < N ; i++){
            if((flag & (1 << i)) > 0) continue;
            StringBuilder next = new StringBuilder();
            next.append(sb.toString()).append(i+1).append(" ");
            perm(depth + 1, flag | (1 << i), next);
        }
    }
}
