import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068 {
	
	static ArrayList<Integer>[] graph;
	static int leafAmount = 0;
	
	static int removeNodeNum;
	
	static void dfs(int curNodeNum) {
		int childCount = 0;
		for(int childNodeNum : graph[curNodeNum]) {
			if(childNodeNum == removeNodeNum) {
				continue;
			}
			childCount++;
			dfs(childNodeNum);
		}

		// 탐색한 자식이 없다면 리프 추가
		if(childCount == 0) {
			leafAmount++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int numAmount = Integer.parseInt(br.readLine());
		// 그래프 직접 그리기
		graph = new ArrayList[numAmount];
		for(int numCount = 0; numCount < numAmount; numCount++) {
			graph[numCount] = new ArrayList<>();
		}
		
		int root = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int numCount = 0; numCount < numAmount; numCount++) {
			int parent = Integer.parseInt(st.nextToken());
			// 부모가 -1이 아니라면 부모 노드에 자식 추가
			if(parent != -1) {
				graph[parent].add(numCount);
			} 
			// 부모가 -1이라면 root
			else {
				root = numCount;
			}
		}
		
		removeNodeNum = Integer.parseInt(br.readLine());
		
		// root가 삭제 될 경우 탐색 자체를 X
		if(removeNodeNum != root) {
			dfs(root);
		}
		
		System.out.println(leafAmount);
	}
	
}
