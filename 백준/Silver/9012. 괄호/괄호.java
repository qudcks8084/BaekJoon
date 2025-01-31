import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> st = new Stack<>();
        Scanner sc = new Scanner(System.in);
        int numOfThings = Integer.parseInt(sc.nextLine());
        for(int i = 0 ; i < numOfThings ; i++){
            String[] array = sc.nextLine().split("");
            st.clear();
            for (String s : array) {
                if(st.isEmpty()){st.push(s);}
                else{
                    if(Objects.equals(st.peek(), "(") && Objects.equals(s, ")")){
                        st.pop();
                    }else {
                        st.push(s);
                    }
                }
                //System.out.println(st);
            }
            if(st.isEmpty())
                System.out.println("YES");
            else
                System.out.println("NO");
        }

    }
}
