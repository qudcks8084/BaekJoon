import java.io.*;
import java.util.*;
public class Main {
    /*
        인오더와 포스트오더로 프리오더 찾는법....
        특징을 잘 찾아야한다.

             1
          2     3
        4   5     7
        에서
        인오더는 4-2-5-1-3-7
        포스트는 4-5-2-7-3-1야.
        프리오더 1-2-4-5-3-7야.

        방법 1. 포스트 오더의 마지막이 루트 노드야.
        그걸 기준으로 쪼개.
        4-2-5 | 1 | 7-3
        그 다음은 4-5-2를 포스트오더에서 가장 마지막을 찾아. 그게 중심이야. 그런식으로 가면 될거같은데...?

        그리고 StringBuilder에 중간 왼쪽 오른쪽 순으로 가면 정답 나올거같은데
    */

    static int N;
    static StringBuilder sb;
    static int[] inorder, in_idx;
    static int[] postorder, post_idx;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // inorder를 입력받는다.
        inorder = new int[N];
        in_idx = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int num = Integer.parseInt(st.nextToken());
            in_idx[num] = i;
            inorder[i] = num;
        }

        // postorder를 입력받는다.
        postorder = new int[N];
        post_idx = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int num = Integer.parseInt(st.nextToken());
            post_idx[num] = i;
            postorder[i] = num;
        }

        // 탐색을 시작한다.
        sb = new StringBuilder();
        int mid = postorder[N-1];
        int mid_idx = in_idx[mid];

        // 시작점은 postorder[N-1]이다.
        sb.append(mid).append(" ");

        // 왼쪽부터 방문을 시작한다.
        preorder(0, mid_idx - 1);

        // 다음 오른쪽을 방문한다.
        preorder(mid_idx + 1, N-1);

        System.out.println(sb);
    }

    // lf -> 왼쪽 영역 rf -> 오른쪽 경계
    // 왼쪽에서부터 오른쪽에서
    public static void preorder(int lf, int rf){

        // 만약 경계값을 넘어가면 끝.
        if(rf < 0 || lf >= N || lf > rf) return;

        // 자 이제 postOrder 숫자의 해당 구역을 순회하면서 중심점을 찾는다.
        int mid = -1;
        int max = -1;
        for(int i = lf ; i <= rf ; i++){
            int num = inorder[i];
            if(max < post_idx[num]){
                max = post_idx[num];
                mid = num;
            }
        }

        // 동일하게 왼쪽, 중간, 오른쪽 하면 된다.
        int mid_idx = in_idx[mid];
        sb.append(mid).append(" ");
        preorder(lf, mid_idx - 1);
        preorder(mid_idx + 1, rf);

    }
}
