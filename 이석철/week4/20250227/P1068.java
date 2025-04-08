/**
 * 트리 전략
 * 집 와서 정신을 맑게 하고 처음 딱 봤을 때
 * 유겸이 말대로 BFS로 접근하는 게 더 쉬울 거 같다는 생각이 강하게 든다
 * (내가 비엪이 익숙해서 그럴 수도)
 */

 /** 
 * 반례 검색해버림.. 문제가 너무 불친절해 루트부터 순서대로 들어오는 줄 알았는데 아니었음
 * 루트 노드가 0번째에 들어오는 게 아닐 수 있따.....
 * 루트만 남았으면 루트가 리프가 된다(중요)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068 {
    static int n;
    static int deleteNode;
    
    public static void main(String[] args) throws Exception{
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for (int i = 0; i < n; i++){ // initialize
            graph.add(new ArrayList<>());
        }
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // tree 처리
        int rootNode = -1;
        for (int i = 0; i < n; i++){
            int parent = Integer.parseInt(st.nextToken());
            if (parent != -1){
                graph.get(parent).add(i); 
            } else if (parent == -1){
                rootNode = i; // root노드가 가장 처음 st에 있는 줄 알았는데, 순서가 보장되지 않기 때문에 rootNode 값 가지고 있어야 함
            }
        }
        
        deleteNode = Integer.parseInt(br.readLine());
        if (deleteNode == rootNode){ // 만약 루트 노드를 삭제하는 거라면, 그냥 바로 0 출력하고 프로그램 종료
            System.out.println(answer);
            System.exit(0);
        }

        // 문제가 쉬워지는 포인트
        // 현재 graph 이차원 배열의 인덱스 값은 노드 번호고, 자식들의 노드 번호가 그 인덱스 배열 안에 들어가 있음
        // 노드의 뎁스가 깊더라도, graph 안에 deleteNode 값은 유일하게 1개임이 보장되기 때문에
        // 완전 탐색으로 배열을 돌면서 deleteNode가 안에 있으면(인덱스 노드 번호의 자식이면)
        // 그냥 걔 삭제해주면 됨. 그럼 bfs로 순회할 때 삭제된 노드의 인덱스에는 접근할 일이 없다(유일한 값인데 삭제하면 찾을 수 없으니)
        // 삭제된 노드의 인덱스에는 자식 인덱스 값이 있는데, 삭제된 노드 인덱스 정보가 이미 삭제되었으니 걔네를 방문할 일이 없게 된다

        for (int i = 0; i < n; i++){
            if (graph.get(i).contains(deleteNode)){
                graph.get(i).remove(Integer.valueOf(deleteNode));
            }            
        }

        // for bfs
        // 자식 노드 인덱스만 가지고 있어서 굳이 vis 처리할 필요 x
        // 어차피 다시 갈 일이 없음(그래프 안에 값은 유일함 = 양방향 그래프가 아니라서)

        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.offer(rootNode); // root 노드부터 탐색 시작
        boolean firstTry = true; // 주목해야 하는 플래그
        
        while (!que.isEmpty()){
            int node = que.poll();
            
            // first Try에는 루트 노드부터 시작인데 루트 노드에 연결된 애가 없다? 그럼 루트 노드가 유일한 리프인 경우임
            if (graph.get(node).isEmpty() && firstTry){
                System.out.println(1);
                System.exit(0);
            } else if (!graph.get(node).isEmpty()){ // 길이가 0이 아니면 자식 노드가 있다는 뜻
                firstTry = false;
                for (int nextNode: graph.get(node)){ // 자식들 노드 순회
                    if (graph.get(nextNode).isEmpty()){ // 자식들 노드가 비어있다? 그럼 그 자식은 리프임. 즉 탐색할 필요가 없음. -> que에 넣지 않는 조건
                        answer++; 
                    } else {
                        que.offer(nextNode); // 자식들 노드에도 자식이 있다면 계속 탐색할 필요가 있다. -> que에 넣는 조건
                    }
                }
            }
        }
        System.out.println(answer);
    }   
}