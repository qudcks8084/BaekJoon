import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class TrieNode{
        boolean isEndOfWord; // 현재 단계에서 끝나는 단어가 있는지 저장
        int numOfChild; // 현재 몇개의 자식이 활성화 되어있는지를 저장
        int numOfWord; // 몇개의 자식, 즉 몇개의 분기점을 가지고 있는지 저장
        TrieNode[] children;

        public TrieNode() {
            this.isEndOfWord = false;
            this.numOfChild = 0;
            children = new TrieNode[26];
        }
    }

    static class Trie{
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        void insert(String target){
            TrieNode cur = root;

            int len = target.length();
            for(int l = 0 ; l < len ; l++){
                int next = target.charAt(l) - 'a';

                if(cur.children[next] == null) {
                    cur.children[next] = new TrieNode();
                    cur.numOfChild++;
                }

                cur = cur.children[next];
                cur.numOfWord++;
            }

            cur.isEndOfWord = true;

        }

        void find_answer(TrieNode cur){
            if(cur.numOfChild > 1){
                answer += cur.numOfWord;
                if(cur.isEndOfWord) answer--;
            }

            else if(cur.numOfChild == 1 && cur.numOfWord > 1 && cur.isEndOfWord){
                answer += cur.numOfWord - 1;
            }

            for(int i = 0 ; i < 26 ; i++){
                if(cur.children[i] != null){
                    find_answer(cur.children[i]);
                }
            }
        }
    }


    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null && !input.isEmpty()) { // 입력이 null 또는 빈 문자열이 아닐 때 반복
            Trie trie = new Trie();
            int N = Integer.parseInt(input);
            String[] backup = new String[N];

            for (int i = 0; i < N; i++) {
                String tmp = br.readLine();
                trie.insert(tmp);
                backup[i] = tmp;
            }

            answer = N;

            for(int i = 0 ; i < 26 ; i++){
                if(trie.root.children[i] != null){
                    trie.find_answer(trie.root.children[i]);
                }
            }

            System.out.printf("%.2f%n", (double) answer / N);

        }


    }
}
