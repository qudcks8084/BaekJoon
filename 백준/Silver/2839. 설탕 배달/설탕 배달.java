import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int numOfBag = 0;
        if (n == 4 || n == 7) {
            numOfBag =  -1;
        }
        else if (n % 5 == 0) {
            numOfBag =  (n / 5);
        }
        else if (n % 5 == 1 || n % 5 == 3) {
            numOfBag =  (n / 5) + 1;
        }
        else if (n % 5 == 2 || n % 5 == 4) {
            numOfBag =  (n / 5) + 2;
        }
        System.out.println(numOfBag);
    }
}
