import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] num;
    static StringBuilder[] sb;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int size = (int)Math.pow(2, N) - 1;

        num = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < size ; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder[N];
        for(int i = 0 ; i < N ; i++){
            sb[i] = new StringBuilder();
        }
        find(0, 0, size - 1);

        StringBuilder answer = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            answer.append(sb[i].toString()).append("\n");
        }
        System.out.println(answer);

    }

    public static void find(int depth, int start, int end){
        if(depth == N) return;
        int mid = (start + end) / 2;
        sb[depth].append(num[mid]).append(" ");

        find(depth + 1, start, mid - 1);
        find(depth + 1, mid + 1, end);
    }
}
