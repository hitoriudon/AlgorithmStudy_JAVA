import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class s3_18115 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		ArrayDeque<Integer> res = new ArrayDeque<>();
		int skill, tmp;
		
		// Ai가 x이면, i번째로 카드를 내려놓을 때 x번 기술을 썼다
		// -> 스킬을 반대로 사용해야 함
		int N = Integer.parseInt(br.readLine());
		int[] skillList = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = N-1; i >= 0; i--) skillList[i] = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= N; i++) {
			skill = skillList[i-1];
			
			if (skill == 1) {
				// 제일 위의 카드 1장을 바닥에 -> 제일 앞으로
				res.addFirst(i);
			} else if (skill == 2) {
				// 위에서 두 번째 카드를 바닥에 -> 앞에서 두 번째로
				tmp = res.removeFirst();
				res.addFirst(i);
				res.addFirst(tmp);
			} else {
				// 제일 밑에 있는 카드를 바닥에 -> 제일 뒤로
				res.addLast(i);
			}
		}
		
		for (int r : res) {
			sb.append(r).append(" ");
		}
		System.out.println(sb);
	}
}