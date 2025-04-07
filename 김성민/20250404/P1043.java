import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1043 {
	static int[] parents;
	
	static int find(int human) {
		if(human == parents[human]) {
			return human;
		}
		
		return parents[human] = find(parents[human]);
	}
	
	static void union(int human1, int human2) {
		int human1Root = find(human1);
		int human2Root = find(human2);
		
		if(human1Root == human2Root) {
			return;
		}
		
		parents[human2Root] = human1Root;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int humanAmount = Integer.parseInt(st.nextToken());
		int partyAmount = Integer.parseInt(st.nextToken());
		parents = new int[humanAmount + 1];
		for(int humanCount = 1; humanCount <= humanAmount; humanCount++) {
			parents[humanCount] = humanCount;
		}
		ArrayList<Integer>[] partys = new ArrayList[partyAmount];
		for(int partyCount = 0; partyCount < partyAmount; partyCount++) {
			partys[partyCount] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		int knownAmount = Integer.parseInt(st.nextToken());
		if(knownAmount == 0) {
			System.out.println(partyAmount);
			return;
		}
		int[] knowns = new int[knownAmount];
		for(int knownCount = 0; knownCount < knownAmount; knownCount++) {
			knowns[knownCount] = Integer.parseInt(st.nextToken());
		}
		
		for(int partyCount = 0; partyCount < partyAmount; partyCount++) {
			st = new StringTokenizer(br.readLine());
			
			int partyHumanAmount = Integer.parseInt(st.nextToken());
			for(int humanCount = 0; humanCount < partyHumanAmount; humanCount++) {
				int curHuman = Integer.parseInt(st.nextToken());
				partys[partyCount].add(curHuman);
				
				union(partys[partyCount].get(0), curHuman);
			}
		}
		
		int liePartyAmount = 0;
		for(ArrayList<Integer> party : partys) {
			boolean lieFlag = true;
			for(int partyHuman : party) {
				for(int knownHuman : knowns) {
					if(find(partyHuman) == find(knownHuman)) {
						lieFlag = false;
						break;
					}
				}
				if(lieFlag == false) {
					break;
				}
			}
			
			if(lieFlag) {
				liePartyAmount++;
			}
		}
		
		System.out.println(liePartyAmount);
	}
}
