import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static StringBuilder sb;
    static int N, M;
    static char[] input, comb;
    static HashSet<Character> moeum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new char[M];
        comb = new char[N];

        moeum = new HashSet<>();
        char[] moeums = new char[]{'a', 'i', 'u', 'e', 'o'};
        for (char c : moeums) {
            moeum.add(c);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < M ; i++){
            input[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(input);

        DFS(0, 0);
        System.out.println(sb);
    }

    public static void DFS(int depth, int start){
        if(depth == N){
            int numOfMoeum = 0;
            StringBuilder line = new StringBuilder();
            for (char c : comb) {
                if(moeum.contains(c)) numOfMoeum++;
                line.append(c);
            }
//            System.out.println(Arrays.toString(comb) + " 모음의 개수 " + numOfMoeum + " 자음의 개수 " + (N - numOfMoeum));
            if (numOfMoeum > 0 && N - numOfMoeum > 1) {
                sb.append(line).append("\n");
            }
        }else{
            for(int i = start ; i < M ; i++){
                comb[depth] = input[i];
                DFS(depth + 1, i + 1);
                comb[depth] = ' ';
            }
        }
    }
}
