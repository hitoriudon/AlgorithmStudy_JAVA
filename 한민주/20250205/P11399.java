import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P11399 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] waitTimeString = br.readLine().split(" ");
        int[] waitTimes = new int[n];
        // 정렬
        for (int i = 0; i < n; i++) {
            waitTimes[i] = Integer.parseInt(waitTimeString[i]);
        }

        Arrays.sort(waitTimes); // 1 2 3 3 4
        // for i in arr[n]
        int sum = 0;
        for (int i : waitTimes) { // 5 8 9 6 4
            sum += i * n--;
        }

        System.out.println(sum);

    }
}
