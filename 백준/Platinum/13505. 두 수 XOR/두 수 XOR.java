import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static final int MAX = 30;

    public static class TrieNode{
        boolean isLeaf;
        TrieNode[] children;

        public TrieNode() {
            this.isLeaf = false;
            this.children = new TrieNode[2];
        }
    }

    public static class Trie{
        int max;
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(int num){
            TrieNode cur = root;

            for(int i = MAX ; i >= 0 ; i--){
                int index = (num >> i) & 1;

                if(cur.children[index] == null){
                    cur.children[index] = new TrieNode();
                }

                cur = cur.children[index];
            }

            cur.isLeaf = true;
        }

        /*
        * 1. 앞에서부터 탐색
        * 2. 2개로 갈라지는지 검사 -> 갈라지면 분기
        * */
        public int findMax(){
            max = 0;
            find(MAX, 0, root, root);
            return max;
        }

        public void find(int depth, int num, TrieNode left, TrieNode right) {

//            System.out.println(depth + " " + num);
            if(depth == -1){
                max = Math.max(max, num);
                return;
            }

            boolean flag = false;
            // 1 0 or 0 1 인지 검사하고 맞으면 탐색
            if(left.children[0] != null && right.children[1] != null){
                find(depth - 1, num | (1 << depth), left.children[0], right.children[1]);
                flag = true;
            }
            if(left.children[1] != null && right.children[0] != null){
                find(depth - 1, num | (1 << depth), left.children[1], right.children[0]);
                flag = true;
            }
            if(flag) return; // 만약 위가 존재한다면 아래는 더 클 수 없음
            if(left.children[0] != null && right.children[0] != null){
                find(depth - 1, num , left.children[0], right.children[0]);
            }
            if(left.children[1] != null && right.children[1] != null){
                find(depth - 1, num, left.children[1], right.children[1]);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Trie trie = new Trie();
        for(int i = 0 ; i < N ; i++){
            trie.insert(Integer.parseInt(st.nextToken()));
        }

        System.out.println(trie.findMax());

    }
}
