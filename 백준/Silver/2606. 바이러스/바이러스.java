
import java.util.Scanner;

public class Main {
    static boolean connect[][];
    static boolean visited[];
    static int N, Com, answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Com = sc.nextInt();
        N = sc.nextInt();

        connect = new boolean[Com][Com];
        for(int i  = 0 ; i < N ; i++){
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            connect[a][b] = true;
            connect[b][a] = true;
        }

        visited = new boolean[Com];
        DFS(0);

        System.out.println(answer);
    }

    public static void DFS(int com){
        visited[com] = true;
        for(int i = 0 ; i < Com ; i++){
            if(i != com && connect[com][i] && !visited[i]){
                answer++;
                DFS(i);
            }
        }
    }
}
