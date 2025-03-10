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
        public void insert(String number){
            TriNode cur = root;

            for(int i = 0 ; i < number.length() ; i++){

                // 해당 자릿수에 해당하는 숫자를 추출
                int digit = number.charAt(i) - '0';

                // 현재 해당 숫자의 노드가 존재하지 않는다면 해당 숫자를 생성
                if(cur.children[digit] == null){
                    cur.children[digit] = new TriNode();
                }

                // 이후 자식 노드로 이동
                cur = cur.children[digit];
            }

            // 만약 끝까지 이동했다면 해당 숫자가 끝임을 입력
            cur.isEndOfNumber = true;
        }

        // 해당 문자열로 시작하는 문자열이 있는지 검색
        // 만일 null이 아니라면 해당 노드가 생성되어있음을 의미
        public boolean startsWith(String prefix){
            TriNode node = getNode(prefix);
            if(node == null) return false;

            for(TriNode next : node.children){
                if(next != null) return true;
            }

            return false;
        }

        // 특정 문자열에 해당하는 노드 찾기
        public TriNode getNode(String str){
            TriNode cur = root;

            for(int i = 0 ; i < str.length() ; i++){
                int digit = str.charAt(i) - '0';

                if(cur.children[digit] == null) return null;

                cur = cur.children[digit];
            }

            return cur;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        test : for (int testcase = 0; testcase < T; testcase++) {

            int N = Integer.parseInt(br.readLine());
            String[] input = new String[N];
            Trie trie = new Trie();

            for(int i = 0 ; i < N ; i++){
                String tmp = br.readLine();
                trie.insert(tmp);
                input[i] = tmp;
            }

            for (String str : input) {
                if(trie.startsWith(str)){
                    sb.append("NO\n");
                    continue test;
                }
            }

            sb.append("YES\n");

        }

        System.out.println(sb);

    }
}
