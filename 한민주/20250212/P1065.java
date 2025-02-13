<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.InputStreamReader;

<<<<<<< HEAD

public class P1065 {
=======
public class Main {
=======
package com.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P1065 {
>>>>>>> 44d22db (add solution 0212)
>>>>>>> 6e7fe7f (add solution 0212)

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int x = Integer.parseInt(br.readLine());
		int ans = 0;
		if (x < 100) {
			System.out.println(x);
		} else if (x < 111)
			System.out.println(99);
		else {
			for (int i = 111; i <= x; i++) {
				int n = i;
				int pre = n % 10;
				n /= 10;
				int now = n % 10;
				int differ = pre - now;
				boolean is = true;

				while (n != 0) {
					if (pre - now != differ) {
						is = false;
						break;
					}
					pre = now;
					n /= 10;
					now = n % 10;
				}
				if (is)
					ans++;
			}
			System.out.println(ans + 99);
		}

	}

<<<<<<< HEAD

}
=======
<<<<<<< HEAD
}
=======
}
>>>>>>> 44d22db (add solution 0212)
>>>>>>> 6e7fe7f (add solution 0212)
