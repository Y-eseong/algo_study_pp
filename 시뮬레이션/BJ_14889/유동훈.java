package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
    /*
     * 소요시간 2시간
     * 메모리: 55676KB
     * 시간: 596ms
     * 회고:나는야 알알못
     */
    
    static int[] teamA = new int[21];
    static int N;
    static int halfN;
    static int[][] arr;
    static int people = 1;
    static int minSum = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        halfN = N / 2;
        arr = new int[N + 1][N + 1];
        
        // 1부터 N까지 (zero-index 조심)
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 1);
        
        System.out.println(minSum);
    }

    // 백트래킹으로 Team A 후보군 정하기 (중복되지 않게 오름차순으로 N개 중 절반 선택)
    public static void dfs(int k, int idx) {
        if (k == N / 2) {
            calc(); // Team A 다 모였으면 계산 로직 호출
            return;
        }

        // static people을 통해서 team idx 관리
        for (int i = idx; i <= N; i++) {
            if (teamA[people - 1] >= i) continue; 
            teamA[people++] = i;
            dfs(k + 1, idx + 1);
            people--;
        }
        // team 배열 -> idx 1 ~ 3 에 선수 번호 저장
        // ex)             0 1 2 3
        //           team[]  0 1 2 5
        // anotherTeam[]  0 3 4 6
    }

    
    private static void calc() {    
        int[] teamB = new int[21 - halfN];
        int[] candidate = new int[21];
        
        // Team B 후보군 정하기
        for (int i = 1; i <= N; i++) {
            if (teamA[i] > 0) {
                candidate[teamA[i]] = 1;
            }
        }
        
        int people = 1;
        for (int i = 1; i <= N; i++) {
            if (candidate[i] == 0) {
                teamB[people++] = i;
            }
        }
        
        // Team A
        int sumA = 0;
        for (int i = 1; i <= halfN; i++) {
            if (i + 1 > halfN) break; 
            for (int j = i + 1; j <= halfN; j++) {
                System.out.println("teamA[]: " + teamA[i] + " " + teamA[j]);
                System.out.println("a ij : " + arr[teamA[i]][teamA[j]]);
                System.out.println("a ji : " + arr[teamA[j]][teamA[i]]);
                sumA += arr[teamA[i]][teamA[j]] + arr[teamA[j]][teamA[i]];
            }
        }
        
        // Team B
        int sumB = 0;
        for (int i = 1; i <= halfN; i++) {
            if (i + 1 > halfN) break; 
            for (int j = i + 1; j <= halfN; j++) {
                System.out.println("teamB[]: " + teamB[i] + " " + teamB[j]);
                System.out.println("b ij : " + arr[teamB[i]][teamB[j]]);
                System.out.println("b ji : " + arr[teamA[j]][teamA[i]]);
                sumB += arr[teamB[i]][teamB[j]] + arr[teamB[j]][teamB[i]];
            }
        }
        
        System.out.println("--------");
        int abs = Math.abs(sumA - sumB);
        
        minSum = Math.min(minSum, abs);
    }
}