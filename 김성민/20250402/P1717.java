import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1717 {
	static int numAmount;
	static int[] parents;

	static void make() {
		parents = new int[numAmount + 1];
		for (int num = 1; num <= numAmount; num++) {
			parents[num] = num;
		}
	}

	static int findRoot(int num) {
		if (num == parents[num]) {
			return num;
		}
		return parents[num] = findRoot(parents[num]);
	}

	static void union(int num1, int num2) {
		int num1Root = findRoot(num1);
		int num2Root = findRoot(num2);
		if (num1Root == num2Root) {
			return;
		}

		parents[num2Root] = num1Root;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		numAmount = Integer.parseInt(st.nextToken());
		int orderAmount = Integer.parseInt(st.nextToken());

		make();
		for (int orderCount = 0; orderCount < orderAmount; orderCount++) {
			st = new StringTokenizer(br.readLine());

			int order = Integer.parseInt(st.nextToken());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());

			if (order == 0) {
				union(num1, num2);
			} else {
				sb.append((findRoot(num1) == findRoot(num2) ? "YES\n" : "NO\n"));
			}
		}
		sb.append("\n");

		System.out.println(sb);
	}
}
