import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21758 {
	static int N, res;
	static int[] orig, d;

	static void sideHive(int hive, int firstBee) {
		// 벌집이나 1벌이 항상 끝을 차지하므로, 2벌은 항상 가운데
		for (int secondBee = 1; secondBee < N - 1; secondBee++) {
			if (firstBee == secondBee)
				continue;

			int sum = 0;

			// 1벌이 왼쪽 끝, 벌집이 오른쪽 끝인 경우
			if (firstBee == 0) {
				// 1벌
				sum += d[N - 1] - orig[0] - orig[secondBee];
				// 2벌
				sum += d[N - 1] - d[secondBee];
			} else {
				// 1벌
				sum += d[N - 1] - orig[N - 1] - orig[secondBee];
				// 2벌
				sum += d[secondBee - 1];
			}

			res = Math.max(res, sum);
		}
	}

	static void centerHive() {
		for (int i = 1; i < N - 1; i++) {
			int sum = 0;

			sum += d[i] - orig[0];
			sum += d[N - 1] - orig[N - 1] - d[i - 1];

			res = Math.max(res, sum);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		orig = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			orig[i] = Integer.parseInt(st.nextToken());
		}

		d = new int[N];
		d[0] = orig[0];
		for (int i = 1; i < N; i++) {
			d[i] = d[i - 1] + orig[i];
		}

		res = 0;
		// 채취량의 최댓값은 벌집이나 벌이 끝에 위치한 경우
		// 1. 1벌은 한쪽 끝, 벌집은 반대편 끝인 경우
		sideHive(0, N - 1);
		sideHive(N - 1, 0);
		// 2. 1벌, 2벌이 각각 끝쪽이고 벌집이 가운데인 경우
		centerHive();

		System.out.println(res);
	}
}
