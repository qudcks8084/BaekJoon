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

        일단 성공은 했음. 근데 너무 느림. for문을 없애야됨. 어캐 줄임...?

        어차피 노드의 개수에서 가장 마지막꺼만 보면 된다. -> post의 위치로 한번에 찾는다

        오른쪽은 어차피 사용한 마지막 위치 -1 위치임.
        왼쪽은 왼쪽에서 + 왼쪽 노드의 위치임.
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
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            int num = Integer.parseInt(st.nextToken());
            postorder[i] = num;
        }

        // 탐색을 시작한다.
        sb = new StringBuilder();
        int mid = postorder[N-1];
        int mid_idx = in_idx[mid];

        // 왼쪽부터 방문을 시작한다.
        preorder(0, N-1, 0, N-1);

        System.out.println(sb);
    }

    // lf -> 왼쪽 영역 rf -> 오른쪽 경계
    // 왼쪽에서부터 오른쪽에서
    public static void preorder(int in_lf, int in_rf, int po_lf, int po_rf){

        // 만약 경계값을 넘어가면 끝.
        if(in_lf > in_rf || po_lf > po_rf ) return;

        // 루트 노드 찾기
        int mid = postorder[po_rf];

        sb.append(mid).append(" ");

        int mid_idx = in_idx[mid];

        // cnt개만큼 이동한 것이 다음의 중심 노드임.
        preorder(in_lf, mid_idx - 1, po_lf, po_lf + mid_idx - in_lf - 1);

        // 1개 사용했으니까 오른쪽을 po_rf - 1
        preorder(mid_idx + 1, in_rf, po_lf + mid_idx - in_lf, po_rf - 1);
    }
}
