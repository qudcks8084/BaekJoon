import java.util.*;
import java.io.*;

class Solution {
    public int solution(int M, int N) {
        return cut(M, N);
    }
    
    public int cut(int m, int n){
        
        // 가로를 자를 수 있다면
        if(m >= 2){
            System.out.println(m + " " + n);
            int m_a = m / 2;
            int m_b = m - m_a;
            return 1 + cut(m_a, n) + cut(m_b, n);
        }
        
        // 세로를 자를 수 있다면
        else if(n >= 2){
            System.out.println(m + " " + n);
            int n_a = n / 2;
            int n_b = n - n_a;
            return 1 + cut(m, n_a) + cut(m, n_b);
        }
        
        else 
            return 0;

    }
}