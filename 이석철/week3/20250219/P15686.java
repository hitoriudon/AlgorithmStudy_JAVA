import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P15686 {
    public static ArrayList<ArrayList<Integer>> house;
    public static ArrayList<ArrayList<Integer>> chickenStore;
    public static ArrayList<ArrayList<Integer>> selectedChickenStore;
    public static int n;
    public static int m;
    public static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] city = new int[n][n];
        house = new ArrayList<>();
        chickenStore = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int info = Integer.parseInt(st.nextToken());
                city[i][j] = info;
                if (info == 1) {
                    ArrayList<Integer> dots = new ArrayList<>();
                    dots.add(i);
                    dots.add(j);
                    house.add(dots);
                } else if (info == 2) {
                    ArrayList<Integer> dots = new ArrayList<>();
                    dots.add(i);
                    dots.add(j);
                    chickenStore.add(dots);
                }
            }
        }
        selectedChickenStore = new ArrayList<>();
        answer = Integer.MAX_VALUE;
        makeCombination(0);
        System.out.println(answer);
    }

    public static void makeCombination(int start) {
        if (selectedChickenStore.size() == m) {
            answer = Math.min(answer, calculateMinimumDistance(selectedChickenStore));
        }

        for (int i = start; i < chickenStore.size(); i++) {
            selectedChickenStore.add(chickenStore.get(i));
            makeCombination(i + 1);
            selectedChickenStore.remove(selectedChickenStore.size() - 1);
        }
    }

    public static int calculateMinimumDistance(ArrayList<ArrayList<Integer>> selected) {
        int minimumDistance = 0;
        for (int i = 0; i < house.size(); i++) {
            ArrayList<Integer> distances = new ArrayList<>();
            int hx = house.get(i).get(0); // house x
            int hy = house.get(i).get(1); // house y

            for (int idx = 0; idx < m; idx++) {
                int cx = selected.get(idx).get(0);
                int cy = selected.get(idx).get(1);

                distances.add(Math.abs(hx - cx) + Math.abs(hy - cy));
            }
            minimumDistance += Collections.min(distances);
        }

        return minimumDistance;
    }
}
