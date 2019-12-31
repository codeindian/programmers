package challenges.statckqueue;

//문제 설명
//    트럭 여러 대가 강을 가로지르는 일 차선 다리를 정해진 순으로 건너려 합니다.
//    모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
//    트럭은 1초에 1만큼 움직이며, 다리 길이는 bridge_length이고 다리는 무게 weight까지 견딥니다.
//    ※ 트럭이 다리에 완전히 오르지 않은 경우, 이 트럭의 무게는 고려하지 않습니다.
public class Truck {
    public static void main(String[] args) {
        int bridge_length = 2; // 100
        int weight = 10; // 100
        int[] truck_weight = {7, 4, 5, 6}; // {10, 10, 10, 10, 10, 10, 10, 10, 10, 10}
        int answer = solution(bridge_length, weight, truck_weight);
        System.out.println("answer : " + answer);
    }

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        answer = compute(bridge_length, weight, truck_weights);
        return answer;
    }

    public static int compute(int bridge_length, int weight, int[] truck_weights) {
        int truck_cnt = truck_weights.length; // 대기 중인 트럭 대수
        int[] times = new int[truck_cnt]; // 트럭별 출발 시간 기록
        int timer = 0; // 타이머
        int position = 0; // 첫 번째 트럭의 위치
        int curr_wt = 0; // 다리 위에 있는 트럭 무게 총합
        int driving = 0; // 다리 위를 지나고 있는 트럭 대수
        int complete = 0; // 다리 끝에 도달한 트럭 대수
        int ptr = 0;

        for (int indx = 0; complete != truck_cnt; ) { // 모든 트럭이 다리를 지날 때까지
            timer += 1; // 1초 증가
            position += 1; // 위치 1 이동

            if (indx < truck_cnt
                    && curr_wt + truck_weights[indx] <= weight
                    && driving < bridge_length) {

                curr_wt += truck_weights[indx];
                driving += 1;
                times[indx++] = timer;
            }

            if (position == bridge_length) { // 트럭이 다리 끝에 도달
                curr_wt += -truck_weights[ptr];
                driving += -1;
                complete += 1;
                position = run(times, ptr++, bridge_length, driving); // 다음 트럭 위치 계산
            }
        }

        return ++timer;
    }

    public static int run(int[] times, int ptr, int bridge_length, int driving) {
        if (driving == 0) return 0; // 다리 위를 지나고 있는 트럭이 없다면

        int interval = times[ptr + 1] - times[ptr]; // 트럭 간 시간 차이 계산
        int position = bridge_length - interval; // 시간 차이 만큼 위치 이동

        return position;
    }
}
