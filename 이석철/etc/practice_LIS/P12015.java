package practice_LIS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// binary search를 직접 구현하는 게 더 빠르지만, 이 문제는 커버 가능해서 그냥 컬렉션 사용
public class P12015 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        ArrayList<Integer> lis = new ArrayList<>();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());

            int pos = Collections.binarySearch(lis, nums[i]);

            if (pos < 0) {
                pos = -(pos + 1);
            }

            if (pos == lis.size()) {
                lis.add(nums[i]);
            } else {
                lis.set(pos, nums[i]);
            }
        }

        System.out.println(lis.size());
    }
}