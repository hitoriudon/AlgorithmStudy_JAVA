import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P10773 {

    //  23028KB 184ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int index = 0;
        int value;
        int sum = 0;

        int K = Integer.parseInt(br.readLine());
        int[] numbers = new int[K];

        for (int i = 0; i < K; i++) {
            value = Integer.parseInt(br.readLine());
            if(value == 0){
                numbers[--index] = 0;
            }else {
                numbers[index++] = value;
            }
        }

        for (int i = 0; i < index; i++) {
            sum += numbers[i];
        }

        System.out.print(sum);
    }

//    23436KB 200ms
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int tot = 0;
//        int minus = 0;
//        int value;
//
//        ArrayDeque<Integer> stack = new ArrayDeque();
//        int K = Integer.parseInt(br.readLine());
//        for (int i = 0; i < K; i++) {
//            value = Integer.parseInt(br.readLine());
//            if(value == 0){
//                minus += stack.pollLast();
//            }else {
//                tot += value;
//                stack.addLast(value);
//            }
//        }
//
//        System.out.print(tot - minus);
//    }
}
