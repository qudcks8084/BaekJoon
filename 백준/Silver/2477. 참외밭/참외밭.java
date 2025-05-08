import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int[] dirs = new int[6];
        int[] lens = new int[6];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            dirs[i] = Integer.parseInt(st.nextToken());
            lens[i] = Integer.parseInt(st.nextToken());
        }

        int[] cnt = new int[5];
        for (int dir : dirs) {
            cnt[dir]++;
        }

        int max_W = 0;
        int max_H = 0;
        int idx_W = 0;
        int idx_H = 0;

        for (int i = 0; i < 6; i++) {
            if ((dirs[i] == 1 || dirs[i] == 2) && cnt[dirs[i]] == 1) {
                max_W = lens[i];
                idx_W = i;
            } else if ((dirs[i] == 3 || dirs[i] == 4) && cnt[dirs[i]] == 1) {
                max_H = lens[i];
                idx_H = i;
            }
        }

        int idx_s_w = (idx_W + 3) % 6;
        int idx_s_h = (idx_H + 3) % 6;

        System.out.println(K * (max_W * max_H - lens[idx_s_w] * lens[idx_s_h]));
    }
}