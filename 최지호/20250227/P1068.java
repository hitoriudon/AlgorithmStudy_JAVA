// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.StringTokenizer;


// /*해줘야하는것
//  * 1. 트리 초기화
//  * 2. 노드 삭제
//  * 3. 리프 노드 개수 세기(dfs)
//  */
// public class P1068 {
//     static List<Integer>[] tree;
//     static int count = 0;
//     public static void main(String[] args) throws NumberFormatException, IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//         int n = Integer.parseInt(br.readLine());
        
//         //트리 초기화 해주기
//         tree = new ArrayList[n];

//         for(int i = 0; i < n; i++) {
//             tree[i] = new ArrayList<>();
//         }

//         int root = 0;
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         //한개 버리기
//         st.nextToken();
//         for(int i = 1; i < n; i++) {
//             int parent = Integer.parseInt(st.nextToken());
//             tree[parent].add(i);
//         }

//         int target = Integer.parseInt(br.readLine());

//         // target 노드 지우기
//         for (int i = 0; i < tree.length; i++) {
//             tree[i].remove(Integer.valueOf(target));
//         }
        
//         tree[target] = null;

//         if(root != target) {
//             dfs(root);
//         }

//         System.out.println(count);
//     }


//     static void dfs(int i) {
//         //삭제된 노드는 건너뛰기
//         //비어있는 노드면 count++
//         if(tree[i] != null &&tree[i].isEmpty()) {
//             count++;
//             return;
//         }
        
//         for (int child : tree[i]) {
//             dfs(child);
//         }
//     }    
// }
