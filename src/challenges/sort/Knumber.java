package challenges.sort;

import java.util.ArrayList;

public class Knumber {

    public static void main(String[] arge) {
        int[] array = { 1, 5, 2, 6, 3, 7, 4 };
        int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
        int[] answer = solution(array, commands);
        System.out.println("answer : " + answer);
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = {};
        answer = compute(array, commands);
        return answer;
    }

    public static int[] compute(int[] array, int[][] commands) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] answer = null;
        int start;
        int end;
        int position;
        int k_number;

        if (array.length < 1 || array.length > 100) return answer;

        if (commands.length < 1 || commands.length > 50) return answer;

        for (int indx = 0; indx < commands.length; indx++) {
            start = commands[indx][0];
            end = commands[indx][1];
            position = commands[indx][2];

            answer = merge(array, start - 1, end - 1);
            k_number = answer[start - 1 + position - 1];
            list.add(k_number);
        }

        answer = new int[list.size()];

        for (int indx = 0; indx < list.size(); indx++) {
            answer[indx] = list.get(indx);
        }

        return answer;
    }

    public static int[] merge(int[] array, int start, int end) {
        int length = end - start + 1;
        int mid;
        int[] answer;
        int r_indx;
        int mov;

        if (length == 1) return array;

        mid = start + ((end - start) / 2);
        r_indx = mid + 1;

        array = merge(array, start, mid);
        array = merge(array, r_indx, end);

        answer = new int[array.length];

        for (int indx = 0; indx < array.length; indx++) {
            answer[indx] = array[indx];
        }

        mov = start;

        for (int indx = start; indx <= mid && r_indx <= end; ) {
            if (array[indx] <= array[r_indx]) {
                answer[mov++] = array[indx++];
                start = start + 1;
            } else {
                answer[mov++] = array[r_indx++];
            }
        }

        for (int indx = start; indx <= mid; indx++) {
            answer[mov++] = array[indx];
        }

        for (int indx = r_indx; indx <= end; indx++) {
            answer[mov++] = array[indx];
        }

        return answer;
    }
}
