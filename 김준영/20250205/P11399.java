import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P11399 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> time = new ArrayList<>();

        while (st.hasMoreTokens()) {
            time.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(time);

        int res = 0;

        for (int i = 0; i < time.size(); i++) {

            res += time.get(i) * (time.size() - i);
        }

        System.out.println(res);
    }
}