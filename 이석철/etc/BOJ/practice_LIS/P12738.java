package BOJ.practice_LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P12738 {
    static ArrayList<Integer> lis;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        lis = new ArrayList<>();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int len = 0;

        for (int i = 0; i < n; i++) {
            int idx = lowerBound(0, len, nums[i]); // nums[i]가 오름차순 정렬을 유지하면서 들어가야 하는 인덱스 찾기

            if (idx == len) { // right였다? 넣으면 됨
                lis.add(nums[i]);
                len++; // 정답 길이 +1
            } else {
                lis.set(idx, nums[i]); // 해당 인덱스 값을 변경 (바꾸는 게 이득. 더 작은 값일 테니)
            }
        }

        System.out.println(len);
    }

    // binary search 직접 구현
    public static int lowerBound(int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (lis.get(mid) < target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }
}
