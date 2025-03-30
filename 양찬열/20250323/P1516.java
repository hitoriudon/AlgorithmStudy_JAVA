package topologySort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// 게임 개발: 작업과 유사
public class P1516 {
	static int N;
	static Deque<Integer> queue;
	static Task[] tasks;  // task 객체 관리용
	static List<Integer>[] postTasks;
	static class Task {
		int in;  // 진입차수
		int time;  // 소모 시간
		int totalTime; // 누적합
		
		Task(int in, int time) {
			this.in = in;
			this.time = time;
			this.totalTime = time;  // 소모 시간으로 초기화
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tasks = new Task[N + 1];
		queue = new ArrayDeque<>();
		postTasks = new List[N + 1];  // 0번 무시
		for (int idx = 1; idx <= N; idx++) {
			postTasks[idx] = new ArrayList<>();  // 초기화
		}
		
		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			
			int in = 0;
			int pre;
			while ((pre = Integer.parseInt(st.nextToken())) != -1){
				postTasks[pre].add(idx);
				in++;
			}
			Task task = new Task(in, time);
			tasks[idx] = task;
			
			if (in == 0) {
				queue.offer(idx);
			}
		}
		
		findMinTime();
		for (int i = 1; i <= N; i++) {
			System.out.println(tasks[i].totalTime);
		}
	}

	private static void findMinTime() {
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Task task = tasks[curr];
						
			for (int idx: postTasks[curr]) {
				Task child = tasks[idx];
				child.in--;
				child.totalTime = Math.max(child.totalTime, task.totalTime + child.time);  // 시간 업데이트: 이전 토탈(n + time=c.total)과 현재 토탈(p.total + time)
				if (child.in == 0) {
					queue.add(idx);
				}
			}
		}
	}
}
