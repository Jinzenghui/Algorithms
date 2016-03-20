
import java.util.Scanner;

public class QuickFindUF{
	
	private static Scanner scanner = new Scanner(System.in);
	
	private int[] id;
	private int count;
	
	public QuickFindUF(int N){
		
		count = N;
		id = new int[N];
		for(int i=0; i < N; i++)
			id[i] = i;
		
		
	}
	
	public int count(){
		
		return count;
		
	}
	
	public int find(int p){
		
		validate(p);
		
		return id[p];
		
	}
	
	private void validate(int p){
		int N = id.length;
		if(p < 0 || p >= N){
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
		}
	}
	
	public boolean connected(int p, int q){
		validate(p);
		validate(q);
		return id[p] == id[q];
	}
	
	public void union(int p, int q){
		
		validate(p);
		validate(q);
		
		int pID = id[p];
		int qID = id[q];
		
		if(pID == qID)
			return;
		
		for(int i = 0; i < id.length; i++)
			if(id[i] == pID)
				id[i] = qID;
		count--;
	}
	
	public static void main(String[] args){
		int N = scanner.nextInt();
		QuickFindUF uf = new QuickFindUF(N);
		while(scanner.hasNext())
		{
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