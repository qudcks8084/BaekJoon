import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 1 ; i <= N ; i++){
            sb.append("*".repeat(i)).append("\n");
        }
        System.out.println(sb);
    }
}