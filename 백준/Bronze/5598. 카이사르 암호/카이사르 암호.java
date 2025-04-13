import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : input) {
            sb.append((char)('A' + (((c - 'A') + 23) % 26)));
        }
        System.out.println(sb);
    }

}
