import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        int[] cnt = new int[8001];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0 ; i < N ; i++) {
            int target = Integer.parseInt(br.readLine());
            sum += target;
            min = Math.min(min, target);
            max = Math.max(max, target);
            cnt[(target+4000)]++;
        }

        long average = Math.round((double) sum / (double) N);

        int cnt_max = 0;
        for(int i = min ; i <= max ; i++) {
            cnt_max = Math.max(cnt_max, cnt[i + 4000]);
        }

        int cnt_max_num = 0;
        boolean hasFirstAlready = false;
        for(int i = min ; i <= max ; i++) {
            int tmp = cnt[i+4000];
            if(tmp == cnt_max) {
                if(hasFirstAlready) {
                    cnt_max_num = i;
                    break;
                }

                cnt_max_num = i;
                hasFirstAlready = true;
            }
        }

        int mid = N/2+1;
        int middle_num = 0;
        for(int i = min ; i <= max ; i++) {
            int tmp = cnt[i+4000];
            if(tmp >= mid) {
                middle_num = i;
                break;
            }else {
                mid -= tmp;
            }
        }

        StringBuilder sb = new StringBuilder();
//        sb.append("산술평균 : ");
        sb.append(average).append("\n");
//        sb.append("중앙값 : ");
        sb.append(middle_num).append("\n");
//        sb.append("최빈값 : ");
        sb.append(cnt_max_num).append("\n");
//        sb.append("범위 : ");
        sb.append((max - min)).append("\n");
        System.out.println(sb);
    }

}
