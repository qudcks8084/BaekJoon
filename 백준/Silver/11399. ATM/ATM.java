import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0 ; i < N ; i ++){
            pq.offer(sc.nextInt());
        }

        int waiting = 0;
        int answer = 0;
        while (!pq.isEmpty()) {
            int num = pq.poll();
            waiting += num;
            answer += waiting;
        }
        System.out.println(answer);
    }
}
