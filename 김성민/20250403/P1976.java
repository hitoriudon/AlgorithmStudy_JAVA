import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1976 {
	static int[] parents;

	static int find(int city) {
		if (city == parents[city]) {
			return city;
		}

		return parents[city] = find(parents[city]);
	}

	static void union(int city1, int city2) {
		int city1Root = find(city1);
		int city2Root = find(city2);

		if (city1Root == city2Root) {
			return;
		}

		parents[city2Root] = city1Root;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int cityAmount = Integer.parseInt(br.readLine());
		int visitAmount = Integer.parseInt(br.readLine());

		parents = new int[cityAmount + 1];
		for (int cityCount = 1; cityCount <= cityAmount; cityCount++) {
			parents[cityCount] = cityCount;
		}

		for (int cityCount = 1; cityCount <= cityAmount; cityCount++) {
			st = new StringTokenizer(br.readLine());
			for (int nextCity = 1; nextCity <= cityAmount; nextCity++) {
				int check = Integer.parseInt(st.nextToken());
				if (check == 1) {
					union(cityCount, nextCity);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int startCity = Integer.parseInt(st.nextToken());
		int startCityRoot = find(startCity);
		
		boolean flag = true;
		for(int visitCount = 2; visitCount <= visitAmount; visitCount++) {
			int nextCity = Integer.parseInt(st.nextToken());
			int nextCityRoot = find(nextCity);
			
			if(startCityRoot != nextCityRoot) {
				flag = false;
				break;
			}
		}
		
		System.out.println(flag ? "YES" : "NO");
	}
}
