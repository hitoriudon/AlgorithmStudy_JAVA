/**
 * 꿀 따기
 * 
 * 시간 제한이 1초인데 N 사이즈 1차원 배열의 사이즈 최대값이 10만이다? 반복문 두 번 돌리면 무조건 터짐
 * 누적합인 걸 알고 있어서 더 빠르게 풀었지만, input Size만 보고 완탐을 피하는 생각을 가져야겠당
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21758 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] honey = new int[n + 1];
        int[] prefixSum = new int[n + 1];
        
        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++){
            int ret = Integer.parseInt(st.nextToken());
            sum += ret;
            honey[i] = ret;
            prefixSum[i] = sum;
        }

        int maxValue = 0;
        
        // 벌 2마리 사이에 벌집이 있는 경우부터 체크
        for (int houseIndex = 2; houseIndex < n; houseIndex++){
            int leftHoney = prefixSum[houseIndex] - prefixSum[1];
            int rightHoney = prefixSum[n - 1] - prefixSum[houseIndex - 1];
            maxValue = Math.max(maxValue, leftHoney + rightHoney);
        }
        
        // 벌집이 왼쪽 끝이나 오른쪽 끝에 있는 경우 체크
        // 맨 왼쪽에 벌집이 있다고 가정하면 벌 하나는 맨 오른쪽에 있어야 Greedy하게 최대값
        // 나머지 벌 하나 인덱스 순차적으로 변경하면서 최대값 찾기, O(n)
        for (int beeIndex = 2; beeIndex < n; beeIndex++){
            int lessHoney = prefixSum[beeIndex - 1];
            int moreHoney = prefixSum[n - 1] - honey[beeIndex];
            
            maxValue = Math.max(maxValue, lessHoney + moreHoney);
        }

        // 맨 오른쪽에 벌집이 있다고 가정하면 벌 하나는 맨 왼쪽에 있어야 Greedy하게 최대값
        // 나머지 벌 하나 인덱스 순차적으로 변경하면서 최대값 찾기, O(n)
        for (int beeIndex = 2; beeIndex < n; beeIndex++){
            int lessHoney = prefixSum[n] - prefixSum[beeIndex];
            int moreHoney = prefixSum[n] - prefixSum[1] - honey[beeIndex]; 
            
            maxValue = Math.max(maxValue, lessHoney + moreHoney);
        }

        System.out.println(maxValue);
    }    
}