import java.util.Scanner;

public class Main {
	static char[][] map;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        map = new char[n][n];
        
        print(n, 0, 0);
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n ; i++) {
        	for(int j = 0 ; j < n ; j++) {
        		if(map[i][j] != '*' && map[i][j] != ' ') sb.append(" ");
        		else sb.append(map[i][j]);
        	}
        	sb.append("\n");
        }
        System.out.println(sb);
    }
    
    private static void print(int n, int x, int y) {
    	if(n == 3) 
    		for(int i = 0 ; i < 3 ; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					if(!(i == 1 && j == 1)) map[x+i][y+j] = '*';
					else map[x+i][y+j] = ' ';
				}
			}
		else {
			for(int i = 0 ; i < 3 ; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					if(!(i == 1 && j == 1)) print(n/3, x+n/3*i, y+n/3*j);
				}
			}
		}
	}
}
