import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P2615 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] direct = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
		int[] answer = new int[3];
		boolean[][][] visited = new boolean[19][19][8];
		int[][]	badukpan = new int[19][19];
		StringTokenizer st;

		for (int i = 0; i < badukpan.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < badukpan.length; j++) {
				badukpan[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ArrayDeque<int[]> stack;
		for (int x = 0; x < badukpan.length; x++) {
			for (int y = 0; y < badukpan.length; y++) {
				if(badukpan[x][y] != 0) {
					stack = new ArrayDeque<>();
					for (int i = 0; i < direct.length; i++) {
						stack.add(new int[] {x,y,i,1,badukpan[x][y]});						
					}
					while(!stack.isEmpty()) {
						int[] values = stack.pop();
						int directIdx = values[2];
						int dx = values[0] + direct[directIdx][0];
						int dy = values[1] + direct[directIdx][1];
						visited[values[0]][values[1]][directIdx] = true;
						if(dx >= 0 && dx < badukpan.length && dy >= 0 && dy < badukpan.length && badukpan[dx][dy] == values[4] && !visited[dx][dy][directIdx]) {
							values[0] = dx;
							values[1] = dy;
							values[3]++;
							stack.add(values);
						}else {
							if(values[3]==5) {
								answer[0] = values[4];
								if(direct[directIdx][0] == 1)
									answer[1] = values[0] - direct[directIdx][0] * 5; 
								else answer[1] = values[0]+1;
								if(direct[directIdx][1] == 1)
									answer[2] = values[1] - direct[directIdx][1] * 5; 
								else answer[2] = values[1]+1;
							}
						}
					}
				}
			}
		}
		System.out.println(answer[0]);
		if(answer[0] != 0) {
			System.out.println((answer[1]+1)+ " "+ (answer[2]+1));						
		}
	}

}