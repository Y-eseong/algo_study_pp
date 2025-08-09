import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static boolean[] isUsed;
	static int N, M;
	static int minCityDistance = Integer.MAX_VALUE;
	static ArrayList<house> houses = new ArrayList<>();
	static ArrayList<chickenHouse> chickenHouses = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] city = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 1) {
					houses.add(new house(i, j));
				} else if (city[i][j] == 2) {
					chickenHouses.add(new chickenHouse(i, j));
				}
			}
		}

		isUsed = new boolean[chickenHouses.size()];
		combination(0, 0);
		System.out.println(minCityDistance);
	}

	private static void combination(int start, int count) {
		if (count == M) {
			calculate();
			return;
		}

		for (int i = start; i < isUsed.length; i++) {
			if (!isUsed[i]) {
				isUsed[i] = true;
				combination(i + 1, count + 1);
				isUsed[i] = false;
			}
		}

	}

	private static void calculate() {
		int cityDistance = 0; // 최솟값들의 합
		for (house house : houses) {
			int chickenDistance = Integer.MAX_VALUE; // 집에서 치킨집까지 거리들 중 최솟값
			for (int i = 0; i < chickenHouses.size(); i++) {
				if (isUsed[i]) {
					chickenDistance = Math.min(chickenDistance, Math.abs(house.houseRow - chickenHouses.get(i).chickenHouseRow)
							+ Math.abs(house.houseColumn - chickenHouses.get(i).chickenHouseColumn));
				}
			}
			cityDistance += chickenDistance;
		}
		minCityDistance = Math.min(minCityDistance, cityDistance);
		return;
	}

	static class chickenHouse {
		int chickenHouseRow;
		int chickenHouseColumn;

		public chickenHouse(int chickenHouseRow, int chickenHouseColumn) {
			this.chickenHouseRow = chickenHouseRow;
			this.chickenHouseColumn = chickenHouseColumn;
		}
	}

	static class house {
		int houseRow;
		int houseColumn;

		public house(int houseRow, int houseColumn) {
			this.houseRow = houseRow;
			this.houseColumn = houseColumn;
		}
	}
}