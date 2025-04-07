package algorithm;

// 메모리: 146912 KB, 시간: 564 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P12015 {
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int n;

	static int find(int newVal) {
		int s = 0;
		int e = list.size() - 1;
		if (list.isEmpty()||newVal > list.get(e) )
			return -1;
		while (s <= e) {
			int mid = (s + e) / 2;
			if (list.get(mid) == newVal) {
				return mid;
			} else if (list.get(mid) < newVal) {
				s = mid + 1;
			} else {
				e = mid - 1;
			}
		}
		return Math.max(s, e);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			int newVal = Integer.parseInt(st.nextToken());
			int idx = find(newVal);
			if (idx == -1) {
				list.add(newVal);
			} else {
				list.set(idx, newVal);
			}
		}

		System.out.println(list.size());
	}

}
