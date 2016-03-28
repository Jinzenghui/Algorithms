
import java.util.Scanner;

public class DepthFirstSearch{
	
	private boolean[] marked;
	private int count;
	
	private static Scanner scanner = new Scanner(System.in);
	
	public DepthFirstSearch(Graph G, int s){
		
		marked = new boolean[G.V()];
		dfs(G, s);
		
	}
	
	private void dfs(Graph G, int v){
		
		count++;
		marked[v] = true;
		
		for(int w : G.adj(v)){
			if(!marked[w]){
				dfs(G,w);
			}
		}
		
	}
	
	public boolean marked(int v){
		
		return marked[v];
	
	}
	
	public int count(){
		
		return count;
		
	}
	
	public static void main(String[] args){
		
		Graph G = new Graph(scanner);
		
		int s = Integer.parseInt(args[0]);
		
		DepthFirstSearch search = new DepthFirstSearch(G, s);
		
		for(int v = 0; v < G.V(); v++){
			if(search.marked(v))
				System.out.print(v + " ");
		}
		
		System.out.println();
		if(search.count() != G.V())
			System.out.println("NOT connected");
		else
			System.out.println("connected");
		
	}	
	
}



































