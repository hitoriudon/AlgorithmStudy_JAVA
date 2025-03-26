package algorithm;

//메모리: 19604 KB, 시간: 192ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1516 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> buildings = new ArrayList<ArrayList<Integer>>();
		ArrayDeque<Integer> build = new ArrayDeque<Integer>();
		int time[] = new int[n + 1];
		int buildTime[] = new int[n + 1];
		int preBuild[] = new int[n + 1];
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i <= n; i++) {
			buildings.add(new ArrayList<Integer>());
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			buildTime[i] = time[i];
			int pre = Integer.parseInt(st.nextToken());
			while (pre != -1) {
				buildings.get(pre).add(i);
				preBuild[i]++;
				pre = Integer.parseInt(st.nextToken());
			}
			if (preBuild[i] == 0) {
				build.add(i);
			}
		}

		while (!build.isEmpty()) {
			int now = build.pollFirst();
			for (int i = 0; i < buildings.get(now).size(); i++) {
				int next = buildings.get(now).get(i);
				preBuild[next]--;
				buildTime[next] = Math.max(buildTime[now] + time[next], buildTime[next]);
				if (preBuild[next] == 0) {
					build.add(next);
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			sb.append(buildTime[i]);
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
