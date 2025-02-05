import java.io.*;
import java.util.*;

public class ATM_11399 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 버퍼드리더 선언
        int n = Integer.parseInt(br.readLine()); // 문자열로 입력받았으니까 꼭 int 타입으로 변환
        StringTokenizer st = new StringTokenizer(br.readLine()); // 문자열 타입 "숫자"를 공백을 기준으로 분리해 입력받게 됨 
        int answer = 0;

        // 정수 타입으로 변환해서 리스트에 담기
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