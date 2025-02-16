import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1895 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		// 배열 초기화
		int[][] origImg = new int[R][C];
		ArrayList<Integer> filter = new ArrayList<>();
		
		// 이미지 배열 값 저장
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				origImg[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		int res = 0;
		// 이미지를 순회하여 작업 실시
		for (int i = 0; i < R-2; i++) {
			for (int j = 0; j < C-2; j++) {
				// 필터 초기화
				filter.clear();
				
				// 필터에 값 저장
				filter.add(origImg[i][j]);
				filter.add(origImg[i][j+1]);
				filter.add(origImg[i][j+2]);
				filter.add(origImg[i+1][j]);
				filter.add(origImg[i+1][j+1]);
				filter.add(origImg[i+1][j+2]);
				filter.add(origImg[i+2][j]);
				filter.add(origImg[i+2][j+1]);
				filter.add(origImg[i+2][j+2]);
				
				// 필터를 정렬한 뒤 중앙값이 T보다 크거나 같으면 결과에 저장
				Collections.sort(filter);
				if (filter.get(4) >= T) res++;
			}
		}
		
		System.out.println(res);
	}
}