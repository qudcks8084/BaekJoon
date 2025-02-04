import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] teams = new int[2];
		int[] last_win = new int[2];
		int last_team = 0;
		
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			int team = Integer.parseInt(st.nextToken());
			
			StringTokenizer time = new StringTokenizer(st.nextToken(),":");
			
			int hour = Integer.parseInt(time.nextToken());
			int minute = Integer.parseInt(time.nextToken());
			if(last_team > 0) {
				teams[0] += (hour-last_win[0]) * 60;
				teams[0] += minute-last_win[1];
			} else if(last_team < 0) {
				teams[1] += (hour-last_win[0]) * 60;
				teams[1] += minute-last_win[1];
			}
			
			last_win[0] = hour;
			last_win[1] = minute;
			
			if(team == 1) last_team++;
			else last_team--;
			
		}
		
		if(last_team > 0) {
			teams[0] += (48-last_win[0])*60;
			teams[0] += 0-last_win[1];
		} else if(last_team < 0) {
			teams[1] += (48-last_win[0])*60;
			teams[1] += 0-last_win[1];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%02d:%02d\n", (teams[0]/60),(teams[0]%60)));
		sb.append(String.format("%02d:%02d\n", (teams[1]/60),(teams[1]%60)));
		System.out.println(sb);
	}
}
