package algorithm;

// 메모리: 60060 KB, 시간: 432ms
// 루트를 따로 추적해서 network마다 실제 속한 friend를 관리해줄 필요 없이 숫자만 관리해주면 된다는 사실을
// gpt 선생님에게 배웠습니다. (hashset addall 해주니 터졌어요)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Friend {
	Friend root;
	Network net;
	String name;

	public Friend(String name) {
		this.name = name;
		this.root = this;

	}
}

class Network {
	int cnt;
}

public class P4195 {
	static int rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int test = 0; test < T; test++) {
			int f = Integer.parseInt(br.readLine());
			HashMap<String, Friend> nameH = new HashMap<>();

			for (int i = 0; i < f; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();

				Friend f1 = nameH.get(name1);
				Friend f2 = nameH.get(name2);

				int total = 0;

				// 기존 관계 있는 사람인지 검색
				// 없으면 새로 만들고 있으면 기존 관계 가져오기
				if (f1 == null && f2 == null) {
					f1 = new Friend(name1);
					f2 = new Friend(name2);
					nameH.put(name1, f1);
					nameH.put(name2, f2);
					Network net = new Network();
					f2.root = f1;
					f1.net = net;
					net.cnt = 2;
					total = 2;
				} else if (f1 != null && f2 == null) {
					f2 = new Friend(name2);
					nameH.put(name2, f2);
					f2.root = find(f1);
					f1.root.net.cnt++;
					total = f1.root.net.cnt;
				} else if (f2 != null && f1 == null) {
					f1 = new Friend(name1);
					nameH.put(name1, f1);
					f1.root = find(f2);
					f2.root.net.cnt++;
					total = f2.root.net.cnt;
				} else {
					find(f1);
					find(f2);
					if (f1.root != f2.root) {
						f2.root.net.cnt += f1.root.net.cnt;
						f1.root.root = f2.root;
					}
					total = f2.root.net.cnt;
				}
				sb.append(total);
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

	static Friend find(Friend f) {
		if (f.root.equals(f)) {
			return f;
		} else {
			return f.root = find(f.root);
		}
	}

}