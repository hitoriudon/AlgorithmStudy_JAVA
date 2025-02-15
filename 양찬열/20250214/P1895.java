import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// P1895 필터(3 * 3 필터링)
// 실수: 필터링을 위한 저장 시 전수 i, j로 저장
public class P1895 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, T;
	static int count;
	static int[] dxs = {-1, 0, 1};
	static int[][] map;
	static PriorityQueue<Integer> sortedValues;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		count = 0;
//		filteredMap = new int[R - 2][C - 2];

		// map 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		T = Integer.parseInt(br.readLine());
		
		// filtering
		for (int i = 1; i < R - 1; i++) {
			for (int j = 1; j < C - 1; j++) {
				// filter의 중앙값 찾기
				sortedValues = new PriorityQueue<>();
				for (int dx: dxs) {
					for (int dy: dxs) {
						sortedValues.add(map[i + dx][j + dy]);
					}
				}
				for (int k = 0; k < 4; k++) {
					sortedValues.poll();
				}
//				System.out.println(min);
				if (sortedValues.peek() >= T) {  // 중앙값
					count++;
				}	
			}
		}
		System.out.println(count);
	}
}
