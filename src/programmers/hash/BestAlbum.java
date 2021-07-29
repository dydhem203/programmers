package programmers.hash;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제 설명
 * 스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
 *
 * 속한 노래가 많이 재생된 장르를 먼저 수록합니다.
 * 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
 * 장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
 * 노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
 *
 * 제한사항
 * genres[i]는 고유번호가 i인 노래의 장르입니다.
 * plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
 * genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
 * 장르 종류는 100개 미만입니다.
 * 장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
 * 모든 장르는 재생된 횟수가 다릅니다.
 */
public class BestAlbum {
    public static void main(String[] args){
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] result = solution(genres, plays);
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.toList()));//	[4, 1, 3, 0]


        String[] genres2 = {"classic", "pop", "classic", "classic","jazz","pop", "Rock", "jazz"};
        int[] plays2 = {500, 600, 150, 800, 1100, 2500, 100, 1000};
        int[] result2 = solution(genres2, plays2);
        System.out.println(Arrays.stream(result2).mapToObj(String::valueOf).collect(Collectors.toList()));//	[5, 1, 4, 7, 3, 0, 6]

    }

    public static int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genresPlayCntMap = new HashMap<>();
        Map<String, List> genresPlayMap = new HashMap<>();
        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            if(genresPlayMap.containsKey(genre)) {
                List tempList = genresPlayMap.get(genre);
                Map tempMap = new HashMap<Integer, Integer>();
                tempMap.put(i, play);
                tempList.add(tempMap);
                genresPlayMap.put(genre, tempList);
                genresPlayCntMap.put(genre,genresPlayCntMap.get(genre)+play);
            }
            else {
                List tempList = new ArrayList<Map<Integer,Integer>>();
                Map tempMap = new HashMap<Integer, Integer>();
                tempMap.put(i, play);
                tempList.add(tempMap);
                genresPlayMap.put(genre, tempList);
                genresPlayCntMap.put(genre, play);
            }
        }
        List<String> keySetList = new ArrayList<>(genresPlayCntMap.keySet());

        Collections.sort(keySetList, (o1, o2) -> (genresPlayCntMap.get(o2).compareTo(genresPlayCntMap.get(o1))));

        List<Integer> returnList = new ArrayList();
        for(String key : keySetList){
            List<Map<Integer,Integer>> tempList = genresPlayMap.get(key);
            Collections.sort(tempList, (o1, o2) -> o2.get(o2.keySet().iterator().next()).compareTo(o1.get(o1.keySet().iterator().next())));
            int cnt = tempList.size()>2?2:tempList.size();
            for(int i=0; i<cnt;i++){
                returnList.add(tempList.get(i).keySet().iterator().next());
            }
        }

        return returnList.stream().mapToInt(i->i).toArray();
    }
}
