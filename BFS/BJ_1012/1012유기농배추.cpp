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

//�����¿� �̵�
int dx[] = { -1, 1, 0, 0 };
int dy[] = { 0, 0, -1, 1 };

void larva(vector<vector<int>>& arr, int x, int y, vector<vector<bool>>& tf, int M, int N)
{
	queue<pair<int, int>> q;

	q.push({ x, y });
	tf[x][y] = true;

	while (!q.empty())
	{
		// A1.��ǥ�� ť ���� ������(�����¿� �̵��� ��ǥ ����).
		int xx = q.front().first;
		int yy = q.front().second;

		//�ʿ������ ����.
		q.pop();

		for (int i = 0; i < 4; i++)
		{
			// A2. �̵� ������ Ȯ���Ű�� ���� �ű� ��ǥ���� �ʿ���(A1�� �� ����).
			int nx = xx + dx[i];
			int ny = yy + dy[i];

			//���� �ʰ� ����.
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

		//���� '1'�̿��� �湮���θ� �Ǻ��ϱ� ����.
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