import java.io.*;
import java.util.*;

public class P1895 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int r = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	
    	int[][] map = new int[r][c];
    	ArrayList<Integer> jList = new ArrayList<>();
    	ArrayList<Integer> tmpList = new ArrayList<>();
    	
    	for(int i = 0; i < r; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < c; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int t = Integer.parseInt(br.readLine());
    	    		
    	for(int i = 0; i < r - 2; i++) {
    		for(int j = 0; j < c - 2; j++) {
    			
    			//j 만들기
    			for(int k = i; k < i + 3; k++) {
    				for(int q = j; q < j + 3; q++) {
    					tmpList.add(map[k][q]);
    				}
    			}
    			tmpList.sort(Comparator.naturalOrder());
    			int midVal = tmpList.get(4);
    			jList.add(midVal);
    			tmpList.clear();
    		}
    	}
//    	System.out.println(jList);
    	int count = 0;
    	for(int n : jList) {
    		if(n >= t) {
    			count++;
    		}
    	}
    	System.out.println(count);
    }  
}