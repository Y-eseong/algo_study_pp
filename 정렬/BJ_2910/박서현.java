import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		Map<Integer, Info> message = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			if(!message.containsKey(n)) {
				message.put(n, new Info(message.size()));
			}
			else {
				message.get(n).count++;
			}
			
		}
		
		List<Map.Entry<Integer, Info>> entryList = new LinkedList<>(message.entrySet());
		
		Collections.sort(entryList, new Comparator<Map.Entry<Integer, Info>>() {
			@Override
			public int compare(Map.Entry<Integer, Info> o1, Map.Entry<Integer, Info> o2) {
				Info e1 = o1.getValue();
				Info e2 = o2.getValue();
				
				if(e1.count == e2.count) 
					return e1.idx - e2.idx;

				return -(e1.count - e2.count);
			}
		});
		
		for(Map.Entry<Integer, Info> entry : entryList) {
			for(int i=0; i<entry.getValue().count; i++) {
				sb.append(entry.getKey()).append(" ");
			}
		}
		
		System.out.println(sb.toString());
		
	}
}

class Info {
	int idx = 0;
	int count = 1;
	
	Info(int idx) {
		this.idx = idx;
	}
}
