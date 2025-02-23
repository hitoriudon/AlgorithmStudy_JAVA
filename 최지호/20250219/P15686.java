import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15686 {
    static int n, m;
    static int[][] city;
    static List<int[]> house = new ArrayList<>();
    static List<int[]> chicken = new ArrayList<>();
    static int minChicken = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        city = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if(city[i][j] == 1) {
                    house.add(new int[]{i, j});
                }
                if(city[i][j] == 2) {
                    chicken.add(new int[]{i,j});
                }
            }
        }
        //조합 돌리기
        pick(0, 0, new ArrayList<>());

        System.out.println(minChicken);
    }

    private static void pick(int idx, int selected, List<int[]> selectedChikens) {
        //다 골라지면
        if(selected == m) {
            minChicken = Math.min(minChicken, calculatedDistance(selectedChikens));
            return;
        }

        for(int i = idx; i < chicken.size(); i++) {
            selectedChikens.add(chicken.get(i));
            pick(i + 1, selected + 1, selectedChikens);
            selectedChikens.remove(selectedChikens.size() - 1);
        }
    }

    private static int calculatedDistance(List<int[]> selectedChikens) {
        int totalDistance = 0;

        for(int[] h : house) {
            int hx = h[0];
            int hy = h[1];
            int minDistance = Integer.MAX_VALUE;

            for(int[] c : selectedChikens) {
                int cx = c[0];
                int cy = c[1];
                int distance = Math.abs(hx - cx) + Math.abs(hy - cy);
                minDistance = Math.min(minDistance, distance);
            }
            totalDistance += minDistance;
        }
        return totalDistance;
    }
}
