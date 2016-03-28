
import java.util.Scanner;

public class DepthFirstDirectedPaths{
	
	private static Scanner scanner = new Scanner(System.in);
	
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	
	public DepthFirstDirectedPaths(Digraph G, int s){
	
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
		
	}
	
	private void dfs(Digraph G, int v){
		
		marked[v] = true;
		
		for(int w:G.adj(v)){
			
			if(!marked[w]){
				
				edgeTo[w] = v;
				
				dfs(G, w);
				
			}
			
		}
		
		
		
	}
	
	
	
	
	
	
	
}
