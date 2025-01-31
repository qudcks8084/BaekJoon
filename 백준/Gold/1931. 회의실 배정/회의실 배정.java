import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static class Meeting implements Comparable<Meeting>{
        int start, end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meeting o) {
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Meeting> arr = new ArrayList<>();
        StringTokenizer st;
        for(int i =  0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            arr.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(arr);
        int time = 0;
        int answer = 0;
        for (Meeting meeting : arr) {
            if(time <= meeting.start){
                time = meeting.end;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
