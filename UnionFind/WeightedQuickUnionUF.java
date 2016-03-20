
import java.util.Scanner;

public class WeightedQuickUnionUF{
	
	private static Scanner scanner = new Scanner(System.in);
	
	private int[] parent;
	private int[] size;
	private int count;
	
	public WeightedQuickUnionUF(int N){
		
		count = N;
		parent = new int[N];
		size = new int[N];
		
		for(int i=0; i<N; i++){
			parent[i] = i;
			size[i] = 1;
		}
	}
	
	public int count(){
		return count;
	}
	
	public int find(int p){
		validate(p);
		while(p != parent[p])
		{
			p = parent[p];
		}
		return p;
	}
	
	private void validate(int p){
		int N = parent.length;
		if(p < 0 || p >= N){
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
		}
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public void union(int p, int q){
		
		int rootP = find(p);
		int rootQ = find(q);
		
		if(rootP == rootQ)
			return;
		
		if(size[rootP] < size[rootQ]){
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		}else{
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		}
		
		count--;
		
	}
	
	public static void main(String[] args){
		int N = scanner.nextInt();
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
		while(scanner.hasNext()){
			int p = scanner.nextInt();
			int q = scanner.nextInt();
			if(uf.connected(p,q))
				continue;
			uf.union(p,q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + " components");
	}
	
}













































