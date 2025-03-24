package PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// 작업: 위상 정렬 + 누적합을 구하다 자식이 더는 없는 단말에 도달하면 최소 시간 업데이트
public class P2056 {
	static int N;
	static int minTime;
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
			int in = Integer.parseInt(st.nextToken());
			Task task = new Task(in, time);
			tasks[idx] = task;
			
			if (in == 0) {
				queue.offer(idx);
			}
			
			for (int i = 0; i < in; i++) {
				int parent = Integer.parseInt(st.nextToken());
				postTasks[parent].add(idx);
			}
		}
		
		findMinTime();
		System.out.println(minTime);
	}

	private static void findMinTime() {
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Task task = tasks[curr];
			
			if (postTasks[curr].isEmpty()) {  // leaf node
				minTime = Math.max(minTime, task.totalTime);
			}
			
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
