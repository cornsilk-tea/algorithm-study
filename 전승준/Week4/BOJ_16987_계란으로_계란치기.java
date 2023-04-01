package Week4;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16987_계란으로_계란치기 {
	static class Egg {
		int durability;
		int weight;
		
		Egg(int durability, int weight){
			this.durability = durability;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Egg [durability=" + durability + ", weight=" + weight + "]";
		}
		
	}
	static int Ans = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		Egg[] eggs = new Egg[T];
		
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), 
							  Integer.parseInt(st.nextToken()));
		}
		
		pickEgg(0, eggs, new boolean[eggs.length]);

		System.out.println(Ans);
	}
	
	private static void pickEgg(int idx, Egg[] eggs, boolean[] v) {
		if(idx == eggs.length) {
			int cnt = 0;
			
			for(int i=0; i<eggs.length; i++) {
				System.out.println(eggs[i].toString());
				if(eggs[i].durability <= 0) cnt++;
			}
			
			Ans = Math.max(Ans, cnt);
			return;
		}
		
		
		for(int i=0; i<eggs.length; i++) {
			if(i == idx || eggs[idx].durability <= 0) continue;
			if(!v[i]) {
				v[idx] = true;
				if(eggs[idx].durability > 0) {
					eggs[i].durability -= eggs[idx].weight;
					eggs[idx].durability -= eggs[i].weight;
					pickEgg(idx+1, eggs, v);
					eggs[i].durability += eggs[idx].weight;
					eggs[idx].durability += eggs[i].weight;
				}
				pickEgg(idx+1, eggs, v);
				v[idx] = false;
			}
			System.out.println();
			System.out.println("idx:"+idx+" i:"+i);
			print(eggs);
		}
		
	}

	private static void print(Egg[] eggs) {
		for(int i=0; i<eggs.length; i++) {
			System.out.println(eggs[i].toString());
		}
		System.out.println();
	}

}
