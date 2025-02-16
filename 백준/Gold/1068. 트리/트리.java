import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] child;
    static int numOfLeafNode;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int remove_node = Integer.parseInt(br.readLine());

        ArrayList<Integer> root = new ArrayList<>();
        child = new ArrayList[N];
        for(int i = 0 ; i < N ; i++){
            child[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N ; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent == remove_node || i == remove_node) continue;
            if(parent == -1) root.add(i);
            else child[parent].add(i);
        }
        
        numOfLeafNode = 0;
        for (int start : root) {
            leaf_node(start);
        }
        System.out.println(numOfLeafNode);
    }

    public static void leaf_node(int parent){
        ArrayList<Integer> next = child[parent];
        if(next.isEmpty()){ // 비어있다면 리프노드
            numOfLeafNode++;
        }else{
            for (int i : next) {
                leaf_node(i);
            }
        }
    }

}
