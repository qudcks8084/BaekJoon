import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TestCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t < TestCase ; t++){
            char[] inst = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            ArrayDeque<String> arr = new ArrayDeque<>();
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input.substring(1, input.length() - 1), ",");
            for(int i = 0 ; i < N ; i++){
                arr.add(st.nextToken());
            }
            boolean error = false;
            boolean flag = true;
            for(int i = 0 ; i < inst.length ; i++){
                char c = inst[i];
                if(c == 'R') flag = !flag;
                if(c == 'D'){
                    if(arr.isEmpty()){
                        error = true;
                        break;
                    }
                    if(flag) arr.removeFirst();
                    else arr.removeLast();
                }
            }

            if(error){
                sb.append("error").append("\n");
            }else{
                sb.append("[");
                int size = arr.size();
                if(flag){
                    for(int i = 0 ; i < size ; i++){
                        sb.append(arr.removeFirst());
                        if(i+1 != size) sb.append(",");
                    }
                }else{
                    for(int i = 0 ; i < size ; i++){
                        sb.append(arr.removeLast());
                        if(i+1 != size) sb.append(",");
                    }
                }

                sb.append("]").append("\n");
            }
        }
        System.out.println(sb);
    }
}
