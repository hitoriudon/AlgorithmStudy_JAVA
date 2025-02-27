import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068 {
	static ArrayList<Integer>[] tree;
	static int root, removedNode, result = 0;

	static void searchLeaf(int curr) {
		// 첫 탐색 시 루트 노드가 삭제된 경우
		if (curr == removedNode)
			return;

		// 자식이 없다면 결과 카운트
		if (tree[curr].isEmpty()) {
			result++;
			return;
		}

		// 자식이 하나인데 삭제된 경우 리프 노드가 되어버림
		if (tree[curr].size() == 1 && tree[curr].get(0) == removedNode) {
			result++;
			return;
		}
		
		// 자식 노드 탐색
		for (int next : tree[curr]) {
			// 만약 자식 노드가 지워진 경우 탐색하지 않음
			if (next != removedNode)
				searchLeaf(next);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		// ArrayList를 가지고 있는 배열 tree 생성
		tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			// 이후 각 칸을 ArrayList로 할당
			tree[i] = new ArrayList<>();
		}

		int input;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input = Integer.parseInt(st.nextToken());

			if (input == -1) {
				// 만약 -1이라면 루트 노드
				root = i;
			} else {
				// 아니라면 자식 추가
				tree[input].add(i);
			}
		}

		// 특정 노드 삭제
		removedNode = Integer.parseInt(br.readLine());

		// 리프 노드 탐색 시작
		searchLeaf(root);

		System.out.println(result);
	}
}