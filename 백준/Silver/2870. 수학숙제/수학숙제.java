import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> arr = new ArrayList<>();

        for(int i = 0 ; i < N ; i ++){
            String input = br.readLine();
            for(int j = 0 ; j < input.length() ; j++){
                char tmp = input.charAt(j);
                if (Character.isDigit(tmp)) {
                    StringBuilder num = new StringBuilder(String.valueOf(tmp));
                    for(int k = j+1 ; k < input.length() ; k++){
                        if(Character.isDigit(input.charAt(k))){
                            num.append(input.charAt(k));
                            j++;
                        }else{
                            break;
                        }
                    }
                    
                    String numStr = num.toString();
                    int startIdx = 0;
                    while (startIdx < numStr.length() && numStr.charAt(startIdx) == '0') {
                        startIdx++;
                    }
                    
                    String cleanNum = (startIdx == numStr.length()) ? "0" : numStr.substring(startIdx);

                    arr.add(cleanNum);
                }
            }
        }
        arr.sort((str1, str2) -> {
            if(str1.length() == str2.length()){
                return str1.compareTo(str2);
            }
            else return Integer.compare(str1.length(), str2.length());
        });

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}