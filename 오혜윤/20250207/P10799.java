import java.io.*;
import java.util.ArrayList;

// [실버 2] 10799번. 쇠막대기
public class P10799 {
    public static void main(String[] args) throws Exception {
        Solution1();
        Solution2();
    }

    // #1. 불필요한 자료구조 및 반복문 사용 (lastIndexOf, 반복문 등)
    // 메모리 17064 KB, 시간 2180 ms
    static void Solution1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int result = 0;

        ArrayList<Character> bar = new ArrayList<>();
        for(Character c : input.toCharArray()) {
            if (c.equals(')')) {    // ')'일 때
            int lastIndex = bar.size() - 1;

                // 이전 괄호가 '('면 레이저 * 추가
                if (bar.get(lastIndex).equals('(')) {
                    bar.remove(lastIndex);
                    bar.add('*');
                }

                // 이전 괄호가 '*'면 가장 최근에 추가한 '('의 Index 찾기
                // 가장 최근의 '('와 현재 ')' 사이에 있는 *의 수 + 1만큼 result에 추가
                else if (bar.get(lastIndex).equals('*')) {
                    // 절단한 쇠막대기의 수
                    int cnt = 1;

                    // 가장 최근에 추가한 '('의 Index (쇠막대기 시작 지점)
                    int lastOpenBrackets = bar.lastIndexOf('(');

                    // 중간에 레이저가 있으면 절단 막대기 수 + 1
                    for (int i = lastOpenBrackets; i <= lastIndex; i++) {
                        if (bar.get(i).equals('*')) cnt += 1;
                    }

                    result += cnt;
                    bar.remove(lastOpenBrackets);
                }

            } else bar.add(c);  // '('이면 bar에 추가
        } System.out.println(result);
    }

    // #2. 단순 변수 및 연산을 주로 활용
    // 메모리 14000 KB, 시간 84 ms
    static void Solution2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int result = 0; // 전체 쇠막대기 수
        int cnt = 0;    // 현재 쇠막대기 수

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // '('면 쇠막대기 개수 1개 추가 (쇠막대기 시작 or 레이저 시작)
            if (c == '(') cnt++;

            // ')'인 경우
            else {
                cnt--;
                // 이전 괄호가 '(' -> 레이저 -> 현재 남은 쇠막대기가 모두 잘림
                if (input.charAt(i - 1) == '(') result += cnt;

                // 이전 괄호가 ')' -> 쇠막대기 종료 -> 1개 추가
                else result++;
            }
        } System.out.println(result);
    }
}
