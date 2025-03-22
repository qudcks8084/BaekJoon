import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N = 10;
    static int minPaper;
    static int M;
    static ArrayList<int[]> blank;
    static int[] dn = {5, 4, 3, 2, 1};
    static int[] stock = {5, 5, 5, 5, 5};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[][] map = new boolean[N][N];

        blank = new ArrayList<>();

        for (int c = 0; c < N; c++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                boolean flag = st.nextToken().equals("1");
                if(flag){
                    blank.add(new int[]{c, r});
                    map[c][r] = true;
                }
            }
        }

        if(blank.isEmpty()) {
            System.out.println(0);
            return;
        }

        M = blank.size();
        minPaper = Integer.MAX_VALUE/10;
        dfs(0, 0, map);

        if(minPaper == Integer.MAX_VALUE/10) System.out.println(-1);
        else System.out.println(minPaper);

    }

    static void dfs(int depth, int numOfPaper, boolean[][] map){

        if(depth == M){
            minPaper = Math.min(minPaper, numOfPaper);
            return;
        }

        int[] cur = blank.get(depth);
        int sc = cur[0];
        int sr = cur[1];

        if(!map[sc][sr]){ // 이미 색종이로 채워진 경우
            dfs(depth + 1, numOfPaper, map);
            return;
        }

        for(int i = 0 ; i < 5 ; i++){
            if(stock[i] == 0) continue;
            int d = dn[i];
            if (check(sc, sr, d, map)) {
                stock[i]--;
                boolean[][] next = new boolean[N][N];
                for(int c = 0 ; c < N ; c++){
                    next[c] = Arrays.copyOf(map[c], N);
                }

                for(int c = sc ; c < sc+d ; c++){
                    for (int r = sr; r < sr + d; r++) {
                        next[c][r] = false;
                    }
                }

                dfs(depth + 1, numOfPaper + 1, next);
                stock[i]++;
            }

        }
    }

    static boolean check(int sc, int sr, int d, boolean[][] map){
        for(int c = sc ; c < sc+d ; c++){
            for (int r = sr; r < sr + d; r++) {
                if(c < 0 || c >= N || r < 0 || r >= N) return false;
                if(!map[c][r]) return false;
            }
        }
        return true;
    }
}
