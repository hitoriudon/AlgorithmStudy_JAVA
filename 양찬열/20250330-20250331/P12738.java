package BinarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// 가장 긴 증가하는 부분 수열 3: 수열의 원소를 순서대로 뽑아 만들 수 있는 부분수열 중 가장 긴 값 -> 완전탐색(2^N) 시 overflow -> 가장 긴 증가하는 부분 수열 5(P14003)
// N <= 10^9 -> int 범위 내
// 방법을 모르겠어서 찾아봤다...: dp+이진 탐색->배열을 순회하며 길이가 i+1일 때 가능한 부분 수열의 마지막 원소를 tail 배열에 업데이트
// tail의 길이 = 가장 긴 증가하는 부분 수열!
public class P12738 {
	static int N;
	static int[] nums;
	static List<Integer> tails;
//	static Map<Integer, List<Integer>> map;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		tails = new ArrayList<>();
		parents = new int[N];
//		map.put(0, tails);  // 초기화
//		int preIdx = -2;
//		boolean flag = false;  // 새로운 원소 업데이트 이후 연속으로 다음 idx 업데이트가 일어나 끝까지 가면 새로운 list 저장, 아니면 새로운 값으로 바꾼 이후의 증가 부분 순열의 길이가 원본 이상이 될 수 없으므로 원본 복원
		// dp와 같이 원소를 하나씩 추가하며 tail 업데이트 -> tail에 대해 이진 탐색 시도!(tail은 정렬을 지키며 원소를 추가하므로 이진 탐색 가능)
		// 실수: 연속 업데이트 조건에 의해, 단독으로 마지막 idx를 업데이트하는 경우가 빠져버렸다! -> 별도 처리
		// 실수: 작은 값이 나온 후 값이 추가된 경우에도, 이후 작은 값만 연속으로 나오면 작은 값을 선택하는 경우가 더 짧아지는 경우가 발생한다!
		// 결국 이것도 chatGPT의 힘을 빌렸다...: 부모의 값을 저장하여 전부 끝난 후 뒤에서부터 역으로 찾아가기! -> input의 범위가 크고 중복을 허용하지 않으므로 map으로 압축
		// 실수: hashMap 형태를 사용할 경우, 같은 값에 의한 overlapping 발생 -> 각 입력의 idx 기준으로 정렬
		for (int i = 0; i < N; i++) {
			// tail에서 현재 값이 들어갈 수 있는 index 계산
            int idx = binarySearch(0, tails.size(), nums[i]);
            if (idx != 0) {
            	parents[i] = tails.get(idx -1);  // 현재값을 키로, 이전 값 저장
            }
            // idx가 tails의 크기 -> 새로운 원소 추가
            if (idx == tails.size()) {
//            	if (!(flag && preIdx == idx - 1)) {  // 연속 업데이트 + 마지막 idx까지 업데이트 완료인 경우에만 원본 복원 X
//            		tails = map.get(tails.size());  // 원본 복원
//            	}
            	tails.add(i);  // 실제 값 대신 인덱스 저장
            	
//            	// size 증가 시마다 저장
//        		List<Integer> copy = new ArrayList<>(tails);
//        		map.put(tails.size(), copy);
            }
            // idx가 중간에 삽입 가능한 경우 -> 삽입할 수 있는 idx 위치의 값을 새로운 값으로 update
            // 새로운 값으로 바꾸는 것이 더 유리한 경우, 새로운 값 이후의 값이 전부 update되며 리스트 확장
            // 새로운 값으로 바꾸는 것이 더 불리한 경우, 어쨌든 정렬은 유지되며 이전의 최대 길이 또한 변화하지 않으므로 마지막 원소를 기점으로 정상적으로 리스트 확장 가능
            // dp에서, 원소의 추가에 따른 이차원 배열 대신 이전 값을 idx-1일 때의 값처럼 여기고 이용하는 것과 유사!
            else {
//            	if (idx - preIdx == 1 || idx == tails.size()) {
//            		flag = true;  // 연속하여 업데이트 중인 경우 flag 처리
//            	} else {
//            		flag = false;
//            	}
            	tails.set(idx, i);
//            	preIdx = idx;
            }
		}
		StringBuilder sb = new StringBuilder();
//		tails = map.get(tails.size());  // 가장 최근에 update된 해당 길이의 증가 부분 순열 사용
		int size = tails.size();
		sb.append(size).append("\n");
//		for (int item : tails) {
//			sb.append(item).append(" ");
//		}
		
		// 행렬에 저장하고 뒤집는 대신 perm.append(0, nums[last])도 가능하나, idx를 찾아 집어넣는 만큼 시간이 오래 소모되어 시간 초과 발생
		int last = tails.get(size - 1);
		int[] result = new int[size];
		for (int i = 0; i < size; i++) {
			result[size - i - 1] = nums[last];
			last = parents[last];
		}
		for (int i = 0; i < size; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

	private static int binarySearch(int start, int end, int num) {
		if (end == 0) {
			return 0;
		}
		
		while (start <= end) {
			// num의 위치: 조건(x>=num)을 만족하는 최솟값 -> 조건 만족 시 최댓값 감소, start 값 이용
			int middle = (start + end)/2;
			if (middle == tails.size()) return middle;  // idx가 tails의 범위를 벗어나는 경우
			if (nums[tails.get(middle)] >= num) end = middle - 1;  // num이 값보다 작은 경우, 오름차순 정렬에 의해 idx는 더 감소해야 한다.
			else start = middle + 1;  // num이 값보다 큰 경우, 오름차순 정렬에 의해 idx는 더 증가해야 한다.
			
			// 감소 부분 수열 시: x<=num을 만족 시 최댓값 감소
//			if (tails.get(middle) <= num) end = middle - 1;  // num이 값보다 작은 경우, 오름차순 정렬에 의해 idx는 더 감소해야 한다.
//			else start = middle + 1;  // num이 값보다 큰 경우, 오름차순 정렬에 의해 idx는 더 증가해야 한다.
		}
		return start;
	}
}
