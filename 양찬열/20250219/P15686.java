import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P15686 {
	static int N, M;
//	static int[][] map;
	static List<int[]> chickens = new ArrayList<>(M);  // 치킨 집 좌표 저장
	static List<int[]> houses = new ArrayList<>(2*N);
	static int[][] distances;  // 치킨 집과 각 집과의 거리 저장
	static int[] minChickens;  // 집에서 가장 가까운 치킨 집 번호 저장
	static boolean[] isSelected;  // 조합은 재방문하지 않아 필요하지 않으나, 새로 값을 찾아야 하는 경우에만 새로운 최솟값을 찾기 위해 설정
	static int originMin, minDistance;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// 치킨 거리: 각 치킨집과 집 간의 거리를  저장, 조합 M을 계산한 후 다른 최단 거리 확보
	// 어차피 BFS 써도 최악에서는 (N*N - M)*M이긴 하겠군.. 대신 최단 거리이니 M이 줄 수는 있을 듯
	public static void main(String[] args) throws Exception {
		chickens.clear();
		houses.clear();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		map = new int[N][N];
		
		// map 생성
		int value;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				value = Integer.parseInt(st.nextToken());
				if (value == 1) {
					houses.add(new int[] {i, j});
				}
				else if (value == 2) {
					chickens.add(new int[] {i, j});
				}
			}
		}
		
		// 집과 치킨 집의 거리를 비교하여 최솟값 계산
		distances = new int[houses.size()][chickens.size()];
		minChickens = new int[houses.size()];
		originMin = 0;
		int x, y, chickX, chickY, houseMin, currMin, minIdx;
		for (int houseIdx = 0; houseIdx < houses.size(); houseIdx++) {
			x = houses.get(houseIdx)[0];
			y = houses.get(houseIdx)[1];
			houseMin = Integer.MAX_VALUE;
			minIdx = -1;
			for (int chickenIdx = 0; chickenIdx < chickens.size(); chickenIdx++) {
				chickX = chickens.get(chickenIdx)[0];
				chickY = chickens.get(chickenIdx)[1];
				currMin = Math.abs(x - chickX) + Math.abs(y - chickY);
				distances[houseIdx][chickenIdx] = currMin;
				if (houseMin > currMin) {
					houseMin = currMin;
					minIdx = chickenIdx;
				}
			}
			minChickens[houseIdx] = minIdx;
			originMin += houseMin;
		}
		
		// 삐질 치킨 집 구하기
		minDistance = Integer.MAX_VALUE;
		isSelected = new boolean[chickens.size()];
		checkedRemainChickens(0, 0);
		
		System.out.println(minDistance);
	}

	// 폐업시키지 않을 치킨 집
	private static void checkedRemainChickens(int cnt, int start) {
		// TODO Auto-generated method stub
		if (cnt == M) {
			int newMn = checkedMinDistance();
			minDistance = Math.min(newMn, minDistance);
			return;
		}
		
		for (int i = start; i < chickens.size(); i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			checkedRemainChickens(cnt + 1, i + 1);
			isSelected[i] = false;
		}
	}
	
	// 최소 거리 계산
	private static int checkedMinDistance() {
		// TODO Auto-generated method stub
		int newMin = originMin;
		for (int i = 0; i < houses.size(); i++) {
			if(!isSelected[minChickens[i]]) {  // 선택된 집과 가장 가까운 치킨집이 죽은 경우
				newMin -= distances[i][minChickens[i]];
				int min = Integer.MAX_VALUE;
				for (int l = 0; l < chickens.size(); l++) {
					if(isSelected[l] && distances[i][l] < min) {  // 살아있는 치킨 집 && 현재 최소값보다 작음
						min = distances[i][l];
					}
				}
				newMin += min;
				
				// 가지치기
				if (newMin >= minDistance) {
					return newMin;
				}
			}
		}
		return newMin;
	}
}
