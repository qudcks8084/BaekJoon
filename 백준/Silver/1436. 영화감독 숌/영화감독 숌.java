import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int index = 0;
        int num = 665;
        while(index != n){
            num++;
            String tmp = String.valueOf(num);
            if(tmp.contains("666")) index++;
        }

        System.out.println(num);
    }

}
