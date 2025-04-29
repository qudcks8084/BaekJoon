import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] sec = {1, 2, 3, 1, 2, 3};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ":");
        int min = Integer.parseInt(st.nextToken());
        System.out.println( min / 10 + min % 10 + sec[Integer.parseInt(st.nextToken()) / 10]);
    }
}
