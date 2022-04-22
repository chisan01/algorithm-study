package BOJ;

// [백준] Gold5 연속합 2
// https://www.acmicpc.net/problem/13398

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13398 {
    List<Integer> arr;

    int func(int startIndex, int curIndex, int sum, int removed) {
        // base case
        if(curIndex >= arr.size()) return sum;

        // 멈추는 경우
        int ret = sum;
        // 연속하는 경우
        ret = Math.max(ret, func(startIndex, curIndex + 1, sum + arr.get(curIndex), removed));
        // 새로 시작하는 경우
        ret = Math.max(ret, func(curIndex, curIndex + 1, arr.get(curIndex), removed));
        // 숫자를 제거할 수 있고, 숫자를 제거하는 경우
        if(removed == 0) {
            ret = Math.max(ret, func(curIndex, curIndex + 1, sum, 1));
        }

        return ret;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        arr = new ArrayList<>();
        for(int i=0; i<n; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(func(0, 0, 0, 0));
    }

    public static void main(String[] args) throws Exception {
        new BOJ_13398().solution();
    }
}
