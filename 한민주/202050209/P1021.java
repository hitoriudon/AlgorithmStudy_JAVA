import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class P1021{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n, m, answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            deque.add(i);
        }

        int objNum, moveN;
        while(st.hasMoreTokens()){
            objNum = Integer.parseInt(st.nextToken());
            
            // 1번
            if(objNum == deque.getFirst()){
                deque.poll();
                continue;
            }

            // 인덱스 계산
            if(deque.indexOf(objNum) <= deque.size()/2){
                // 2번
                while(deque.getFirst() != objNum){
                    moveN = deque.pollFirst();
                    deque.add(moveN);
                    answer++;
                }
            } else{
                // 3번
                while(deque.getFirst() != objNum){
                    moveN = deque.pollLast();
                    deque.addFirst(moveN);
                    answer++;
                }
            }
            deque.pollFirst();
        }
        System.out.println(answer);
    }
}