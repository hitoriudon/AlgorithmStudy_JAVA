import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class P10773 {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int k = Integer.parseInt(br.readLine());
		int answer = 0;
		List<Integer> list = new ArrayList();
		int input;
		for (int i = 0; i < k; i++) {
			input = Integer.parseInt(br.readLine());
			list.add(input);
		}

		int zeroCnt = 0;
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) == 0) {
				zeroCnt++;
			} else if (zeroCnt == 0) {
				answer += list.get(i);
			} else
				zeroCnt--;

		}

		System.out.println(answer);

	}
}