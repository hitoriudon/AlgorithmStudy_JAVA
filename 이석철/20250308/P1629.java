
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 곱셈
 * 인풋이 int 맥스 범위 값임
 * 지수 법칙: a^(n + m) = a^m + a^n -> 분할 정복으로 해결 가능
 */
public class P1629 {
    static long c;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        System.out.println(func(a, b));
    }

    public static long func(long a, long b){
        // 기저
        if (b == 1){
            return a % c;
        }

        // 분할 정복
        long temp = func(a, b / 2);

        if (b % 2 == 1) // b(지수)가 홀수면, a를 딱 한 번 더 곱해줘야 한다
            return (temp * temp % c) * a % c; // 이렇게 모듈러 계속 안 해주면 터질듯

        return temp * temp % c;
    }

}

