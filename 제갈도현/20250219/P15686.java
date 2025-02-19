import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P15686 {
	static LinkedList<int[]> house = new LinkedList<>();
	static LinkedList<int[]> chickens = new LinkedList<>();
	static int[] combiRes;
	static boolean[] visited;
	static int N, M, chickenCnt, result = (int) 1e9;

	// 도시 치킨 거리 구하기
	static void calcChickenDistance() {
		int minDistance, chickenDistance = 0;
		int[] chicken;

		for (int[] home : house) {
			// 치킨 거리는 각 집에서 가까운 치킨집과의 거리
			minDistance = (int) 1e9;

			for (int c : combiRes) {
				chicken = chickens.get(c);
				minDistance = Math.min(minDistance, Math.abs(chicken[0] - home[0]) + Math.abs(chicken[1] - home[1]));
			}
			
			// 도시의 치킨 거리는 모든 집의 치킨 거리의 합
			chickenDistance += minDistance;
		}
		
		result = Math.min(result, chickenDistance);
	}
	
	// 조합, n = chicken.size(), r = M;
	static void combination(int index, int n, int r) {
		if (r == 0) {
			int t = 0;
			for (int i = 0; i < chickenCnt; i++) {
				if (visited[i]) {
					combiRes[t++] = i;
				}
			}

			// 치킨집 중 M개를 선택한 조합을 통해 치킨거리 구하기
			calcChickenDistance();

			return;
		}

		for (int i = index; i < n; i++) {
			visited[i] = true;
			combination(i + 1, n, r - 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int temp;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					// 1이라면 집 좌표 저장
					house.add(new int[] { i, j });
				} else if (temp == 2) {
					// 2라면 치킨집 좌표 저장
					chickens.add(new int[] { i, j });
				}
			}
		}

		// 조합에 사용할 배열 초기화
		chickenCnt = chickens.size();
		visited = new boolean[chickenCnt];
		combiRes = new int[M];

		// 치킨집 중 M개 고르는 조합
		combination(0, chickenCnt, M);

		System.out.println(result);
	}
}