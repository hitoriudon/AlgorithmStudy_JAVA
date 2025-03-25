/**
 * 태상이의 훈련소 생활
 * 70264kb	568ms
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P19951 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] ground = new int[n + 1]; // indexing
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++){
            ground[i] = Integer.parseInt(st.nextToken());
        }

        int[] order = new int[n + 2]; // (끝 점 + 1)이 중요한 누적합 배열이고, 인덱싱 처리 해줘야해서 +2

        for (int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            order[a] += height;
            order[b + 1] -= height;
        }

        for (int i = 0; i < n + 1; i++){
            order[i + 1] = order[i] + order[i + 1];
        }
    
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n + 1; i++){
            ground[i] += order[i];
            sb.append(String.valueOf(ground[i])).append(" ");
        }
        
        System.out.println(sb);
    }
}
