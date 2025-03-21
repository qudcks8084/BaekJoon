import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;

public class Main{

    // 스도쿠 관련 고정 값
    static int N = 9;
    static int[][] board;

    // 방문 확인 용
    static boolean[][][] square;
    static boolean[][] column;
    static boolean[][] row;

    // 정답 확인용
    static boolean found;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[N][N];
        square = new boolean[3][3][10];
        column = new boolean[N][10];
        row = new boolean[N][10];

        for(int c = 0 ; c < N ; c++){
            char[] line = br.readLine().toCharArray();
            for(int r = 0 ; r < N ; r++){
                int tmp = line[r] - '0';
                if(tmp > 0){ // 이미 정해진 숫자라면 boolean 배열을 초기화
                    board[c][r] = tmp;
                    square[c/3][r/3][tmp] = true;
                    column[c][tmp] = true;
                    row[r][tmp] = true;
                }
            }
        }

        find(0);

    }

    public static void find(int depth){

        // 종료조건 - 이미 정답을 찾았거나, 81개를 모두 찾은 경우
        if(found) return;
        if(depth == 81){
            print();
            found = true;
            return;
        }

        int c = depth / N;
        int r = depth % N;

        // 이미 값이 정해져 있는 경우
        if(board[c][r] > 0) find(depth + 1);
        else{
            for(int i = 1 ; i <= N ; i++){
                // 해당 위치에 해당 숫자를 놓을 수 있는지 검사
                if(!square[c/3][r/3][i] && !row[r][i] && !column[c][i]){
                    square[c/3][r/3][i] = true;
                    row[r][i] = true;
                    column[c][i] = true;
                    board[c][r] = i;
                    find(depth + 1);
                    board[c][r] = 0;
                    square[c/3][r/3][i] = false;
                    row[r][i] = false;
                    column[c][i] = false;
                }
            }
        }

    }

    static void print(){
        StringBuilder sb = new StringBuilder();
        for(int c = 0 ; c < N ; c++){
            for (int r = 0; r < N; r++) {
                sb.append(board[c][r]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
