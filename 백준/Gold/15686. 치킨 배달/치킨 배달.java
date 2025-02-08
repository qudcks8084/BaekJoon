import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M, answer;
    static ArrayList<int[]> chickens;
    static ArrayList<int[]> houses;
    static int[][] selected_chicken;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selected_chicken = new int[M][2];
        chickens = new ArrayList<>();
        houses = new ArrayList<>();

        // 치킨과 집들을 찾아 배열로 관리
        for(int c = 0 ; c < N ; c++){
            st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                int now = Integer.parseInt(st.nextToken());
                if(now == 2) chickens.add(new int[]{c, r});
                if(now == 1) houses.add(new int[]{c, r});
            }
        }

        answer = Integer.MAX_VALUE;
        DFS(0, 0);
        System.out.println(answer);

    }

    public static void DFS(int depth, int start){
        if(depth == M){ // 치킨집 조합이 완성된 경우
            int min_chicken_distance = 0;
            for (int[] house : houses) {
                int min = Integer.MAX_VALUE;
                for(int[] chicken : selected_chicken){
                    min = Math.min(min, getDistance(house, chicken));
                }
                min_chicken_distance += min;
            }
            answer = Math.min(answer, min_chicken_distance);
        }else{
            for(int i = start ; i < chickens.size() ; i++){
                int[] next = chickens.get(i);
                selected_chicken[depth] = next;
                DFS(depth + 1, i + 1);
                selected_chicken[depth] = null;
            }
        }
    }

    public static int getDistance(int[] chicken, int[] house){
        return Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
    }

}
