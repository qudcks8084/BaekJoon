import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.print(Integer.bitCount(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine())));
        
    }
}
