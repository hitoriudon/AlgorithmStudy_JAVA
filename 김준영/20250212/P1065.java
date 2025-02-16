import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.sql.rowset.serial.SQLOutputImpl;

/*
[1065, 한수]
 */

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		
//		===== 반복
		for(int input = 1; input <= n; input++) {
			if(input < 100) {
				cnt ++;
			}
			else {
//				System.out.println("현재 수 : " + input);

				boolean possible = true;
				int cur = input;
				int diff = (cur%10) - ((cur%100)/10);
//				System.out.println("첫자리 - 두번째 자리" + diff);
				
				while(cur >= 10) {
					int temp = (cur%10) - ((cur%100)/10);
					if(temp!= diff) {
						possible = false;
						break;
					}
					cur /= 10;
				}
				
				if(possible) {
//					System.out.println("Cur" + cur);
					cnt ++;
				}
			}
			
			
		}
		
		System.out.println(cnt);
	
	}
}