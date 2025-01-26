import java.util.Scanner;

public class Main {
    static char[][] map;
    static int n, max_x, max_y;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        max_x = 4 * (n - 1) + 1;
        max_y = 3 + 4*(n-1);

        if( n != 1){
            map = new char[max_x][max_y];

            for(int i = 0 ; i < max_y ; i++){
                for(int j = 0 ; j < max_x ; j++){
                    map[j][i] = ' ';
                }
            }

            Square(n, max_x, 0);

            StringBuilder sb = new StringBuilder();
            for(int i = 0 ; i < max_y ; i++){
                StringBuilder line = new StringBuilder();
                for(int j = 0 ; j < max_x ; j++){
                    line.append(map[j][i]);
                }
                sb.append(line.toString().stripTrailing()).append("\n"); // 오른쪽 공백 제거
            }

            System.out.println(sb);

        }else{
            System.out.println("*");
        }
    }

    static void Square(int depth, int x, int y){
        if(depth == 1){
            // 왼쪽
            while(true){
                int n_x = --x;
                if( n_x-2 >= 0 && map[n_x-2][y] =='*'){
                    map[n_x][y] = '*';
                    break;
                }
                if(n_x == 0){
                    map[n_x][y] = '*';
                    break;
                }
                if(n_x > 0){
                    map[n_x][y] = '*';
                }
            }

            // 아래
            while(true){
                int n_y = ++y;
                if (n_y + 2 < max_y && map[x][n_y + 2] == '*') {
                    map[x][n_y] = '*';
                    break;
                }
                if(n_y == max_y - 1){
                    map[x][n_y] = '*';
                    break;
                }
                if(n_y < max_y){
                    map[x][n_y] = '*';
                }
            }

            return;
        }
        // 왼쪽
        while(true){
            int n_x = --x;
            if( n_x-2 >= 0 && map[n_x-2][y] =='*'){
                map[n_x][y] = '*';
                break;
            }
            if(n_x == 0){
                map[n_x][y] = '*';
                break;
            }
            if(n_x > 0){
                map[n_x][y] = '*';
            }
        }

        // 아래
        while(true){
            int n_y = ++y;
            if (n_y + 2 < max_y && map[x][n_y + 2] == '*') {
                map[x][n_y] = '*';
                break;
            }
            if(n_y == max_y - 1){
                map[x][n_y] = '*';
                break;
            }
            if(n_y < max_y){
                map[x][n_y] = '*';
            }
        }

        // 오른쪽
        while(true){
            int n_x = ++x;
            if( n_x+2 < max_x && map[n_x+2][y] =='*'){
                map[n_x][y] = '*';
                break;
            }
            if(n_x == max_x-1){
                map[n_x][y] = '*';
                break;
            }
            if(n_x < max_x-1){
                map[n_x][y] = '*';
            }
        }

        // 위쪽
        while(true){
            int n_y = --y;
            if( n_y-2 >= 0 && map[x][n_y-2] =='*'){
                map[x][n_y] = '*';
                break;
            }
            if( n_y == 0){
                map[x][n_y] = '*';
                break;
            }
            if(n_y > 0){
                map[x][n_y] = '*';
            }
        }

        Square(depth - 1, x, y);

    }
}
