import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1895 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[][] image = new int[r][c];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                image[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int t = Integer.parseInt(br.readLine());

        List<Integer> medianValues = new ArrayList<>();
        for (int i = 0; i < r - 2; i++) {
            for (int j = 0; j < c - 2; j++) {
                List<Integer> filteredImage = new ArrayList<>();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        filteredImage.add(image[i + x][j + y]);
                    }
                }
                Collections.sort(filteredImage);
                medianValues.add(filteredImage.get(4)); // 정렬한 뒤 중간값은 4에 위치함
            }
        }

        int ans = 0;
        for (int i = 0; i < medianValues.size(); i++) {
            if (medianValues.get(i) >= t) {
                ans++;
            }
        }
        System.out.println(ans);
    }
}