import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int[] dc = {0, 1, 0, -1};
    static int[] dr = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = N * N;

        // 1. 일단 빈 영역을 찾는다.
        int[][] empty = new int[N][N];
        Arrays.fill(empty[0], 3);
        Arrays.fill(empty[N - 1], 3);
        for(int i = 1 ; i < N-1 ; i++){
            Arrays.fill(empty[i], 4);
        }

        for(int i = 0 ; i < N ; i++){
            empty[i][0]--;
            empty[i][N-1]--;
        }

        // 2. 학생을 입력받기 - HashSet 사용
        HashSet<Integer>[] favoriteSet = new HashSet[M+1];
        int[] order = new int[M];

        for(int i = 1 ; i <= M ; i++){
            favoriteSet[i] = new HashSet<>();
        }

        for(int i = 0 ; i < M ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int studentNum = Integer.parseInt(st.nextToken());
            order[i] = studentNum;

            for(int j = 0 ; j < 4 ; j++){
                favoriteSet[studentNum].add(Integer.parseInt(st.nextToken()));
            }
        }

        int[][] classroom = new int[N][N];

        // 3. 각 순서에 맞춰서 학생 배치
        for(int i = 0 ; i < M ; i++){
            int studentNum = order[i];
            int maxLike = -1;
            int maxEmpty = -1;
            int bestCol = 0;
            int bestRow = 0;

            for(int c = 0 ; c < N ; c++){
                for(int r = 0 ; r < N ; r++){

                    if(classroom[r][c] != 0) continue;

                    int likeCnt = 0;

                    for(int d = 0 ; d < 4 ; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(nr >= 0 && nr < N && nc >= 0 && nc < N && classroom[nr][nc] != 0){
                            if(favoriteSet[studentNum].contains(classroom[nr][nc])){
                                likeCnt++;
                            }
                        }
                    }

                    int emptyCnt = empty[r][c];

                    if(likeCnt > maxLike ||
                            (likeCnt == maxLike && emptyCnt > maxEmpty) ||
                            (likeCnt == maxLike && emptyCnt == maxEmpty && (r < bestRow || (r == bestRow && c < bestCol)))){
                        maxLike = likeCnt;
                        maxEmpty = emptyCnt;
                        bestCol = c;
                        bestRow = r;
                    }
                }
            }

            classroom[bestRow][bestCol] = studentNum;

            for(int d = 0 ; d < 4 ; d++){
                int nr = bestRow + dr[d];
                int nc = bestCol + dc[d];

                if(nr >= 0 && nr < N && nc >= 0 && nc < N && classroom[nr][nc] == 0){
                    empty[nr][nc]--;
                }
            }
        }

        // 4. 만족도 계산
        int result = 0;

        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ; r++){
                int studentNum = classroom[r][c];
                int likeCnt = 0;

                for(int d = 0 ; d < 4 ; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(nr >= 0 && nr < N && nc >= 0 && nc < N && classroom[nr][nc] != 0){
                        if(favoriteSet[studentNum].contains(classroom[nr][nc])){
                            likeCnt++;
                        }
                    }
                }

                if(likeCnt == 1) result += 1;
                else if(likeCnt == 2) result += 10;
                else if(likeCnt == 3) result += 100;
                else if(likeCnt == 4) result += 1000;
            }
        }

        System.out.println(result);
    }
}