
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		char[] plates = sc.next().toCharArray();
		
		char before = plates[0];
		int height = 10;
		for(int i = 1 ; i < plates.length; i++) {
			char now = plates[i];
			if(now == before) {
				height += 5;
			}else {
				height += 10;
				before = now;
			}
		} 
		
		System.out.println(height);
	}

}
