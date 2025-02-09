import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class P1158{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        LinkedList<Integer> list = new LinkedList<>();

        for(int i = 0;i < n; i++){
            list.add(i+1);
        }

        int nextIdx = 0;

        sb.append("<");

        while(true){
            nextIdx = (nextIdx+k-1)%list.size();
            sb.append(list.get(nextIdx));
            list.remove(nextIdx);
            if(list.isEmpty()) break;
            sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb);
        
    }
}