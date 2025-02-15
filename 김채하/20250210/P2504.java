import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class P2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        int[] plus = new int[15];
        int answer = 1;
        char prev = ' ';
        for(char chr : text.toCharArray()) {
            if(stack.size() > 16) { //길이가 최대 30이기 때문에 스택에 쌓인 괄호가 16개 이상이면 안됨.15개의 쌍까지만 가능
                answer = 0;
                break;
            }
            if(chr == '[' || chr == '(') {
                if (prev == ']' || prev == ')') { //더해야 할때
                    plus[stack.size()] = answer;
                    answer = 1;
                }
                stack.addLast(chr);
            }
            else {
                answer *= chr == ']' ? 3 : 2;
                if (chr == ']') {
                    if (stack.isEmpty() || stack.pollLast() != '[') {
                        answer = 0;
                        break;
                    }
                    answer += plus[stack.size()];
                    plus[stack.size()] = 0;
                }
                if (chr == ')') {
                    if (stack.isEmpty() || stack.pollLast() != '(') {
                        answer = 0;
                        break;
                    }
                    answer += plus[stack.size()];
                    plus[stack.size()] = 0;
                }
            }
            prev = chr;
        }
        if(stack.isEmpty()) {
            System.out.println(answer);
        }else {
            System.out.println(0);
        }

    }
}
