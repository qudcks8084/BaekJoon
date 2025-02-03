import java.util.Scanner;

public class Main {
    static StringBuilder sb;
    static int N;
    static String A = "\"재귀함수가 뭔가요?\"\n";
    static String B = "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n";
    static String C = "라고 답변하였지.\n";

    static String D = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static String E = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static String F = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        sb = new StringBuilder();
        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        recursion(0);
        System.out.println(sb);
    }

    public static void recursion(int depth){
        StringBuilder blank = new StringBuilder();
        for(int i = 0 ; i < depth ; i++){
            blank.append("____");
        }
        if(depth == N){
            sb.append(blank).append(A).append(blank).append(B).append(blank).append(C);
        }else{
            sb.append(blank).append(A).append(blank).append(D).append(blank).append(E).append(blank).append(F);
            recursion(depth + 1);
            sb.append(blank).append(C);

        }

    }
}
