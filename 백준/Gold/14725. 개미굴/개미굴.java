import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb;

    static class TrieNode{
        String value;
        boolean isLeafNode;
        TreeMap<String, TrieNode> child;

        TrieNode(String value) {
            this.value = value;
            this.isLeafNode = false;
            this.child = new TreeMap<>();
        }
    }

    static class Trie{
        TrieNode root;

        Trie() {
            this.root = new TrieNode("");
        }

        void insert(String target){
            TrieNode cur = root;

            StringTokenizer st = new StringTokenizer(target);
            int depth = Integer.parseInt(st.nextToken());
            for(int i = 0 ; i < depth ; i++){
                String next = st.nextToken();

                if(!cur.child.containsKey(next)){
                    cur.child.put(next, new TrieNode(next));
                }

                cur = cur.child.get(next);
            }

            cur.isLeafNode = true;
        }

        void printAll(){
            print(root, -1);
        }

        void print(TrieNode next, int depth){
            for(int i = 0 ; i < depth ; i++){
                sb.append("--");
            }
            if(depth != -1) sb.append(next.value).append("\n");
            if(next.isLeafNode) return;

            for (String key : next.child.keySet()) {
                print(next.child.get(key), depth + 1);
            }
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Trie trie = new Trie();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            trie.insert(br.readLine());
        }

        trie.printAll();
        System.out.println(sb);
    }
}
