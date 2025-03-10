
/**
 * 암호 만들기 전략
 * 
 * 비트마스킹으로 조합(nCr) 구하고 모음 최소 1개, 자음 최소 2개인 배열만 sort해준 뒤 
 * string으로 바꿔서 배열에 넣어주고 그 배열을 정렬
 * 그럼 사전 순으로 정렬됨
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P1759 {
    static char[] alphabet;
    static ArrayList<String> password;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        alphabet = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        password = new ArrayList<>();
        for (int mask = 0; mask < (1 << c); mask++) {
            if (Integer.bitCount(mask) == l) {
                ArrayList<Integer> selected = new ArrayList<>();
                for (int i = 0; i < c; i++) {
                    if ((mask & (1 << i)) != 0) {
                        selected.add(i);
                    }
                }
                checkPassword(selected);
            }
        }
        Collections.sort(password);
        for (int i = 0; i < password.size(); i++) {
            System.out.println(password.get(i));
        }
    }

    public static void checkPassword(ArrayList<Integer> selected) {
        ArrayList<Character> selectedAlphabet = new ArrayList<>();
        for (int i = 0; i < selected.size(); i++) {
            selectedAlphabet.add(alphabet[selected.get(i)]);
        }

        boolean vowelsFlag = false;
        int consonantsCount = 0;
        for (int i = 0; i < selectedAlphabet.size(); i++) {
            Character currentAlphabet = selectedAlphabet.get(i);
            if (currentAlphabet == 'a' || currentAlphabet == 'e' || currentAlphabet == 'i' || currentAlphabet == 'o'
                    || currentAlphabet == 'u') {
                vowelsFlag = true;
            } else {
                consonantsCount++;
            }
        }

        if (vowelsFlag && (consonantsCount >= 2)) {
            Collections.sort(selectedAlphabet);
            StringBuilder sb = new StringBuilder();
            for (Character ch : selectedAlphabet) {
                sb.append(ch);
            }

            String str = sb.toString();
            password.add(str);
            return;
        }
    }
}
