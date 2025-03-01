import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int X = sc.nextInt();
		
		if(X == 64) {
			System.out.println(1);
			return;
		}
		
		pq.add(64);
		
		while(true) {
		
			int smallest = pq.poll();
			int half = smallest / 2;
			
			pq.add(half);
			
			// 가장 짧은 것을 반을 잘라서 넣고 확인
			int sum = 0;
			for(int num : pq) {
				sum += num;
			}
			
			if(sum == X) break;
			else if (sum < X) pq.offer(half);
			
		}
		
		System.out.println(pq.size());
	}
}
