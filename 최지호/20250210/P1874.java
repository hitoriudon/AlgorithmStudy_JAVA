import java.io.*;
import java.util.*;

public class P1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        //부호들을 저장하는 공간
        ArrayList<String> answer = new ArrayList<>();
        //입력 값
        Deque<Integer> dq = new ArrayDeque<>();
        
        for(int i = 0; i < n; i++) {
        	dq.add(Integer.parseInt(br.readLine()));
        }
        
        //1~n까지 값을 담아두는 공간(count)
        ArrayList<Integer> count = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
        	count.add(i);
        }
        
        int idx = 0;
        Stack<Integer> stack = new Stack<>();
        while(!dq.isEmpty()) {
        	
        	int target = dq.peekFirst();
	        while(idx < count.size() && count.get(idx) <= target) {
	        	stack.push(count.get(idx));
	        	answer.add("+");
	        	idx ++;
	        }
        	
	        if(!stack.isEmpty() && stack.peek() == target) {
	        	stack.pop();
	        	dq.pollFirst();
	        	answer.add("-");
	        }
	        else {
	        	System.out.println("NO");
	        	return;
	        }
        }
        for(String s : answer) {
        	System.out.println(s);
        }
    }   
}