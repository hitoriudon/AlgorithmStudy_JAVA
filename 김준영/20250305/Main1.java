// [1759 암호 만들기]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int l,c;
    static ArrayList<Character> words;
    static char[] res;
    static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken()); // l개의 알파벳 소문자로 구성 (한개 이상의 모음, 두개 이상의 자음)
        c = Integer.parseInt(st.nextToken()); // 문자들
        
        visited = new boolean[c];
        res = new char[l];
        		
        words = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
        	words.add(st.nextToken().charAt(0));
        }
        Collections.sort(words);
        
//        System.out.println("Sorted WORDS : "s + words);
        
        dfs(0, 0);
        
        System.out.println(sb);
    }
    
    static void dfs(int depth, int start_idx) {
    	if(depth == l) { // l개의 단어를 뽑은 경우
    		
    		int mo = 0; // 1개 이상의 모음
    		int ja = 0; // 2개 이상의 자음
    		
    		for(char val : res) {
    			if(val == 'a' || val == 'e' || val == 'i' || val == 'o' || val == 'u') mo ++;
    			else ja++;
    		}
    		
    		if(mo >= 1 && ja >= 2) {
    			for(char val : res) {
    				sb.append(val);
    			}
    			
    			sb.append("\n");    			
    		}
    		return;	
    	}
    	
    	for(int i = start_idx; i < words.size(); i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			res[depth] = words.get(i);
    			dfs(depth + 1, i + 1);
    			visited[i] = false;
    		}
    	}
    }
}