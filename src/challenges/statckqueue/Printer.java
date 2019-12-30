package challenges.statckqueue;

//문제 설명
//    일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다.
//    그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다.
//    이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다.
//    이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.
//        1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
//        2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
//        3. 그렇지 않으면 J를 인쇄합니다.
public class Printer {
    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2}; // {1, 1, 9, 1, 1, 1}
        int location = 2; // 0
        int answer = solution(priorities, location);
        System.out.println("answer : " + answer);
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        answer = compute(priorities, location);
        return answer;
    }

    public static int compute(int[] priorities, int location) {
        int length = priorities.length;
        int priority;
        boolean flag = true;
        int order = 1;


        for ( ; length != 0; ) {
            priority = priorities[0];

            for (int indx = 1; indx < length; indx++) {
                if (priority < priorities[indx]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                if (location == 0) break;

                for (int indx = 1; indx < length; indx++)
                    priorities[indx - 1] = priorities[indx];

                order += 1;
                length += -1;
                location += -1;
            } else {
                for (int indx = 1; indx < length; indx++)
                    priorities[indx - 1] = priorities[indx];

                priorities[length - 1] = priority;
                flag = true;

                if (location == 0) {
                    location = length - 1;
                } else {
                    location += -1;
                }
            }
        }

        return order;
    }
}
