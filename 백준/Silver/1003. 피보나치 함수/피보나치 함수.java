
import java.util.Scanner;

public class Main {
    static int[] fob_0, fob_1;
    static int[] input;
    static int N, max;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        max = 0;
        for(int i = 0 ; i < N ; i++){
            input[i] = sc.nextInt();
            max = Math.max(max, input[i]);
        }
        fob_0 = new int[max + 1];
        fob_1 = new int[max + 1];
        fob_0[0] = 1;

        if(max > 0){
            fob_1[1] = 1;

            for(int i = 2 ; i <= max ; i++){
                fob_0[i] = fob_0[i - 1] + fob_0[i - 2];
                fob_1[i] = fob_1[i - 1] + fob_1[i - 2];

            }

        }
        for (int i : input) {
            System.out.println(fob_0[i] +" "+ fob_1[i]);
        }
    }
}
