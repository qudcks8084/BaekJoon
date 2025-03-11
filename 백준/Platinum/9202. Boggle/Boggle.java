import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static class TrieNode{
        int isLeafNode;
        TrieNode[] children;

        public TrieNode() {
            this.isLeafNode = -1;
            this.children = new TrieNode[26];
        }
    }

    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dr = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};
    static class Trie{
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void insert(String target, int input_index){
            TrieNode cur = root;

            int len = target.length();
            for(int i = 0 ; i < len ; i++){
                int next = target.charAt(i) - 'A';

                if(cur.children[next] == null){
                    cur.children[next] = new TrieNode();
                }

                cur = cur.children[next];
            }

            cur.isLeafNode = input_index;
        }


        void find(TrieNode node, int len, int c, int r, StringBuilder sb){
            // 만약 leaf Node라면 정답을 갱신
            if(node.isLeafNode > -1 && !word_visited[node.isLeafNode]) {
                word_visited[node.isLeafNode] = true;
                max_score += score[len];
                numOfWord++;
                if(max_len < len){
                    max_len = len;
                    max_str = sb.toString();
                }
                else if(max_len == len && max_str.compareTo(sb.toString()) > 0){
                    max_str = sb.toString();
                }
            }

            // 8방을 탐색
            for(int i = 0 ; i < 8 ; i++){
                int n_c = c + dc[i];
                int n_r = r + dr[i];
                if(n_c < 0 || n_c >= 4 || n_r < 0 || n_r >= 4) continue;
                int next = map[n_c][n_r];
                if(visited[n_c][n_r] || node.children[next] == null) continue;
                visited[n_c][n_r] = true;
                StringBuilder next_sb = new StringBuilder(sb.toString());
                next_sb.append((char)(next + 'A'));
                find(node.children[next], len + 1, n_c, n_r, next_sb);
                visited[n_c][n_r] = false;
            }
        }

    }

    static int max_score;
    static int max_len;
    static int numOfWord;
    static String max_str;
    static int[][] map;
    static boolean[][] visited;
    static boolean[] word_visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();

        // 사용자에게 입력받은 문자열 삽입하기
        int N = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < N ; i++){
            trie.insert(br.readLine(), i);
        }

        br.readLine(); // 빈줄 읽기
        int T = Integer.parseInt(br.readLine());
        for(int testcase = 0 ; testcase < T ; testcase++){
            max_score = 0;
            max_len = 0;
            numOfWord = 0;
            max_str = "";
            map = new int[4][4];
            visited = new boolean[4][4];
            word_visited = new boolean[N];

            for(int c = 0 ; c < 4 ; c++){
                char[] line = br.readLine().toCharArray();
                for(int r = 0 ; r < 4 ; r++){
                    map[c][r] = line[r] - 'A';
                }
            }

            for(int c = 0 ; c < 4 ; c++){
                for(int r = 0 ; r < 4 ; r++){
                    int next = map[c][r];
                    if(trie.root.children[next] == null) continue;
                    visited[c][r] = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append((char) (next + 'A'));
                    trie.find(trie.root.children[next], 1, c, r, sb);
                    visited[c][r] = false;
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append(max_score).append(" ").append(max_str).append(" ").append(numOfWord);
            System.out.println(sb);

            if(testcase != T-1) br.readLine();
        }

    }
}
