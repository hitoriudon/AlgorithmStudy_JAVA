import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2458 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int humanAmount = Integer.parseInt(st.nextToken());
		int relationAmount = Integer.parseInt(st.nextToken());

		// 앞에보다 뒤에가 키가 큰지 알 수 있는지 체크
		boolean[][] relation;
		relation = new boolean[humanAmount + 1][humanAmount + 1];

		for (int i = 0; i < relationAmount; i++) {
			st = new StringTokenizer(br.readLine());

			int human1 = Integer.parseInt(st.nextToken());
			int human2 = Integer.parseInt(st.nextToken());

			// human1가 human2보다 작다 확인이 가능
			relation[human1][human2] = true;
		}

		// 플로이드-워셜 응용: 중간-출발-도착 : 3중 for문
		// human3을 통해서 human1가 human2보다 작은지 확인 가능하면 추가
		for (int human3 = 1; human3 <= humanAmount; human3++) {
			for (int human1 = 1; human1 <= humanAmount; human1++) {
				for (int human2 = 1; human2 <= humanAmount; human2++) {
					if(relation[human1][human3] && relation[human3][human2]) {
						relation[human1][human2] = true;
					}
				}
			}
		}

		int result = 0;
		for (int human1 = 1; human1 <= humanAmount; human1++) {
			// 몇명을 알고 있는지 체크
			int knowCount = 0;
			for (int human2 = 1; human2 <= humanAmount; human2++) {
				// human2에 대해서 나보다 작은지 큰지에 대한 관계를 알고 있다면 추가
				if (relation[human1][human2] || relation[human2][human1]) {
					knowCount++;
				}
			}
			// 나를 제외하고 모두의 정보와 이어졌다면 결과에 추가
			if (knowCount == humanAmount - 1)
				result++;
		}

		System.out.println(result);
	}

}