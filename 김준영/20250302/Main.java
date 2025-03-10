
// [2458, 키순서]
// 자신의 키가 몇번째인지 아는 학생의 수 출력
// 순서가 결정되는 경우 : 나보다 작은애 + 나보다 큰에 = 전체개수 - 1 인 경우
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int small, big;
	static ArrayList<Integer>[] smaller;
	static ArrayList<Integer>[] bigger;
	static int cnt;
	static int res;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // n까지의 학생
		m = Integer.parseInt(st.nextToken()); // m번 비교

		smaller = new ArrayList[n + 1];
		bigger = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			smaller[i] = new ArrayList<>();
			bigger[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			small = Integer.parseInt(st.nextToken());
			big = Integer.parseInt(st.nextToken());

			smaller[big].add(small);
			bigger[small].add(big);
		}

		res = 0;

		for (int i = 1; i <= n; i++) {
//			System.out.println(">>>>> 현재 학생 : " + i);
//			System.out.println(i + "보다 작은 애 : " + smaller[i]);
//			System.out.println(i + "보다 큰 애 : " + bigger[i]);

			cnt = 0;
			visit = new boolean[n + 1];
			
			findSmall(i);
			findBig(i);
//			System.out.println(cnt);
			if (cnt == n - 1) {
				res++;
			}
		}

		System.out.println(res);
	}

	static void findSmall(int curStd) {

		if (smaller[curStd].size() == 0) { // 나보다 작은 애가 없는 경우
			return;
		} else { // 나보다 작은 애가 있는 경우
			for (int std : smaller[curStd]) {
				if(visit[std] == false) { // 방문 안한 경우에만
					visit[std] = true;
					cnt++;
					findSmall(std);
				}
			}
		}
	}

	static void findBig(int curStd) {

		if (bigger[curStd].size() == 0) { // 나보다 큰 애가 없는 경우
			return;
		} else { // 나보다 큰 애가 있는 경우
			for (int std : bigger[curStd]) {
				if(visit[std] == false) {
					visit[std] = true;
					cnt++;
					findBig(std);
				}
			}
		}

	}
}