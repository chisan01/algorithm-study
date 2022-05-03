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
    int[] minSpentMoney;

    final int INF = 987654321;

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
        minSpentMoney = new int[clientIncreaseGoal + 1];
        Arrays.fill(minSpentMoney, INF);

        // base case
        minSpentMoney[0] = 0;
        for (int curNumberOfClients = 0; curNumberOfClients < clientIncreaseGoal; curNumberOfClients++) {
            if(minSpentMoney[curNumberOfClients] == INF) continue;
            for (City city : cities) {
                int nextNumberOfClients = curNumberOfClients + city.customer;
                if(nextNumberOfClients > clientIncreaseGoal) nextNumberOfClients = clientIncreaseGoal;

                minSpentMoney[nextNumberOfClients] = Math.min(minSpentMoney[nextNumberOfClients], minSpentMoney[curNumberOfClients] + city.price);
            }
        }

        System.out.println(minSpentMoney[clientIncreaseGoal]);
    }

    public static void main(String[] args) throws Exception {
        new BOJ_1106().solution();
    }
}
