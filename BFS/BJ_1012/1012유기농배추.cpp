#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <math.h>
#include <stack>
#include <queue>
#include <set>
#include <unordered_map>
using namespace std;

//상하좌우 이동
int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

void larva(vector<vector<int>>& arr, int x, int y, vector<vector<bool>>& tf, int M, int N)
{
	queue<pair<int, int>> q;

	q.push({ x, y });
	tf[x][y] = true;

	while (!q.empty())
	{
		// A1.좌표의 큐 값을 가져옴(상하좌우 이동한 좌표 포함).
		int xx = q.front().first;
		int yy = q.front().second;

		//필요없으니 제거.
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			// A2. 이동 범위를 확장시키기 위해 신규 좌표값이 필요함(A1을 한 이유).
			int nx = xx + dx[i];
			int ny = yy + dy[i];

			//범위 초과 제한.
			if (nx >= 0 && nx < M && ny >= 0 && ny < N)
			{
				if (arr[nx][ny] == 1 && !tf[nx][ny])
				{
					q.push({ nx, ny });
					tf[nx][ny] = true;
				}
			}
		}
	}
}

int main()
{
	cin.tie(NULL);
	cout.tie(NULL);
	ios_base::sync_with_stdio(false);

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++)
	{
		int M, N, K;
		cin >> M >> N >> K;

		vector<vector<int>> arr(M, vector<int>(N, 0));

		//같은 '1'이여도 방문여부를 판별하기 위해.
		vector<vector<bool>> tf(M, vector<bool>(N, false));

		for (int i = 0; i < K; i++)
		{
			int x, y;
			cin >> x >> y;
			arr[x][y] = 1;
		}

		int cnt = 0;

		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (arr[i][j] == 1 && !tf[i][j])
				{
					larva(arr, i, j, tf, M, N);
					cnt++;
				}
			}
		}
		cout << cnt << "\n";
	}

	return 0;
}