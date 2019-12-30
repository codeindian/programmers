package challenges.heap;

//문제 설명
//    매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
//    모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 Leo는 스코빌 지수가 가장 낮은 두 개의 음식을
//    아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.
//    섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
public class Scoville {
    public static void main(String[] args) {
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        int answer = 0;
        answer = compute(scoville, K);
        System.out.println(answer);
    }

    public static int compute(int[] scoville, int K) {
        MinHeap minHeap;
        int length;
        int element1, element2;
        int mix_cnt = 0;

        if (check(scoville, scoville.length, K)) return 0;

        length = scoville.length;
        minHeap = new MinHeap(length);
        minHeap.arrayCopy(scoville, length);

        for ( ; minHeap.getPtr() != 1; ) {
            minHeap.build();

            element1 = minHeap.delete();
            element2 = minHeap.delete();

            minHeap.setHeap((element1 + element2 * 2), minHeap.getPtr() + 1);
            minHeap.setPtr(minHeap.getPtr() + 1);

            mix_cnt += 1;

            if (check(minHeap.getHeap(), minHeap.getPtr(), K)) break;
        }

        return mix_cnt;
    }

    // 스코빌 지수 검사
    public static boolean check(int[] data, int length, int K) {
        for (int indx = 1; indx <= length; indx++) {
            if (data[indx] < K)
                return false;
        }

        return true;
    }
}
