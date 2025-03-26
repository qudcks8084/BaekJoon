import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static boolean[][] p;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 1. 배열을 3개 만든다 
		 * 		- p[][] : 각 파티별로의 저장
		 * 		- S[] : 소문을 알고있는 사람의 배열
		 * 2. 각각의 입력값에 대해서 Union을 설정
		 * 3. 만들어진 Union에 대해서 HashSet에 전부 넣기
		 * 4. S를 이용해서 알고 있는 사람의 Union을 Set에서 제외
		 * 5. Set의 크기를 출력
		 * */
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 사람이 파티에 저장되어있는지 저장
		p = new boolean[N][M];
		
		// 진실을 알고있는 사람을 저장
		ArrayDeque<Integer> q = new ArrayDeque<>();
				
		st = new StringTokenizer(br.readLine());
		int numOfPersonWhoKnowLie = Integer.parseInt(st.nextToken());
		for(int i = 0 ; i < numOfPersonWhoKnowLie ; i++) {
			q.offer(Integer.parseInt(st.nextToken()) - 1);
		}
		
		// Union 설정
		for(int i = 0 ; i < M ; i++){
			st = new StringTokenizer(br.readLine());
			int numOfPersonInParty = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < numOfPersonInParty ; j++) {
				p[Integer.parseInt(st.nextToken())-1][i] = true;
			}
		}
		
		HashSet<Integer> party = new HashSet<>();
		for(int i = 0 ; i < M ; i++) {
			party.add(i);
		}
		
		// s를 기준으로 진실을 아는 사람들을 전파
		// 파티를 기준으로 나랑 같은 곳에 속한 사람들을 계속 탐색
		while(!q.isEmpty()) {
			int cur = q.poll();
			// 그 사람이 포함된 파티를 전부 제외
			for(int i = 0 ; i < M ; i++) {
				if(p[cur][i] && party.contains(i)) {
					party.remove(i);
					// 해당 파티에 있는 다른 사람들을 추가
					for(int j = 0 ; j < N ; j++) {
						if(p[j][i]) q.offer(j);
					}
				} 
			}
		}
	
		System.out.println(party.size());
	
	}
}
