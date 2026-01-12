import java.util.*;

class TrieNode{
    boolean isLeaf;
    TrieNode[] children;
    
    public TrieNode(){
        this.isLeaf = false;
        
        // 0~9까지 다룬다.
        children = new TrieNode[10];
    }
}

class Trie{
    TrieNode root;
    
    public Trie(){
        this.root = new TrieNode();
    }
    
    public void insert(String num){
        int len = num.length();
        
        TrieNode cur = root;
        
        for(int i = 0 ; i < len ; i++){
            int tmp = num.charAt(i) - '0';
            
            if(cur.children[tmp] == null){
                cur.children[tmp] = new TrieNode();
            }
            
            cur = cur.children[tmp];
        }
        
        // leaf에 도착
        cur.isLeaf = true;
    }
    
    public boolean check(String num){

        int len = num.length();

        TrieNode cur = root;
        
        for(int i = 0 ; i < len ; i++){
            
            // 내려가면서 만약 중간에 isLeaf를 발견하면 return false;
            if(cur.isLeaf) return true;
            
            cur = cur.children[num.charAt(i) - '0'];
                
        }
        
        return false;
    }
    
}


class Solution {
    public boolean solution(String[] phone_book) {
        
        Trie trie = new Trie();
        
        for(String num : phone_book){
            trie.insert(num);
        }
        
        for(String num : phone_book){
            if(trie.check(num)) return false;
        }
        
        return true;
    }
}