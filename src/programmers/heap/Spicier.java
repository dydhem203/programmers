package programmers.heap;

import java.util.PriorityQueue;

/**
 * 문제 설명
 * 매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
 *
 * 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
 * Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
 * Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 사항
 * scoville의 길이는 2 이상 1,000,000 이하입니다.
 * K는 0 이상 1,000,000,000 이하입니다.
 * scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
 * 모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 */
public class Spicier {
    public static void main(String[] args){
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int result = solution(scoville, K);
        System.out.println(result);//2
    }
    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int num : scoville){
            priorityQueue.add(num);
        }
        while(true){
            answer++;
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            priorityQueue.add(a+b*2);
            if(priorityQueue.peek() >= K) return answer;
            if(priorityQueue.size()==1) return -1;
        }
    }
}