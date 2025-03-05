import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, V;
    static int minInfectionTime;
    static int numOfBlank;
    static ArrayList<int[]> virusLocations;
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minInfectionTime = Integer.MAX_VALUE;
        
        virusLocations = new ArrayList<>();
        map = new int[N][N];
        
        numOfBlank = 0;
        for(int c = 0; c < N; c++) {
            st = new StringTokenizer(br.readLine());
            for(int r = 0; r < N; r++) {
                int tmp = Integer.parseInt(st.nextToken());
                map[c][r] = tmp;
                if(tmp == 2) virusLocations.add(new int[]{c, r});
                if(tmp == 0) numOfBlank++;
            }
        }
        
        if(numOfBlank == 0) {
            System.out.println(0);
            return;
        }
        
        V = virusLocations.size();
        generateCombinations();
        
        System.out.println(minInfectionTime == Integer.MAX_VALUE ? -1 : minInfectionTime);
    }
    
    public static void generateCombinations() {
        // 비트 연산자를 이용해서 조합을 계산
    	// 0에서부터 (1<<V)까지 즉 V가 5라면 0부터 31까지 모든 경우의 수를 탐색 
    	// Integer.bitCount() static Method : 입력값을 bit로 1이 몇개인지 계산
        for(int i = 0; i < (1 << V); i++) {
            if(Integer.bitCount(i) == M) {
                spreadVirus(i);
            }
        }
    }
    
    static int[] dc = {-1, 0, 1, 0};
    static int[] dr = {0, 1, 0, -1};
    
    public static void spreadVirus(int combination) {
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        
        // 비트연산자를 이용해서 현재 활성화된 바이러스를 확인
        for(int v = 0; v < V; v++) {
            if((combination & (1 << v)) != 0) {
                int[] start = virusLocations.get(v);
                q.offer(start);
                visited[start[0]][start[1]] = true;
            }
        }
        
        int time = 0;
        int infectedSpaces = 0;
        
        while(!q.isEmpty()) {
            if(time >= minInfectionTime) return;
            
            int size = q.size();
            for(int l = 0; l < size; l++) {
                int[] cur = q.poll();
                int c = cur[0], r = cur[1];
                
                for(int i = 0; i < 4; i++) {
                    int nc = c + dc[i];
                    int nr = r + dr[i];
                    
                    if(nc < 0 || nc >= N || nr < 0 || nr >= N || visited[nc][nr]) continue;
                    
                    if(map[nc][nr] == 0) {
                        infectedSpaces++;
                        visited[nc][nr] = true;
                        q.offer(new int[]{nc, nr});
                    } else if(map[nc][nr] == 2) {
                        visited[nc][nr] = true;
                        q.offer(new int[]{nc, nr});
                    }
                }
            }
            
            time++;
            
            if(infectedSpaces == numOfBlank) {
                minInfectionTime = Math.min(time, minInfectionTime);
                return;
            }
        }
    }
}