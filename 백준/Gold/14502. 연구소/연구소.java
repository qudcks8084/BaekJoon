import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, extra, min;
    static int[][] map;
    static boolean[][] visitied;
    static ArrayList<Node> virus, blank;
    static Node[] walls;
    static int[] nx = {0, 1, 0, -1};
    static int[] ny = {-1, 0, 1, 0};

    static class Node{
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        virus = new ArrayList<>();
        blank = new ArrayList<>();
        walls = new Node[3];
        min = Integer.MAX_VALUE;

        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                int tmp = Integer.parseInt(st.nextToken());
                map[j][i] = tmp;
                if(tmp == 2) virus.add(new Node(j, i));
                if(tmp == 0) blank.add(new Node(j, i));
            }
        }

        wall(0, 0);

        int answer = blank.size() - 3 - min;
        System.out.println(answer);
    }

    private static void wall(int L, int start) {
        if(L == 3){
            extra = 0;
            visitied = new boolean[M][N];
            for (Node node : virus) {
                check(node.x, node.y);
            }
            min = Math.min(min, extra);
        }else{
            for(int i = start ; i < blank.size() ; i++){
                Node tmp = blank.get(i);
                walls[L] = tmp;
                map[tmp.x][tmp.y] = 1;
                wall(L + 1, i + 1);
                map[tmp.x][tmp.y] = 0;
                walls[L] = null;
            }
        }
    }

    private static void check(int x, int y){
        for(int i = 0 ; i < 4 ; i++){
            int n_x = x + nx[i];
            int n_y = y + ny[i];
            if(n_x < 0 || n_x >= M || n_y < 0 || n_y >= N) continue;
            if(map[n_x][n_y] != 0 || visitied[n_x][n_y]) continue;
            visitied[n_x][n_y] = true;
            extra++;
            check(n_x,n_y);
        }
    }
}
