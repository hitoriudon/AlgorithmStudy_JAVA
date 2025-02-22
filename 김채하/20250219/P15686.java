import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P15686 {
	static int answer = Integer.MAX_VALUE;
	static int N,M;
	static int[] minDistance;
	static int[][] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ArrayList<int[]> chicken = new ArrayList<>();
		ArrayList<int[]> house = new ArrayList<>();
		String val;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j ++) {
				val = st.nextToken();
				if(val.equals("1")) 
					house.add(new int[] {i,j});
				if(val.equals("2")) 
					chicken.add(new int[] {i,j});				
			}
		}
		
		distance = new int[chicken.size()][house.size()];
		
		int x,y;
		//미리 각 치킨집과 집의 거리를 계산
		for (int i = 0; i < chicken.size(); i++) {
			for (int j = 0; j < house.size(); j++) {
				x = Math.abs(chicken.get(i)[0] - house.get(j)[0]);
				y = Math.abs(chicken.get(i)[1] - house.get(j)[1]);
				distance[i][j] = x + y;
			}
		}
		
		minDistance = new int[house.size()];
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		
		backtracking(0, 0, 0);
		
		System.out.println(answer);
	}
	
	static void backtracking(int idx, int cnt, int dis) {
		if(cnt >= M) {
			answer = Math.min(answer, dis);
			return;
		}
		
		if(idx + 1 > distance.length) {
			return;
		}
		
		
		int[] newDistance = minDistance.clone();
		backtracking(idx+1, cnt, dis);
		
		int newDis = 0;
		for(int i = 0; i < minDistance.length; i++) {
			minDistance[i] = Math.min(minDistance[i], distance[idx][i]);
			newDis += minDistance[i];
		}
		
		backtracking(idx+1, cnt+1, newDis);
		minDistance = newDistance;
	}
	
}
