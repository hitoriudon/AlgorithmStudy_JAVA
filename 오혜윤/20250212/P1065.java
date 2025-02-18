// [실버4] 1065번. 한수

import java.io.*;

class P1065 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());

        if (X < 100)
            System.out.println(X);
        else if (X < 111)
            System.out.println(99);
        else {
            int result = 99; // 1부터 99까지는 무조건 한수이기 때문에 미리 할당

            for (int i = 111; i <= X; i++) {
                // num1 : 백의 자리 숫자, num2 : 십의 자리 숫자, num3 : 일의 자리 숫자
                int num1 = i / 100;
                int num2 = (i - num1 * 100) / 10;
                int num3 = (i - (num1 * 100) - (num2 * 10));

                if (((num3) - num2) == (num2 - num1))
                    result++;
            }
            System.out.println(result);
        }
    }
}