
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 피보나치의 인풋 최대가 10^18이라서 이건 O(n)도 택도 없다
 */

public class P11444 {
    static final long MOD = 1000000007;
    static long[][] firstA = {{1, 1} , {1, 0}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        long[][] A = {{1, 1}, {1, 0}};

        System.out.println(func(A, n - 1)[0][0]); // 순서대로 An-1에는 Fn, Fn-1, Fn-1, Fn-2가 들어있음
    }

    public static long[][] func(long[][] A, long b){
        if (b == 1 || b == 0)
            return A;
        
        long[][] temp = func(A, b / 2);

        temp = mul(temp, temp);
        
        if (b % 2 == 1L){
            temp = mul(temp, firstA);
        }
        
        return temp;
    }

    public static long[][] mul(long[][] m1, long[][] m2){
        long[][] temp = new long[2][2]; // 생각해보니 global n이 필요 없음 그냥 2*2 고정이라

        for (int k = 0; k < 2; k++){
            for (int i = 0; i < 2; i++){
                for (int j = 0; j < 2; j++){
                    temp[i][j] += m1[i][k] * m2[k][j];
                    temp[i][j] %= MOD; // 이거 1000으로 아까 거 그대로 복붙했다가 틀림ㅋㅋ
                }
            }
        }

        return temp;
    }
}
