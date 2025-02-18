import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int answerTime = Integer.MAX_VALUE;
        int answerHeight = -1;
        int totalBlock = B;

        int[][] arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                totalBlock += arr[i][j];
            }
        }

        int time, block;
        for(int h = 0; h <= (totalBlock / (N*M)); h++) {
            time = 0;
            block = B;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(arr[i][j] > h) {
                        time += (arr[i][j] - h) * 2;
                        block += arr[i][j] - h;
                    }else if(arr[i][j] < h){
                        time += (h - arr[i][j]);
                        block -= h - arr[i][j];
                    }
                }
            }
            if(block >= 0) { //가능할때
                if(time < answerTime) {
                    answerTime = time;
                    answerHeight = h;
                }else if(time == answerTime && answerHeight < h) {
                    answerHeight = h;
                }
            }
        }

        System.out.println(answerTime+" "+answerHeight);
    }
}
