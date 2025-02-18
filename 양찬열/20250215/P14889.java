
// 팀 나누기 -> subset

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14889 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] start, link;
	static int[][] power;
	static int N;
	static int minDiff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		start = new int[N/2];
		link = new int[N/2];
		power = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				power[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// N/2를 골라 팀 생성
		findTeams(0, 0, 0);  // 선택한 팀원, 찾아본 팀원
		System.out.println(minDiff);
	}

	private static void findTeams(int idx, int member, int found) {
		// TODO Auto-generated method stub
		if (member == N/2) {  // 팀원 전체 선택
			int sumStart = 0;
			for (int i : start) {
				for (int j : start) {
					sumStart += power[i][j];
				}
			}
			
			// link 계산
			int sumLink = 0;
			int startIdx = 0;
			for (int i = 0; i < N; i++) {
				if (startIdx < N/2 && i == start[startIdx]) {
					startIdx++;
					continue;
				}
				
				link[i - startIdx] = i;
			}
			// link 총합 구하기
			for (int i : link) {
				for (int j : link) {
					sumLink += power[i][j];
				}
			}
			
			int diff = Math.abs(sumLink - sumStart);
			minDiff = diff < minDiff?diff:minDiff;
//			System.out.println(sumLink + " " + sumStart);
			return;
		}
		
		if (N - found + member < N/2) return;  // 팀원 숫자 안 맞음
		
		start[member] = idx;
		findTeams(idx + 1, member + 1, found + 1);  // 선택
		start[member] = 0;
		
		findTeams(idx + 1, member, found + 1);  // 선택 X
		
		return;
	}
}
