import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] map;
    static StringBuilder sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 데이터 입력 및 초기화
        int N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        sb = new StringBuilder();

        for(int i = 0 ; i < N ; i++){
            String input = br.readLine();
            for(int j = 0 ; j < N ; j++){
                map[i][j] = input.charAt(j) == '1';
            }
        }

        QuadTree(0, 0, N);

        System.out.println(sb);
    }

    private static void QuadTree(int x, int y, int n) {

        // 내부의 값이 모두 같은지 조사
        boolean wrong = false;
        for(int i = 0 ; i < n ; i++){
            if(wrong) break;
            for(int j = 0; j < n ; j++){
                if(map[x+i][y+j] != map[x][y]) wrong = true;
            }
        }


        // 내부의 값이 통일되지 않는 경우
        if(wrong){
            int next_size = n / 2;
            sb.append("(");
            QuadTree(x,y,n/2);
            QuadTree(x,y+next_size,n/2);
            QuadTree(x + next_size,y,n/2);
            QuadTree(x+next_size,y+next_size,n/2);
            sb.append(")");
        }else{
            if(map[x][y]) sb.append("1");
            else sb.append("0");
        }
    }
}
