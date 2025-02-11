import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

// 실버 5. 1181번 단어 정렬
public class P1181 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 단어의 개수
        String[] arr = new String[N];

        for (int n = 0; n < N; n++) {
            arr[n] = br.readLine();
        }

        Arrays.sort(arr, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                // 길이 같을 시 사전순 정렬
                if (s1.length() == s2.length()){
                    return s1.compareTo(s2);
                } else{ // 길이 정렬
                    return s1.length() - s2.length();
                }
            }
        });

        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append('\n');
        for(int i = 1; i < N; i++){
            if(!arr[i].equals(arr[i-1])) {
                sb.append(arr[i]).append('\n');
            }
        }System.out.println(sb);
    }
}