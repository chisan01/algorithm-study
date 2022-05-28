import java.io.*;
import java.util.*;

// [백준] Silver2 과자 나눠주기
// https://www.acmicpc.net/problem/16401

public class Main {

    long numOfNephew, numOfSnack;
    long[] lenOfSnacks;

    boolean isPossible(long len) {
        long count = 0;
        for (long lenOfSnack : lenOfSnacks) {
            count += lenOfSnack / len;
        }
        return numOfNephew <= count;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        numOfNephew = Integer.parseInt(st.nextToken());
        numOfSnack = Integer.parseInt(st.nextToken());

        lenOfSnacks = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long maxLenOfSnack = 0;
        for (long lenOfSnack : lenOfSnacks) {
            maxLenOfSnack = Math.max(maxLenOfSnack, lenOfSnack);
        }

        long low = 0;
        long high = maxLenOfSnack;

        while(low < high) {
            long mid = (low + high + 1) / 2;
            if(isPossible(mid)) low = mid;
            else high = mid - 1;
        }

        bw.write(Long.toString(low) + '\n');

        br.close();
        bw.close();
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}