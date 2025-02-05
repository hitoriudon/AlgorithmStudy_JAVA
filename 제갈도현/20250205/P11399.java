import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P11399 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cnt = 0, res = 0;

		// 동적 배열 할당
		int N = Integer.parseInt(br.readLine());
		List<Integer> lst = new ArrayList<>();
		
		// 인출 시간을 입력받아 배열에 저장
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			lst.add(Integer.parseInt(st.nextToken()));
		}
		
		// 인출 시간이 최솟값이 되도록 정렬
		Collections.sort(lst);
		
		// 최종 인출시간 계산
		for (int i : lst) {
			cnt += i;
			res += cnt;
		}
		System.out.println(res);
	}
}