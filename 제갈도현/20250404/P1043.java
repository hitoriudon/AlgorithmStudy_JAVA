// 메모리 11592kb, 시간 68ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1043 {
	static int people;
	static int[] parents;

	static void make() {
		parents = new int[people + 1];
		for (int i = 1; i <= people; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (a == parents[a])
			return a;

		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		people = Integer.parseInt(st.nextToken());
		int totalParties = Integer.parseInt(st.nextToken());

		make();

		st = new StringTokenizer(br.readLine(), " ");

		// 진실을 아는 사람의 수
		int peopleWhoKnowsTheTruth = Integer.parseInt(st.nextToken());

		// 진실을 아는 사람의 대표
		int personWhoKnowTheTruth = 0;
		// 진실을 아는 사람이 있으면 union
		if (peopleWhoKnowsTheTruth != 0) {
			personWhoKnowTheTruth = Integer.parseInt(st.nextToken());

			while (st.hasMoreTokens())
				union(personWhoKnowTheTruth, Integer.parseInt(st.nextToken()));
		} else {
			System.out.println(totalParties);
			return;
		}

		// 파티 정보 저장
		int[][] partyList = new int[totalParties][];
		for (int i = 0; i < totalParties; i++) {
			st = new StringTokenizer(br.readLine(), " ");

			// 파티에 참여하는 사람 수
			int peopleWhoAttendsThisParty = Integer.parseInt(st.nextToken());

			// 파티에 참여하는 사람들 저장
			int[] listWhoAttendsThisParty = new int[peopleWhoAttendsThisParty];
			for (int j = 0; j < peopleWhoAttendsThisParty; j++) {
				listWhoAttendsThisParty[j] = Integer.parseInt(st.nextToken());

				// 같은 파티는 같은 그룹으로 묶음
				if (j != 0)
					union(listWhoAttendsThisParty[0], listWhoAttendsThisParty[j]);
			}

			partyList[i] = listWhoAttendsThisParty;
		}

		int res = 0;
		out: for (int[] party : partyList) {
			for (int person : party) {
				// 진실을 알고 있는 사람들이 있는 모임은 find 결과가 같음
				if (find(personWhoKnowTheTruth) == find(person))
					continue out;
			}

			res++;
		}

		System.out.println(res);
	}
}