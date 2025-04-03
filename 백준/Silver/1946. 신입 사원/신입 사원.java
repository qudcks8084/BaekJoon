import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    static class people {
        int index, document, meeting;

        public people(int index, int document, int meeting) {
            this.index = index;
            this.document = document;
            this.meeting = meeting;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0 ; t < T ; t++){
            int N = Integer.parseInt(br.readLine());

            people[] arr = new people[N];
            for(int i = 0 ; i < N ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int d = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                arr[i] = new people(i, d, m);
            }

            TreeSet<Integer> result = new TreeSet<>();

            // 먼저 서류가 높은거부터 검토
            Arrays.sort(arr, ((o1, o2) -> Integer.compare(o1.document, o2.document)));

            // 위에서부터 검토, 단. 1번째꺼는 무조건 통과
            result.add(arr[0].index);
            int max_meeting = arr[0].meeting;
            for (int i = 1 ; i < N ; i++) {
                if(max_meeting > arr[i].meeting){
                    result.add(arr[i].index);
                    max_meeting = arr[i].meeting;
                }
            }

            sb.append(result.size()).append("\n");
        }

        System.out.println(sb);
    }
}
