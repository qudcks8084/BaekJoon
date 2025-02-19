import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static ArrayDeque<Character> front;
    static ArrayDeque<Character> back;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        front = new ArrayDeque<>();
        back = new ArrayDeque<>();
        char[] input = br.readLine().toCharArray();
        for (char c : input) {
            front.offer(c);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            action(st);
        }
        print();
    }

    public static void action(StringTokenizer st) {
        String command = st.nextToken();
        switch (command) {
            case "L":
                if (!front.isEmpty()) {
                    back.addFirst(front.pollLast());
                }
                break;
            case "D":
                if (!back.isEmpty()) {
                    front.offer(back.pollFirst());
                }
                break;
            case "B":
                if (!front.isEmpty()) {
                    front.pollLast();
                }
                break;
            case "P":
                char in = st.nextToken().charAt(0);
                front.offer(in);
                break;
        }
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (char c : front) {
            sb.append(c);
        }
        for (char c : back) {
            sb.append(c);
        }
        System.out.println(sb.toString());
    }
}
