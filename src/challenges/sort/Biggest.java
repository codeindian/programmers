package challenges.sort;

public class Biggest {

    private static int[] perm_int;
    private static int ptr = 0;

    public static void main(String[] args) {
        int[] numbers = {0, 0, 0, 1000};
        String answer = solution(numbers);
        System.out.println(answer);
    }

    public static String solution(int[] numbers) {
        String answer = "";
        answer = compute(numbers);
        return answer;
    }

    public static String compute(int[] numbers) {
        String biggest = "";
        int depth = 0;

        int cnt = init(numbers.length);
        perm_int = new int[cnt];

        permutate(numbers, depth);

        quickSort(perm_int, 0, perm_int.length - 1);

        biggest = Integer.toString(perm_int[perm_int.length - 1]);

        return biggest;
    }

    public static int init(int cnt) {
        for (int indx = cnt - 1; indx > 0; indx--)
            cnt = cnt * indx;

        return cnt;
    }

    public static void permutate(int[] numbers, int depth) {
        if (depth == numbers.length) {
            perm_int[ptr++] = convert(numbers);
            return ;
        }

        for (int indx = depth; indx < numbers.length; indx++) {
            swap(numbers, depth, indx);
            permutate(numbers, depth + 1);
            swap(numbers, depth, indx);
        }
    }

    public static void swap(int[] numbers, int pos1, int pos2) {
        int tmp = numbers[pos1];
        numbers[pos1] = numbers[pos2];
        numbers[pos2] = tmp;
    }

    public static int convert(int[] numbers) {
        int ret = Integer.parseInt(toString(numbers));
        return ret;
    }

    public static String toString(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        for (int indx = 0; indx < numbers.length; indx++) {
            sb.append(Integer.toString(numbers[indx]));
        }

        return sb.toString();
    }

    public static void quickSort(int[] numbers, int start, int end) {
        if (start >= end) return;

        int pos = partition(numbers, start, end);
        quickSort(numbers, start, pos - 1);
        quickSort(numbers, pos + 1, end);
    }

    public static int partition(int[] numbers, int start, int end) {
        double pivot = numbers[start];
        int left = start;
        int right = end;
        int i = left,
                j = right;

        while (i < j) {
            while (pivot < numbers[j])
                j--;

            while (i < j && pivot >= numbers[i])
                i++;

            swap(numbers, i, j);
        }

        swap(numbers, left, i);

        return i;
    }
}
