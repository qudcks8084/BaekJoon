import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class TrieNode{
        int numOfNickName;
        TrieNode[] children;

        public TrieNode() {
            this.numOfNickName = 0;
            this.children = new TrieNode[26];
        }
    }

    static class Trie{
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public String insert(String nickname){
            StringBuilder sb = new StringBuilder();

            // 삽입의 시작은 root 부터
            TrieNode cur = root;

            boolean findNickName = false;
            for(int i = 0 ; i < nickname.length() ; i++){
                int next = nickname.charAt(i) - 'a';

                // 이름은 현재 겹치지 않은 가장 짧은 문자열까지
                if(cur.children[next] == null){
                    if(!findNickName) sb.append(nickname.charAt(i));
                    findNickName = true;
                    cur.children[next] = new TrieNode();
                }

                if(!findNickName) sb.append(nickname.charAt(i));

                cur = cur.children[next];
            }

            // 전부 동일하다면
            if(!findNickName && cur.numOfNickName > 0) sb.append(cur.numOfNickName + 1);

            cur.numOfNickName++;

            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        for(int i = 0 ; i < N ; i++){
            sb.append(trie.insert(br.readLine())).append("\n");
        }

        System.out.println(sb);

    }
}
