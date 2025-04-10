import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P20040 {
	static int[] parents;

	static int find(int num) {
		if (num == parents[num]) {
			return num;
		}

		return parents[num] = find(parents[num]);
	}

	static boolean union(int num1, int num2) {
		int num1Root = find(num1);
		int num2Root = find(num2);

		if (num1Root == num2Root) {
			return false;
		}

		parents[num2Root] = num1Root;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int numAmount = Integer.parseInt(st.nextToken());
		int orderAmount = Integer.parseInt(st.nextToken());

		parents = new int[numAmount];
		for (int numCount = 0; numCount < numAmount; numCount++) {
			parents[numCount] = numCount;
		}
		
		for(int orderCount = 1; orderCount <= orderAmount; orderCount++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			if(!union(num1, num2)) {
				System.out.println(orderCount);
				return;
			}
		}
		
		System.out.println(0);
		return;
	}
}
