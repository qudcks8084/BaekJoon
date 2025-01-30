import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean map[][];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) map[i][j] = true;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]){
                    make(i, j);
                }

            }
        }


        print();

    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]) sb.append(1);
                else sb.append(0);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void make(int origin, int start){
        for(int i = 0 ; i < N ; i++){
            if(map[start][i] && !map[origin][i]){
                map[origin][i] = true;
                make(origin, i);
            }
        }
    }
}
