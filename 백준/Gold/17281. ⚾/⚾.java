import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] inningResults; // Store all innings' results
    static boolean[] visited;     // Track selected batting positions
    static int[] battingOrder;    // Final batting order
    static int maxTotalScore;     // Maximum total score across all innings
    static int currentBatter;     // Current batter position
    static int N;                 // Number of innings

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        inningResults = new int[N][9];

        // Read all innings' results
        for(int inning = 0; inning < N; inning++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++) {
                inningResults[inning][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[9];
        battingOrder = new int[9];
        maxTotalScore = 0;

        // Fix player #1 as cleanup hitter (4th position)
        battingOrder[3] = 0;
        visited[0] = true;

        // Generate all possible batting orders
        makeOrder(0);

        System.out.println(maxTotalScore);
    }

    // Generate batting order using permutation
    static void makeOrder(int depth) {
        if(depth == 9) {
            playAllInnings();
            return;
        }

        // Skip 4th position as it's fixed
        if(depth == 3) {
            makeOrder(depth + 1);
            return;
        }

        for(int i = 1; i < 9; i++) {
            if(!visited[i]) {
                visited[i] = true;
                battingOrder[depth] = i;
                makeOrder(depth + 1);
                visited[i] = false;
            }
        }
    }

    // Play through all innings
    static void playAllInnings() {
        int totalScore = 0;
        currentBatter = 0;

        // Play each inning
        for(int inning = 0; inning < N; inning++) {
            int[] bases = new int[3]; // 0: 1st base, 1: 2nd base, 2: 3rd base
            int outs = 0;

            while(outs < 3) {
                int result = inningResults[inning][battingOrder[currentBatter]];

                switch(result) {
                    case 0: // Out
                        outs++;
                        break;
                    case 1: // Single
                        totalScore += bases[2];
                        bases[2] = bases[1];
                        bases[1] = bases[0];
                        bases[0] = 1;
                        break;
                    case 2: // Double
                        totalScore += bases[2] + bases[1];
                        bases[2] = bases[0];
                        bases[1] = 1;
                        bases[0] = 0;
                        break;
                    case 3: // Triple
                        totalScore += bases[2] + bases[1] + bases[0];
                        bases[2] = 1;
                        bases[1] = bases[0] = 0;
                        break;
                    case 4: // Home run
                        totalScore += bases[2] + bases[1] + bases[0] + 1;
                        bases[2] = bases[1] = bases[0] = 0;
                        break;
                }

                currentBatter = (currentBatter + 1) % 9;
            }
        }

        maxTotalScore = Math.max(maxTotalScore, totalScore);
    }
}