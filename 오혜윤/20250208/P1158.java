import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// [실버3] 1158번. 요세푸스 문제
public class P1158 {
    public static void main(String[] args) throws Exception {
        Solution1();
        Solution2();
    }


    // #1. Queue 사용 : K번째보다 앞에 있는 수를 뒤로 옮기기
    // 메모리 292320 KB, 시간 528 ms
    static void Solution1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 출력 문자열
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) q.offer(i);

        // Queue에 1개만 남을 때 까지
        while(q.size() > 1) {
            // K번째 요소 전에 있는 요소들은 뒤로 이동
            for (int i = 1; i < K; i++) q.offer(q.poll());

            // K번째 요소 뽑아서 StringBuilder에 저장
            sb.append(q.poll()).append(", ");
        }
        // 마지막 요소 입력 후 괄호 닫기
        sb.append(q.poll()).append(">");
        System.out.println(sb);
    }

    
    // #2. ArrayList 사용 + 인덱스 활용 연산
    // 메모리 12312 KB, 시간 80 ms
    static void Solution2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 출력 문자열
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) list.add(i);
        int idx = 0;
        while(list.size() > 1) {
            // 다음 인덱스가 리스트 크기 내에 있으면 그대로 진행
            if (idx + (K - 1) < list.size()) idx += (K - 1);
            // 다음 인덱스가 리스트 크기보다 크면 % 연산 진행
            else idx = (idx + (K - 1)) % list.size();

            sb.append(list.get(idx)).append(", ");
            list.remove(idx);
        }
        sb.append(list.get(0)).append(">");
        System.out.println(sb);
    }
}
