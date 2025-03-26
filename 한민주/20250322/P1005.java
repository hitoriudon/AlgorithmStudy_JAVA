package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1005 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int time[] = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			ArrayList<ArrayList<Integer>> building = new ArrayList<>();
			for (int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				building.add(new ArrayList<Integer>());
			}
			building.add(new ArrayList<Integer>());

			int preCnt[] = new int[n + 1];
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				building.get(x).add(y);
				preCnt[y]++;
			}
			int minTime[] = new int[n + 1];
			ArrayDeque<Integer> preBuild = new ArrayDeque<Integer>();
			for (int i = 1; i <= n; i++) {
				if (preCnt[i] == 0) {
					preBuild.add(i);
					minTime[i] = time[i];
				}
			}
			int w = Integer.parseInt(br.readLine());

			while (!preBuild.isEmpty()) {
				int now = preBuild.pollFirst();
				if (now == w) {
					break;
				}

				for (int i = 0; i < building.get(now).size(); i++) {
					int next = building.get(now).get(i);
					preCnt[next]--;
					minTime[next] = Math.max(minTime[now] + time[next], minTime[next]);
					if (preCnt[next] == 0) {
						preBuild.add(next);
					}
				}
			}
			sb.append(minTime[w]);
			sb.append("\n");
		}

		System.out.println(sb);

	}

}
