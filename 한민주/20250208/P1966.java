import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P1966{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n, m, answer, p1, p2;
        int[] importance;
        int[] papers = new int[100];
        StringTokenizer st;

        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            answer = 0;
            p1 = 0;
            p2 = 9;
            importance = new int[10];

            st = new StringTokenizer(br.readLine());
            for(int i = 0;i < n; i++){
                papers[i] = Integer.parseInt(st.nextToken());
                importance[papers[i]]++;
            }

            while(true){
                answer++;
                while(importance[p2] == 0){
                    p2--;
                }

                while(papers[p1] != p2){
                    p1++;
                    if(p1 == n) p1= 0;
                }

                papers[p1] = 0;
                importance[p2]--;
                if(p1 == m){
                    break;
                }
            }

            sb.append(answer);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}