import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P4195 {
	static int[] parents;
	static int[] relations;

	static int find(int humanNum) {
		if (humanNum == parents[humanNum]) {
			return humanNum;
		}

		return parents[humanNum] = find(parents[humanNum]);
	}

	static void union(int human1Num, int human2Num) {
		int human1Root = find(human1Num);
		int human2Root = find(human2Num);

		if (human1Root == human2Root) {
			return;
		}

		parents[human2Root] = human1Root;
		relations[human1Root] += relations[human2Root];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		StringBuilder sb = new StringBuilder();

		int caseAmount = Integer.parseInt(br.readLine());
		while (caseAmount-- > 0) {
			int relationAmount = Integer.parseInt(br.readLine());
			parents = new int[relationAmount * 2 + 1];
			relations = new int[relationAmount * 2 + 1];
			for (int relationCount = 1; relationCount <= relationAmount * 2; relationCount++) {
				parents[relationCount] = relationCount;
				relations[relationCount] = 1;
			}

			Map<String, Integer> nums = new HashMap<>();
			int num = 1;
			while (relationAmount-- > 0) {
				st = new StringTokenizer(br.readLine());
				String human1 = st.nextToken();
				String human2 = st.nextToken();

				if (!nums.containsKey(human1)) {
					nums.put(human1, num++);
				}
				if (!nums.containsKey(human2)) {
					nums.put(human2, num++);
				}

				union(nums.get(human1), nums.get(human2));
				sb.append(relations[find(nums.get(human1))]).append("\n");
			}
		}

		System.out.println(sb);
	}
}
