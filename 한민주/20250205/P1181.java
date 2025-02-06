import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class P1181 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Set<String> words = new HashSet<String>();
        for(int i = 0; i < n; i++){
            words.add(br.readLine()) ;
        }

        List<String> list = new ArrayList<>(words);
        
        Collections.sort(list, 
        (o1, o2) -> {if (o1.length() == o2.length()) {
            return o1.compareTo(o2);
        }
        else if(o1.length() > o2.length()){
            return 1;
        }
        else
            return -1;});

        list.forEach((word) -> System.out.println(word));
    }
}
