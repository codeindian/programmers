package challenges.heap;

public class MinHeap {
    private int[] heap;
    private int capacity;
    private int ptr;

    public MinHeap(int capacity) {
        this.heap = new int[capacity + 1];
        this.capacity = capacity;
        this.ptr = 0;
    }

    // 초기화
    public void init(int[] input) {
        for (int indx = 1; indx < input.length; indx++) {
            heap[indx] = input[indx];
        }
    }

    // 복사
    public void arrayCopy(int[] data, int length) {
        System.arraycopy(data, 0, heap, 1, length);
        ptr = length;
    }

    // 삽입
    public void insert(int data) {
        if (ptr == capacity) return;
        else heap[++ptr] = data;

        reverse_heapify(ptr);
    }

    // 삭제
    public int delete() {
        int data;

        if (ptr == 0) return - 1;

        if (ptr == 1) {
            data = heap[1];
            heap[1] = 0;
        } else {
            data = heap[1];
            heap[1] = heap[ptr];
            heap[ptr--] = 0;
            heapify(1);
        }

        return data;
    }

    // 최소힙 구성
    public void build() {
        for (int indx = ptr / 2; indx > 0; indx--) {
            heapify(indx);
        }
    }

    // 정렬
    public void sort() {
        for (int indx = ptr; indx > 1; indx--) {
            swap(1, indx);
            ptr += -1;
            heapify(1);
        }
    }

    // 힙 성질을 만족하도록 하는 연산
    public void heapify(int pos) {
        int l_pos = pos * 2;
        int r_pos = pos * 2 + 1;

        if (isLeaf(pos)) return;

        if (hasRight(r_pos)) {
            if (heap[pos] > heap[l_pos]
                    || heap[pos] > heap[r_pos]) {
                if (heap[l_pos] >= heap[r_pos]) {
                    swap(pos, r_pos);
                    heapify(r_pos);
                } else {
                    swap(pos, l_pos);
                    heapify(l_pos);
                }
            }
        } else {
            if (heap[pos] > heap[l_pos]) {
                swap(pos, l_pos);
                heapify(l_pos);
            }
        }
    }

    public void reverse_heapify(int pos) {
        for (int indx = pos; indx / 2 > 1; indx /= 2) {
            if (heap[indx / 2] > heap[indx]) {
                swap(indx / 2, indx);
            } else {
                break;
            }
        }
    }

    public boolean isLeaf(int pos) {
        if (pos > ptr / 2 && pos <= capacity) return true;
        else return false;
    }

    public boolean hasRight(int pos) {
//        if (pos <= capacity && heap[pos] != 0) return true;
//        else return false;
        if (pos <= ptr && pos <= capacity) return true;
        else return false;
    }

    public void swap(int p_ptr, int c_ptr) {
        int data = heap[p_ptr];
        heap[p_ptr] = heap[c_ptr];
        heap[c_ptr] = data;
    }

    public int[] getHeap() {
        return heap;
    }

    public int getHeap(int pos) {
        return heap[pos];
    }

    public void setHeap(int data, int pos) { this.heap[pos] = data; }

    public int getPtr() { return ptr; }

    public void setPtr(int ptr) {
        this.ptr += ptr;
    }
}
