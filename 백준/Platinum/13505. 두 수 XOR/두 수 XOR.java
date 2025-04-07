
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /* XOR 연산
     * 11 0
     * 00 0
     * 10 1
     * 01 1
     * */

    static class TrieNode{
        TrieNode[] children;

        public TrieNode() {
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
                if (cur.children[reverse_bit] != null ) {
                    cur = cur.children[reverse_bit];
                    answer |= (1 << i);
                } else if (cur.children[bit] != null) {
                    cur = cur.children[bit];
                } else break;
            }

            return answer;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Trie trie = new Trie();

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            trie.insert(tmp);
            arr[i] = tmp;
        }

        int max = 0;
        for (int num : arr) {
            max = Math.max(max, trie.xorQuery(num));
        }

        System.out.println(max);

    }
}
