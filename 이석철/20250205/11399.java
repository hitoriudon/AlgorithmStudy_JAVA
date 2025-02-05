import java.io.*;
import java.util.*;

public class P11399 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // ���۵帮�� ����
        int n = Integer.parseInt(br.readLine()); // ���ڿ��� �Է¹޾����ϱ� �� int Ÿ������ ��ȯ
        StringTokenizer st = new StringTokenizer(br.readLine()); // ���ڿ� Ÿ�� "����"�� ������ �������� �и��� �Է¹ް� �� 
        int answer = 0;

        // ���� Ÿ������ ��ȯ�ؼ� ����Ʈ�� ���
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