package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


class Student {
	int x;
	int y;
	int id;

	public Student(int x, int y, int id) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
}

public class P1941 {
	static int answer = 0;
	static Student[][] students = new Student[5][5];
	static Student[] stds = new Student[25];
	static ArrayList<Student> studentsS = new ArrayList<>();
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[] visited = new boolean[25];
	static HashMap<Integer, Student> princess7 = new HashMap<>();
	static int[] princess7idx = new int[7];
	static boolean[] littlevisit;

	static boolean inRange(int x, int y) {
		return 0 <= x && x < 5 && 0 <= y && y < 5;
	}

	static void make(int cnt, int idx) {
		if (cnt == 7) {
			littlevisit = new boolean[25];
			if (checkDistance(princess7.get(0))) {
				answer++;
			}
			return;
		}

		for (int i = idx; i < 25; i++) {
			if (!visited[stds[i].id] && !studentsS.contains(stds[i])) {
				princess7.put(cnt, stds[i]);
				visited[stds[i].id] = true;
			
				make(cnt + 1, i + 1);
				princess7.remove(cnt);
				visited[stds[i].id] = false;
			}
		}

	}

	static void pick(int num, int cnt, int limit) {
		if (limit == cnt) {
			make(cnt, 0);
			return;
		}

		for (int i = num; i < studentsS.size(); i++) {
			Student newS = studentsS.get(i);
			princess7.put(cnt, newS);
			visited[newS.id] = true;
			pick(i + 1, cnt + 1, limit);
			visited[newS.id] = false;
			princess7.remove(cnt);
		}
	}

	static boolean checkDistance(Student princess) {
		littlevisit[princess.id] = true;
		for (int dir = 0; dir < 4; dir++) {
			int newX = princess.x + dx[dir];
			int newY = princess.y + dy[dir];

			if (inRange(newX, newY) && visited[students[newX][newY].id] && !littlevisit[students[newX][newY].id]) {
				checkDistance(students[newX][newY]);
			}
		}

		int temp = 0;
		for (int i = 0; i < 25; i++) {
			if (littlevisit[i])
				temp++;
			if(temp == 7) break;
		}
		return temp == 7;

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int id = 0;
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < 5; j++) {
				Student ns = new Student(i, j, id);
				if (input.charAt(j) == 'S') {
					studentsS.add(ns);
				}
				students[i][j] = ns;
				stds[id] = ns;
				id++;
			}
		}

		for (int i = 4; i <= studentsS.size(); i++) {
			pick(0, 0, i);
		}

		System.out.println(answer);
	}

}
