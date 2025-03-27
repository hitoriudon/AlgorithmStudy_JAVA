package topologySort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// ACM Craft: 작업과 유사
public class P1005 {
	static int T, N, K, W;
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

		@Override
		public String toString() {
			return "Task [in=" + in + ", time=" + time + ", totalTime=" + totalTime + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		
		StringBuffer sb = new StringBuffer();
		for (int test = 1; test <= T; test++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			tasks = new Task[N + 1];
			queue = new ArrayDeque<>();
			postTasks = new List[N + 1];  // 0번 무시
			for (int idx = 1; idx <= N; idx++) {
				postTasks[idx] = new ArrayList<>();  // 초기화
			}
			
			st = new StringTokenizer(br.readLine());
			for (int idx = 1; idx <= N; idx++) {
				int time = Integer.parseInt(st.nextToken());
				Task task = new Task(0, time);
				tasks[idx] = task;
			}
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int pre = Integer.parseInt(st.nextToken());
				int post = Integer.parseInt(st.nextToken());

				tasks[post].in++;  // 실수
				postTasks[pre].add(post);
			}
			
			W = Integer.parseInt(br.readLine());
			
			for (int idx = 1; idx <= N; idx++) {
				if(tasks[idx].in == 0) {
					queue.add(idx);
				}
			}
			
			minTime = 0;
			findMinTime();
			sb.append(minTime).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void findMinTime() {
		while (!queue.isEmpty()) {
			int curr = queue.poll();
			Task task = tasks[curr];
			if (curr == W) {
				minTime = task.totalTime;
				break;  // w 도달 시 종료
			}
						
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
