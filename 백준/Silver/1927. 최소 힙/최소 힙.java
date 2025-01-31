import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i =  0 ; i < N ; i++){
            int num = sc.nextInt();
            if(num == 0){
                if(pq.isEmpty()) sb.append("0").append("\n");
                else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                pq.offer(num);
            }
        }
        System.out.println(sb);
    }
}
