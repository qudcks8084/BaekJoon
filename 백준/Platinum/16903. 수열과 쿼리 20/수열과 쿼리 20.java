
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    /* XOR 연산
     * 11 0
     * 00 0
     * 10 1
     * 01 1
     * */

    static class TrieNode{
        int numOfWord;
        TrieNode[] children;

        public TrieNode() {
            this.numOfWord = 0;
            this.children = new TrieNode[2]; // 0과 1로 저장
        }
    }

    static class Trie{
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(int num) {
            // 비트마스킹을 이용해서 32자리
            TrieNode cur = root;

            for(int i = 30 ; i >= 0 ; i--){
                int bit = (num >> i) & 1;

                if(cur.children[bit] == null){
                    cur.children[bit] = new TrieNode();
                }

                cur = cur.children[bit];
                cur.numOfWord++;
            }
        }

        public void remove(int num) {
            TrieNode cur = root;

            for(int i = 30 ; i >= 0 ; i--){
                int bit = (num >> i) & 1;

                // 1개만 있는 경우에는 아예 리턴
                if(cur.children[bit].numOfWord == 1){
                    cur.children[bit] = null;
                    return;
                }

                // 1개보다 단어가 더 많은 경우
                cur = cur.children[bit];
                cur.numOfWord--;
            }
        }

        // XOR에서 가장 큰 결과가 나오려면 현재 숫자와 반대되는 숫자로 이동하면 된다.
        public int xorQuery(int num) {
            TrieNode cur = root;
            int answer = 0;

            for(int i = 30 ; i >= 0 ; i--) {
                int bit = (num >> i) & 1;
                int reverse_bit = 1 - bit;

                // 반대로 갈 수 있는지 본다.
                if (cur.children[reverse_bit] != null && cur.children[reverse_bit].numOfWord > 0) {
                    cur = cur.children[reverse_bit];
                    answer |= (1 << i);
                } else if (cur.children[bit] != null && cur.children[bit].numOfWord > 0) {
                    cur = cur.children[bit];
                } else break;
            }

            return answer;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());

        Trie trie = new Trie();
        trie.insert(0);

        for(int i = 0 ; i < M ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            switch (command) {
                case 1 : {
                    trie.insert(target);
                    break;
                }
                case 2 : {
                    trie.remove(target);
                    break;
                }
                case 3 : {
                    sb.append(trie.xorQuery(target)).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb);

    }
}
