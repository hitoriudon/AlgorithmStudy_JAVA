import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/*
[18115, 카드 놓기]
[문제]
1. bef의 제일 위의 카드를 내려놓음 after 위에 놓음 -> after의 위 카드를 bef의 제일 위로
2. bef에서 위에서 두번째 카드 after 위에 놓음 (2장 이상 시) -> after의 위 카드를 bef의 위에서 두번째로
3. bef의 제일 밑의 카드 after 위에 내려놓음 (2장 이상 시) -> after의 위의 카드를 bef의 제일 밑으로

[입력]
N (카드 개수)
A (N개의 수열)

[출력]
초기 상태를 출력

[풀이]
output(결과물)에 연산을 반대로 적용
 */

public class P18115 {
	public static void main(String[] args) throws NumberFormatException, IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int n = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());

    	ArrayDeque<Integer> bef = new ArrayDeque<>();
    	ArrayDeque<Integer> after = new ArrayDeque<>();
    	ArrayDeque<Integer> Command = new ArrayDeque<>();
    	
    	for(int i = 1; i <= n; i++) {
    		after.add(i);
    	}
    	
    	while(st.hasMoreTokens()) {
    		Command.push(Integer.parseInt(st.nextToken()));
    	}
    	
    	
    	while(!Command.isEmpty()) {
    		int com = Command.pop();
//    		System.out.println(">>>>>>>> Cur Com : " + com);



    		switch (com) {
			case 1: // after의 위 카드를 bef의 제일 위로
				bef.offerLast(after.pollFirst());
				
				break;

			case 2: // after의 위 카드를 bef의 위에서 두번째로
				
				int temp = bef.pollLast();
				bef.offerLast(after.pollFirst());				
				bef.offerLast(temp);
			
				break;
				
			case 3: // after의 위의 카드를 bef의 제일 밑으로
				bef.offerFirst(after.pollFirst());
				
				break;
				
			}
//    		System.out.println(">>> Rewind..");
//    		System.out.println("bef : " + bef);
//    		System.out.println("after : " + after);


    	}
    
    	StringBuilder sb = new StringBuilder();
    	while(!bef.isEmpty()) {
    		sb.append(bef.pollLast());
    		sb.append(" ");
    	}
    	
    	System.out.println(sb.toString());
    		
    	
    }
}