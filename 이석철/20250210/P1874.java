import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P1874 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    List<Integer> A = new ArrayList<>();
    int n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
        A.add(Integer.parseInt(br.readLine()));
    }

    Stack<Integer> B = new Stack<>();
    List<Character> plusAndMinus = new ArrayList<>(); // 쁠마

    int currentValue = 1;
    int aIndex = 0;

    // 현재 최대값을 가지고 있고, next input 값이 최대값보다 크면 ++하면서 가져오고
    // 작으면 기존 스택에서 --하면서 가져와야 함
    // 이 때, 5에서 3으로 갈 때 4가 스택에 맨 윗요소면 안 되겠지?
    // --할 때 스택의 가장 마지막 인덱스에 필요한 숫자가 위치해야 함.

    while (aIndex < n) {
        int findValue = A.get(aIndex);

        while (currentValue <= findValue) {
            B.push(currentValue++);
            plusAndMinus.add('+');
        }

        if (!B.isEmpty() && B.peek() == findValue) {
            B.pop();
            plusAndMinus.add('-');
            aIndex++;
        } else {
            System.out.println("NO");
            return;
        }
    }

    for (char c : plusAndMinus) {
        System.out.println(c);
    }
}
}