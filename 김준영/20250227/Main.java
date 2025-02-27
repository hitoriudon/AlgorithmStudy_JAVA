import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static ArrayList<Integer>[] mySon;
	static int target;
	static int res;
	static int root;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		mySon = new ArrayList[n];
		res = 0;
		root = 0;
		target = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			mySon[i] = new ArrayList<>();
		}

		for (int i = 0; i < n; i++) {

			int input = Integer.parseInt(st.nextToken());

			if (input != -1) {
				mySon[input].add(i); // ...
			} else {
				root = i;
			}
		}

//		System.out.println("Target : " + target );
//		System.out.println("Root : " + root);
//		for (int i = 0; i < n; i++) {
//			System.out.println(i + " : 내 자식은.. " + mySon[i]);
//		}
		
		if(n!=1) {
			mySon[target].add(0, -1);

			findLeaf(root);
		}
		System.out.println(res);
	}
	
	
	static void findLeaf(int cur) {
		if(cur == -1) { // -1인경우 자식들은 죽음..
			return;
		}
		if(mySon[cur].isEmpty()) {
			res++;
			return;
		}
		for(int son : mySon[cur]) {
			if(son == -1) break;
			findLeaf(son);
		}
		

		
	}
}