import java.io.*;
import java.util.*;

public class P11399 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력
        int n = Integer.parseInt(br.readLine()); // 숫자 입력
        StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 기준준
        int answer = 0;

        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        
        Collections.sort(arr);
        
        int currentTime = 0;
        for (int i = 0; i < n; i++){
            currentTime += arr.get(i);
            answer += currentTime;
        }

        System.out.println(answer);
    }
}