import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
[1021, 회전하는 큐]
[문제]
N개의 원소를 포함한 양방향 순환 큐
연산
	1. 첫 원소 뽑음
	2. 첫 원소 뽑아 뒤에 넣음
	3. 마지막 원소 뽑아 앞에 넣음
[입력]
N (큐의 크기) (N<50) M (뽑으려는 수의 개수) (M <= N)
뽑으려는 수의 

[출력]
초기 상태를 출력

[풀이]

 */

public class P1021 {
	public static void main(String[] args) throws NumberFormatException, IOException {

    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken()); // 큐의 크기
    	int m = Integer.parseInt(st.nextToken()); // 뽑으려는 수의 개수
    	
    	LinkedList<Integer> want = new LinkedList<>(); // 뽑으려는 수
    	LinkedList<Integer> q = new LinkedList<>(); // 원소의 위치 (1~n)
    	
    	st = new StringTokenizer(br.readLine());
    	
    	for(int i = 1; i <= n ; i++) {
    		q.offer(i);
    	}
    	
    	
    	while(m-- > 0) {
    		want.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	
    	int cnt = 0;

    	while(!want.isEmpty()) {
//    		System.out.println("=====");
    		int wantV = want.getFirst();
//    		System.out.println("want " + wantV);
//    		System.out.println("q : " + q);
    		
    		if(q.getFirst()==wantV) {
    			q.remove(0);
    			want.remove(0);
//    			System.out.println("pop~~~");
    		} else {
    			if(q.size() - q.indexOf(wantV) < q.indexOf(wantV)) {
    				q.offerFirst(q.pollLast());
    				cnt ++;
    			}else {
    				q.offerLast(q.pollFirst());
    				cnt ++;
    			}
    		}
    		
//    		System.out.println("cnt :" + cnt);
//    		System.out.println("@@@@@");
    	}
    	
    	

    	System.out.println(cnt);
    	
    	
    }
}