import java.io.*;
import java.util.*;

class Solution {

    final int[] alphabetNums = {
            3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1
    };

    int getNum(char alphabet) {
        return alphabetNums[alphabet - 'A'];
    }

    public void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String A = br.readLine();
        String B = br.readLine();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) arr.add(getNum(A.charAt(i)));
                else arr.add(getNum(B.charAt(i)));
            }
        }

        while (arr.size() > 2) {
            List<Integer> updatedArr = new ArrayList<>();
            for (int i = 1; i < arr.size(); i++) {
                int sum = arr.get(i - 1) + arr.get(i);
                updatedArr.add(sum % 10);
            }
            arr = updatedArr;
        }

        arr.forEach(System.out::print);
        System.out.println();

        br.close();
        bw.close();
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        new Solution().solution();
    }
}
