import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P18115 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
        StringBuilder sb = new StringBuilder();
		
		// 시작점과 끝점을 모두 이용하기 위해 Deque 이용
		Deque<Integer> myDeq = new LinkedList<>();

		int cardAmount = Integer.parseInt(br.readLine());

		// 명령어 역순으로 넣기
		String orderLine = new StringBuilder(br.readLine()).reverse().toString();
		st = new StringTokenizer(orderLine, " ");
        for (int num = 1; num <= cardAmount; num++) {
            int order = Integer.parseInt(st.nextToken());

            // 맨 위에 두기
            if (order == 1) {
            	myDeq.addLast(num);
            } 
            // 맨 위에 빼고 위에 올린 뒤 맨 위에 다시 두기
            else if (order == 2) {
                int top = myDeq.removeLast();
                myDeq.addLast(num);
                myDeq.addLast(top);
            } 
            // 맨 아래 두기
            else {
            	myDeq.addFirst(num);
            }
        }
        
        while(!myDeq.isEmpty()) {
        	sb.append(myDeq.removeLast()).append(" ");
        }
        
        System.out.println(sb);
	}
	
}
