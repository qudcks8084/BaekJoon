import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class TrieNode {
		boolean isLeafNode;
		TrieNode[] children;

		public TrieNode() {
			this.isLeafNode = false;
			this.children = new TrieNode[26];
		}
	}
	
	static class Trie{
		TrieNode root;

		public Trie() {
			root = new TrieNode();
		}
		
		public void insert(String word) {
			TrieNode cur = root;
			
			for(char c : word.toCharArray()) {
				int index = c - 'a';
				
				if(cur.children[index] == null) {
					cur.children[index] = new TrieNode();
				}
				
				cur = cur.children[index];
			}
			
			cur.isLeafNode = true;
		}
		
		public boolean hasWord(String word) {
			TrieNode cur = root;
			
			for(char c : word.toCharArray()) {
				int index = c - 'a';
				
				if(cur.children[index] == null) {
					return false;
				}
				
				cur = cur.children[index];
			}
			
			if(cur.isLeafNode) return true;
			
			return false;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Trie trie = new Trie();
		for(int i = 0 ; i < N ; i++) {
			trie.insert(br.readLine());
		}
		
		int cnt = 0;
		for(int i = 0 ; i < M ; i++) {
			if(trie.hasWord(br.readLine())) cnt++;
		}
		
		System.out.println(cnt);
	}
}
