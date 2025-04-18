import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] doll = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            doll[i] = st.nextToken().equals("1");
        }

        int max = Integer.MAX_VALUE / 10;
        int cnt = 0;
        int lp = -2;
        int rp = -1;
        while (rp > lp){
            if(cnt >= K){ // 라이언의 개수가 K와 같은 경우 -> lp를 ++;
                max = Math.min(max, rp - lp + 1);
                if(lp > -1 && doll[lp]){
                    cnt--;
                }
                lp++;
            }else{
                if(rp < N-1){
                    if(doll[rp + 1]) {
                        cnt++;
                    }
                    rp++;
                }else{
                    break;
                }

            }
        }

        if(max == Integer.MAX_VALUE / 10) System.out.println(-1);
        else System.out.println(max);

    }
}
