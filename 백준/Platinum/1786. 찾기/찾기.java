import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int tLen = text.length;
		int pLen = pattern.length;
		
		// 부분 일치 테이블(실패 함수) 만들기 : 패턴에서 불일치가 발생한 경우 활용해서 패턴 포인터 이동목적
		int[] pi = new int[pLen]; 
		
		// 패턴에서 패턴을 찾는 원리를 이용
		for(int i = 1, j = 0 ; i < pLen ; i++) {
			// i : 패턴의 접미사, j : 패턴의 접두사
			// 두 포인터의 위치에서 불일치가 발생하면 맞은 직전 위치의 정보를 활용해서 불필요한 비교를 줄임
			while(j > 0 && pattern[i] != pattern[j]){
				j = pi[j-1];
			}
			
			// 현재 i위치까지의 부분 문자열의 접미사가 접두사와 일치하는 패턴의 최장길이 저장
			if(pattern[i] == pattern[j]) {
				// j위치까지 일치한 경우 길이는 j+1, 후에 j 뒤로 이동
				pi[i] = ++j; 
			}else {
				// 일치하는 접두사 접미사 없음
				pi[i] = 0; 
			}
			
		}
		
		int cnt = 0;
		ArrayList<Integer> list = new ArrayList<>();
		
		for(int i = 0, j = 0 ; i < tLen ; i++) {
			while(j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			if(text[i] == pattern[j]) {
				if(j == pLen - 1) {
					++cnt; // 완전한 패턴을 찾았음으로 카운트 증가
					list.add(i-j+1);
					j = pi[j];
				}else {
					++j;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append("\n");
		if(cnt > 0) {
			for(int idx : list) sb.append(idx).append("\n");
		}
		System.out.print(sb);
	}
}
