import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // ���۵帮�� ����
        int n = Integer.parseInt(br.readLine()); // ���ڿ��� �Է¹޾����ϱ� �� int Ÿ������ ��ȯ
        StringTokenizer st = new StringTokenizer(br.readLine()); // ���ڿ� Ÿ�� "����"�� ������ �������� �и��� �Է¹ް� �� 
        
        // ���� Ÿ������ ��ȯ�ؼ� ����Ʈ�� ���
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        System.out.println("n: " + n + " arr: " + arr);
    }
}