
import java.util.Scanner;

public class Main {

    static char[][] map;
    static int n, width;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int width = 4 * (n-1) + 1;

        // 초기화
        map = new char[width][width];
        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < width ; j++) {
                map[i][j] = ' ';
            }
        }

        Square( n, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < width ; i++){
            for(int j = 0 ; j < width ; j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void Square(int depth, int x){
        if(depth == 1){
            map[x][x] = '*';
            return;
        }
        int size = 4 * (depth-1) + 1;
        for(int i = x ; i < x+size ; i++){
            map[i][x] = '*';
            map[i][x+size-1] = '*';
            map[x][i] = '*';
            map[x+size - 1][i] = '*';
        }
        Square(depth -1, x+2);
    }
}
