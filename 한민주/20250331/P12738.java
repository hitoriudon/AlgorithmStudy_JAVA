package algorithm;

// 메모리: 168748 KB, 시간: 668 ms
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P12738 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		ArrayList<Long> list = new ArrayList<>();
		while(st.hasMoreTokens()) {
			long a = Integer.parseInt(st.nextToken());
			
			if(list.isEmpty() || a > list.get(list.size() - 1)) {
				list.add(a);
				continue;
			}
			int s = 0;
			int e = list.size() - 1;
			int idx = 0;
			while(s <= e) {
				int mid = (s + e) / 2;
				if(list.get(mid) == a) {
					idx = mid;
					break;
				}else if(list.get(mid) > a) {
					idx = mid;
					e = mid - 1;
				}else {
					s = mid + 1;
				}
			}
			list.set(idx, a);
		}
		
		System.out.println(list.size());
	}

}
