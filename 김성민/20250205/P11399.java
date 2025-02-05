import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11399 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		int humanAmount = Integer.parseInt(br.readLine());
		String timeLine = br.readLine();
		st = new StringTokenizer(timeLine, " ");
		
		ArrayList<Integer> times = new ArrayList<>();
		for(int humanCount = 0; humanCount < humanAmount ; humanCount++) {
			times.add(Integer.parseInt(st.nextToken())); 
		}
		times.sort(null);
		
		int result = 0;
		for(int humanCount = 0; humanCount < humanAmount ; humanCount++) {
			result += times.get(humanCount) * (humanAmount - humanCount);
		}
		
		System.out.println(result);
	}
}