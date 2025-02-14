import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
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
        makeCombination(nums, startTeam, 0, n / 2);

        System.out.println(ans);
    }

    static void makeCombination(int[] nums, Stack<Integer> startTeam, int start, int end) {
        if (startTeam.size() == end) {
            checkDiff(startTeam);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            startTeam.push(nums[i]);
            makeCombination(nums, startTeam, i + 1, end);
            startTeam.pop();
        }
    }

    static void checkDiff(Stack<Integer> startTeam) {
        Stack<Integer> linkTeam = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (!startTeam.contains(i)) {
                linkTeam.push(i);
            }
        }

        int startTeamAbility = 0;
        int linkTeamAbility = 0;

        // startTeam
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                int mem1 = startTeam.get(i);
                int mem2 = startTeam.get(j);
                startTeamAbility += (ability[mem1][mem2] + ability[mem2][mem1]);

                mem1 = linkTeam.get(i);
                mem2 = linkTeam.get(j);
                linkTeamAbility += (ability[mem1][mem2] + ability[mem2][mem1]);
            }
        }

        ans = Math.min(ans, Math.abs(startTeamAbility - linkTeamAbility));
    }
}