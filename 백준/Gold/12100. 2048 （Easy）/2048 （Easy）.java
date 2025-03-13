import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int R = 5;
    static int N;
    static int max;
    static int[] p;
    static int[][] map;
    static int[][] input;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N][N];

        for(int c = 0 ; c < N ; c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int r = 0 ; r < N ; r++){
                input[c][r] = Integer.parseInt(st.nextToken());
            }
        }

        p = new int[R];
        max = 0;

        perm(0);

        System.out.println(max);

    }

    public static void perm(int depth){
        if(depth == R){
            // 배열을 복사
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.copyOf(input[i], N);
            }
            for(int d : p) move(d);
            findMAX();
            return;
        }

        for(int i = 0 ; i < 4 ; i++){
            p[depth] = i;
            perm(depth + 1);
        }
    }

    public static void move(int d){
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        if(d == 0){ // 아래에서 위로
            // 일단 스택에 다 넣어
            for(int r = 0 ; r < N ; r++){
                for (int c = N - 1; c >= 0; c--) {
                    if(map[c][r] != 0){
                        if(stack.isEmpty()) stack.offer(map[c][r]);
                        else {
                            if(stack.peekLast() == map[c][r]){
                                stack.offer(stack.removeLast() * 2);
                                stack.offer(0);
                            }else{
                                stack.offer(map[c][r]);
                            }
                        }
                        map[c][r] = 0;
                    }
                }

                int c = N - 1;
                while(!stack.isEmpty()){
                    int num = stack.poll();
                    if(num == 0) continue;
                    map[c--][r] = num;
                }
            }
        }
        else if(d == 1){ // 왼쪽에서 오른쪽
            for(int c = 0 ; c < N ; c++){
                for(int r = 0 ; r < N ; r++){
                    if(map[c][r] != 0){
                        if(stack.isEmpty()) stack.offer(map[c][r]);
                        else {
                            if(stack.peekLast() == map[c][r]){
                                stack.offer(stack.removeLast() * 2);
                                stack.offer(0);
                            }else{
                                stack.offer(map[c][r]);
                            }
                        }
                        map[c][r] = 0;
                    }
                }

                int r = 0;
                while(!stack.isEmpty()){
                    int num = stack.poll();
                    if(num == 0) continue;
                    map[c][r++] = num;
                }
            }
        }
        else if (d == 2) { // 위에서 아래
            for(int r = 0 ; r < N ; r++){
                for (int c = 0 ; c < N; c++) {
                    if(map[c][r] != 0){
                        if(stack.isEmpty()) stack.offer(map[c][r]);
                        else {
                            if(stack.peekLast() == map[c][r]){
                                stack.offer(stack.removeLast() * 2);
                                stack.offer(0);
                            }else{
                                stack.offer(map[c][r]);
                            }
                        }
                        map[c][r] = 0;
                    }
                }

                int c = 0;
                while(!stack.isEmpty()){
                    int num = stack.poll();
                    if(num == 0) continue;
                    map[c++][r] = num;
                }
            }
        }
        else{ // 오른쪽에서 왼쪽으로
            for(int c = 0 ; c < N ; c++){
                for(int r = N-1 ; r >= 0 ; r--){
                    if(map[c][r] != 0){
                        if(stack.isEmpty()) stack.offer(map[c][r]);
                        else {
                            if(stack.peekLast() == map[c][r]){
                                stack.offer(stack.removeLast() * 2);
                                stack.offer(0);
                            }else{
                                stack.offer(map[c][r]);
                            }
                        }
                        map[c][r] = 0;
                    }
                }

                int r = N-1;
                while(!stack.isEmpty()){
                    int num = stack.poll();
                    if(num == 0) continue;
                    map[c][r--] = num;
                }
            }
        }
    }

    public static void findMAX(){
        for(int c = 0 ; c < N ; c++){
            for(int r = 0 ; r < N ;r++){
                max = Math.max(max, map[c][r]);
            }
        }
    }
}
