package BOJ;

// [백준] Gold5 호텔
// https://www.acmicpc.net/problem/1106

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1106 {

    class City {
        int customer;
        int price;

        public City(int customer, int price) {
            this.customer = customer;
            this.price = price;
        }
    }

    int clientIncreaseGoal, numberOfCity;
    City[] cities;
    int[] dp;

    int func(int leftClientIncreaseGoal) {
        // base case
        if(leftClientIncreaseGoal <= 0) return 0;
        if(dp[leftClientIncreaseGoal] != -1) return dp[leftClientIncreaseGoal];

        int ret = 987654321;
        for (City city : cities) {
            ret = Math.min(ret, func(leftClientIncreaseGoal - city.customer) + city.price);
        }
        return dp[leftClientIncreaseGoal] = ret;
    }

    void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        clientIncreaseGoal = Integer.parseInt(st.nextToken());
        numberOfCity = Integer.parseInt(st.nextToken());

        cities = new City[numberOfCity];
        for (int i = 0; i < numberOfCity; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int price = Integer.parseInt(st.nextToken());
            int customer = Integer.parseInt(st.nextToken());
            cities[i] = new City(customer, price);
        }
        dp = new int[clientIncreaseGoal + 1];
        Arrays.fill(dp, -1);

        System.out.println(func(clientIncreaseGoal));
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1106().solution();
    }
}
