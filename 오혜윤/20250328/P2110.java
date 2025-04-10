// [���4] 2110��. ������ ��ġ
// �޸� : 28396 KB, �ð� : 244 ms

import java.io.*;
import java.util.*;

public class P2110 {
	static int N, C;
	static int[] houses;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// ���� ����
		C = Integer.parseInt(st.nextToken());	// �������� ����
		
		houses = new int[N];
		for (int i = 0; i < N; i++) {
			houses[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(houses);
		
		int start = 1, end = houses[N-1] - houses[0];
		while (start <= end) {
			int mid = (start + end) / 2;	// ������ ��ġ ����
			
			// ��ġ �Ұ��� -> ���� �� �ٿ�����
			if (!install(mid, new ArrayList<>())) end = mid - 1;
			
			// ��ġ ���� -> ���� �� �÷�����
			else start = mid + 1;
		}
		System.out.println(end);
	}
	
	static boolean install(int mid, List<Integer> current) {
		int cnt = 1;	// ������� ��ġ�� ������ ����
		int last = houses[0];	// ���� �ֱ� ��ġ�� ��
		
		for (int i = 1; i < N; i++) {
			if (cnt >= C) return true;
			
			if (houses[i] - last < mid) continue;
			last = houses[i];
			cnt++;
		}
		
		if (cnt < C) return false;
		return true;
	}
}