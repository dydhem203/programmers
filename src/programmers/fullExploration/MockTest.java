package programmers.fullExploration;

import java.util.*;

/**
 * 문제 설명
 * 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
 *
 * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
 * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
 * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
 *
 * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
 * 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
 *
 * 제한 조건
 * 시험은 최대 10,000 문제로 구성되어있습니다.
 * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
 * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
 */
public class MockTest {
    public static void main(String[] args){
        int[] answers = {1,2,3,4,5};
        int[] result = solution(answers);
        System.out.println(Arrays.toString(result));//[1]
        int[] answers2 = {1,3,2,4,2};
        int[] result2 = solution(answers2);
        System.out.println(Arrays.toString(result2));//[1,2,3]
        int[] answers3 = {1,1,1,1};
        int[] result3 = solution(answers3);
        System.out.println(Arrays.toString(result3));//[3]
    }
    public static int[] solution(int[] answers) {
        int [] sa1 = new int[]{1, 2, 3, 4, 5};
        int [] sa2 = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
        int [] sa3 = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int s1=0, s2=0, s3=0;

        // 학생별 맞춘 개수 구하기.
        for(int i=0; i<answers.length; i++){
            if(answers[i]==sa1[(i)%5])s1++;
            if(answers[i]==sa2[(i)%8])s2++;
            if(answers[i]==sa3[(i)%10])s3++;
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        pq.add(new AbstractMap.SimpleEntry<>(1, s1));
        pq.add(new AbstractMap.SimpleEntry<>(2, s2));
        pq.add(new AbstractMap.SimpleEntry<>(3, s3));

        System.out.println(pq);
        // 가장 높은 점수 받은 학생 구하기.
        List<Integer> answerList = new ArrayList<>();
        while(true){
            int val = pq.peek().getValue();
            answerList.add(pq.poll().getKey());
            if(pq.size()==0 || val!=pq.peek().getValue()) break;
        }
        answerList.sort(Integer::compareTo);
        return answerList.stream().mapToInt(i->i).toArray();
    }
}
