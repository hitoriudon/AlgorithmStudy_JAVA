import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//같은 행 시도 x
//행차이 = 열차이 -> 대각선

//flag 방식으로 풀기 (1~n행)
public class P9663 {
    static boolean[] col, slash, bSlash; // 같은 열, 대각선 좌 우 합 차
    static int n, ans;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new boolean[n+1];
        slash = new boolean[2*n+1];
        bSlash = new boolean[2*n];
        
        ans = 0;
        setQueens(1);
        
        System.out.println(ans);
	}
	
	//어느 행에 두는지 매개변수로 
	static void setQueens(int rowNo) {
		
		//끝에 오면
		if(rowNo > n) {
			ans++;
			return;
		}
		
		for(int c = 1; c <= n; c++) {
			//가지치기 추가
			if(!isAvailable(rowNo,c)) {
				continue;
			}
			
			col[c] = slash[rowNo + c] = bSlash[(rowNo-c)+n] = true;
			setQueens(rowNo+1); // next queen 놓기
			col[c] = slash[rowNo + c] = bSlash[(rowNo-c)+n] = false;
		}
	}
	
	//뭐가 없는지 확인! 다 비어있는지...
	static boolean isAvailable(int rowNo, int c) {
		return !col[c] && !slash[rowNo+c] && !bSlash[(rowNo-c)+n];
	}
}