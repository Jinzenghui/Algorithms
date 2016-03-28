
import java.util.Scanner;

public class BreadthFirstDirectedPaths{
	
	private static Scanner scanner = new Scanner(System.in);
	
	private static final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	
	public BreadthFirstDirectedPaths(Digraph G, int s){
		
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		
		bfs(G, s);
		
	}
	
	public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources){
		
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		for(int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		
		bfs(G, sources);
		
	}
	
	private void bfs(Digraph G, int s){
		
		Queue<Integer> q = new Queue<Integer>();
		marked[s] = true;
		distTo[s] = 0;
		
		q.enqueue(s);
		while(!q.isEmpty()){
			
			int v = q.dequeue();
			for(int w:G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}	
		}
	}
	
	private void bfs(Digraph G, Iterable<Integer> sources){
		
		Queue<Integer> q = new Queue<Integer>();
		for(int s:sources){
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
		}
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w:G.adj(v)){
				if(!marked[w]){
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}

	}

	public boolean hasPathTo(int v){
		
		return marked[v];
	
	}
	
	public int distTo(int v){
		
		return distTo[v];
		
	}
	
	public Iterable<Integer> pathTo(int v){
		
		if(!hasPathTo(v))
			return null;
		
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for(int x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		
		path.push(x);
		
		return path;
		
	}
	
	public static void main(String[] args){
		
		Digraph G = new Digraph(scanner);
		
		int s = Integer.parseInt(args[0]);
		
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, s);
		
		for(int v = 0; v < G.V(); v++){
			
			if(bfs.hasPathTo(v)){
				
				System.out.printf("%d to (%d): ", s, v, bfs.distTo(v));
				for(int x : bfs.pathTo(v)){
					
					if(x == s)
						System.out.print(x);
					else
						System.out.print("->" + x);
				}
				
				System.out.println();
			}else{
				
				System.out.printf("%d to %d (-): not connected\n", s, v);
			}
			
		}
		
	}
	
}

