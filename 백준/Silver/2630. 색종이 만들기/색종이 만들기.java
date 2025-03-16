import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    static int N;
    static boolean[][] map;
    static int[] answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];


        for (int c = 0; c < N; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int r = 0; r < N; r++) {
                map[c][r] = st.nextToken().equals("1");
            }
        }

        answer = new int[2];
        find(0, 0, N);
        System.out.println(answer[0]);
        System.out.println(answer[1]);

    }

    public static void find(int c, int r, int size){
        if(count(c, r, size)) {
            if (map[c][r]) answer[1]++;
            else answer[0]++;
        }else{
            int n_size = size / 2;
            find(c, r, n_size);
            find(c + n_size, r, n_size);
            find(c, r + n_size, n_size);
            find(c + n_size, r + n_size, n_size);
        }
    }

    public static boolean count(int nc, int nr, int size){
        boolean target = map[nc][nr];
        for(int c = nc ; c < nc + size ; c++){
            for (int r = nr; r < nr + size; r++) {
                if(map[c][r] != target){ // map[nc][nr]과 값이 하나라도 다르면 실패
                    return false;
                }
            }
        }
        return true;
    }
}
