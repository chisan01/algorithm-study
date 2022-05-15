package BOJ;

// [백준] Silver4 수 찾기
// https://www.acmicpc.net/problem/1920

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class BOJ_1920 {

    int N, M;
    Set<Integer> set;
    int[] searchNumbers;

    void solution() throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        set = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());

        M = Integer.parseInt(br.readLine());
        searchNumbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int searchNumber : searchNumbers) {
            if(set.contains(searchNumber)) bw.write("1\n");
            else bw.write("0\n");
        }

        bw.close();
        br.close();
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1920().solution();
    }
}
