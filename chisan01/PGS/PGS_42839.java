package PGS;

// [프로그래머스] Lv.2 소수 찾기

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PGS_42839 {

    List<Integer> ret;
    boolean[] visit;
    String numbers;

    void makeNum(final int length, String curNumber) {
        if (curNumber.length() == length) {
            ret.add(Integer.parseInt(curNumber));
            return;
        }
        for (int i = 0; i < numbers.length(); i++) {
            if (visit[i]) {
                continue;
            }
            visit[i] = true;
            makeNum(length, curNumber + numbers.charAt(i));
            visit[i] = false;
        }
    }

    List<Integer> generateCandidates(final String numbers) {
        this.ret = new ArrayList<>();
        this.visit = new boolean[numbers.length()];
        Arrays.fill(visit, false);
        this.numbers = numbers;

        for (int len = 1; len <= numbers.length(); len++) {
            makeNum(len, "");
        }

        return ret;
    }

    public int solution(String numbers) {
        // numbers로 만들 수 있는 숫자 조합
        // 범위: 1 ~ 9*10^7, 개수는 9*9! + 9*9!/2! + ... + 9 최대 약 9*9*9! = 29393280
        List<Integer> candidates = generateCandidates(numbers);

        // 소수 체크용 배열 생성
        boolean[] checkPrime = new boolean[10000000];
        Arrays.fill(checkPrime, true);
        checkPrime[0] = false;
        checkPrime[1] = false;
        for (int i = 2; i * i < checkPrime.length; i++) {
            for (int j = i; j * i < checkPrime.length; j++) {
                checkPrime[i * j] = false;
            }
        }

        // 생성된 숫자 조합에 대해서 소수인 숫자 개수 count
        int ret = 0;
        for (int candidate : candidates) {
            if (checkPrime[candidate]) {
                checkPrime[candidate] = false; // 같은 숫자에 대한 중복 제거
                ret++;
            }
        }
        return ret;
    }
}
