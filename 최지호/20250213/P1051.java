package zho;

import java.io.*;
import java.util.*;

public class P1051 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int m = Integer.parseInt(st.nextToken());
    	
    	//정사각형이 될 수 있는 최대 길이 k
    	int k;
    	if(n > m) {
    		k = m;
    	}
    	else {
    		k = n;
    	}
    	int[][] square = new int[n][m];
    	
    	//사각형 입력받기
    	for(int i = 0; i < n; i++) {
    		String input = br.readLine();
    		for(int j = 0; j < m; j++) {
    			square[i][j] = input.charAt(j) - '0';
    		}
    	}
    	int x;
    	int y;
    	for(int i = k; i >= 0; i--) {
    		//오른쪽 탐색
    		x = 0;
    		y = 0;
    		while(i + y < n) {
    			//이 x = 0 초기화 안해주는것때무네 계속 틀려서 이부분만 gpt의 도움 받았습니다..
    			x = 0;
    			///
	    		while(i + x < m) {
	    			int leftTop = square[y][0 + x];
	    			int rightTop = square[y][i + x];
	    			int leftBot = square[i + y][0 + x];
	    			int rightBot = square[i + y][i + x];	    			
	    			if(leftTop == rightTop && rightTop == rightBot && leftBot == rightBot) {
	    				System.out.println((i+1)*(i+1));
	    				return;
	    				}
	    			x++;
	    		}
	    		y++;
    		}
    	}
    	System.out.println(1);
    }  
}