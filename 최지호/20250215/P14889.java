import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class P14889 {
    static int ans = Integer.MAX_VALUE;
    static int[][] ability;
    static int n;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ability = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }

        Stack<Integer> startTeam = new Stack<>();
        makeCombination(startTeam, 0);

        System.out.println(ans);
    }

    private static void makeCombination(Stack<Integer> startTeam, int start) {
        //끝나는 조건
        if(startTeam.size() == n / 2) {
            checkDiff(startTeam);
            return;
        }

        for(int i = start; i < nums.length; i++) {
            startTeam.push(nums[i]);
            makeCombination(startTeam, i + 1);
            startTeam.pop();
        }
    }

    private static void checkDiff(Stack<Integer> startTeam) {
        Stack<Integer> linkTeam = new Stack<>();
        
        for(int i = 0; i < n; i++) {
            if(!startTeam.contains(i)) {
                linkTeam.push(i);
            }
        }

        int startTeamAb = 0;
        int linkTeamAb = 0;

        for(int i = 0; i < n / 2; i++) {
            for(int j = i + 1; j < n / 2; j++) {
                int a = startTeam.get(i);
                int b = startTeam.get(j);
                startTeamAb += ability[a][b] + ability[b][a];

                a = linkTeam.get(i);
                b = linkTeam.get(j);
                linkTeamAb += ability[a][b] + ability[b][a];
            }
        }
        ans = Math.min(ans, Math.abs(startTeamAb - linkTeamAb));
    }
}