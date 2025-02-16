import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int[] comb, least;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 빠른 숫자 전환을 위한 데이터 삽입
        int[] target = new int[]{0, 0, 1, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0};

        int S = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] input_num = new int[S];
        char[] input = br.readLine().toCharArray();
        for(int i = 0 ; i < S; i++){
            input_num[i] = input[i] - 'A';
        }


        least = new int[4];
        comb = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 4 ; i++){
            least[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;

        // 먼저 부분 수열을 만들어
        for(int i = 0 ; i < T ; i++){
            comb[target[input_num[i]]]++;
        }
        check();
        for(int i = 0 ; i < S-T ; i++){
            comb[target[input_num[i]]]--;
            comb[target[input_num[i+T]]]++;
            check();
        }

        System.out.println(answer);

    }

    public static void check(){
        boolean possible = true;
        for (int i = 0; i < 4; i++) {
            if(least[i] > comb[i]) possible = false;
        }
        if(possible){
            answer++;
        }
    }


}
