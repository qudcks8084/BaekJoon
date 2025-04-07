import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class TriNode{
        TriNode[] children;
        boolean isEndOfNumber;

        public TriNode() {
            // 0~9까지의 숫자를 저장할 수 있는 크기의 배열
            this.children = new TriNode[10];
            this.isEndOfNumber = false;
        }
    }

    static class Trie{
        TriNode root;

        public Trie(){
            root = new TriNode();
        }

        // 숫자를 삽입
        public boolean insert(String number){
            TriNode cur = root;
            
            int len = number.length();
            int cnt = 0;

            for(int i = 0 ; i < len ; i++){

                // 해당 자릿수에 해당하는 숫자를 추출
                int digit = number.charAt(i) - '0';

                // 현재 해당 숫자의 노드가 존재하지 않는다면 해당 숫자를 생성
                if(cur.children[digit] == null){
                    cnt++;
                    cur.children[digit] = new TriNode();
                }

                // 이후 자식 노드로 이동
                cur = cur.children[digit];
                if (cur.isEndOfNumber) return true;
//                System.out.println(i + " " + cur.isEndOfNumber);
            }

            // 만약 끝까지 이동했다면 해당 숫자가 끝임을 입력
            cur.isEndOfNumber = true;
            // 오면서 한번도 새로 배열을 만들지 않고 끊났다면 누군가의 접두사라는것
            if(cnt == 0) return true;
            return false;
        }
    }

    // main 메소드 부분
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < T; testcase++) {
            int N = Integer.parseInt(br.readLine());
            Trie trie = new Trie();

            boolean flag = false;
            for(int i = 0; i < N; i++){
                String input = br.readLine();
                if(flag) continue ;
                if(trie.insert(input)){
                    sb.append("NO\n");
                    flag = true;
                }
            }

            if(!flag) sb.append("YES\n");
        }

        System.out.print(sb);
    }
}
