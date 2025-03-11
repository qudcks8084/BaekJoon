import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	static class TrieNode{
		boolean isLeafNode;
		String value;
		TreeMap<String, TrieNode> children;
		
		TrieNode(String value){
			this.isLeafNode = false;
			this.value = value;
			this.children = new TreeMap<>();
		}
	}
	
	static class Trie{
		TrieNode root;
		
		Trie(){
			this.root = new TrieNode(null);
		}
		
		void insert(String input) {
			TrieNode cur = root;
			
			StringTokenizer st = new StringTokenizer(input, "\\");
			while(st.hasMoreTokens()) {
				String next = st.nextToken();
				
				if(!cur.children.containsKey(next)) {
					cur.children.put(next, new TrieNode(next));
				}
				
				cur = cur.children.get(next);
			}
			
			cur.isLeafNode = true;
		}
		
		void printAll() {
			for(String next : root.children.keySet()) {
				dfs(0, root.children.get(next));
			}
		}
		
		void dfs(int depth, TrieNode cur) {
			for(int i = 0 ; i < depth ; i++) {
				sb.append(" ");
			}
			sb.append(cur.value).append("\n");
			for(String next : cur.children.keySet()) {
				dfs(depth + 1, cur.children.get(next));
			}
		}
	}
	
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		Trie trie = new Trie();
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < N ; i++) {
			trie.insert(br.readLine());
		}
		trie.printAll();
		
		System.out.println(sb);
	
	}
}
