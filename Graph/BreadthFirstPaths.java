
import java.util.Scanner;

public class BreadthFirstPaths{
	
	private static final int INFINITY = Integer.MAX_VALUE;
	
	private boolean[] marked;
	
	private int[] edgeTo;
	private int[] distTo;
	
	private static Scanner scanner = new Scanner(System.in);
	
	public BreadthFirstPaths(Graph G, int s){
		
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		bfs(G, s);
		
		assert check(G, s);
		
	}
	
	public BreadthFirstPaths(Graph G, Iterable<Integer> sources){
		
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		
		for(int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		
		bfs(G, sources);
		
	}
	
	private void bfs(Graph G, int s){
		
		Queue<Integer> q = new Queue<Integer>();
		
		for(int v=0; v < G.V(); v++)
			distTo[v] = INFINITY;
		
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);
		
		while(!q.isEmpty()){
			
			int v = q.dequeue();
			for(int w : G.adj(v)){
				
				if(!marked[w]){
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
				}
					
			}
				
		}
		
	}
	
	private void bfs(Graph G, Iterable<Integer> sources){
		
		Queue<Integer> q = new Queue<Integer>();
		for(int s:sources){
			
			marked[s] = true;
			distTo[s] = 0;
			q.enqueue(s);
		
		}
		
		while(!q.isEmpty())
		{
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
		for(x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		
		path.push(x);
		return path;
	}
	
	private boolean check(Graph G, int s){
		
		if(distTo[s] != 0){
			
			System.out.println("distance of source " + s + " to itself = " + distTo[s]);
			
			return false;
			
		}
		
		
		for(int v = 0 ; v < G.V(); v++){
			
			for(int w:G.adj(v)){
				
				if(hasPathTo(v) != hasPathTo(w)){
					
					System.out.println("edge " + v + "-" + w);
					System.out.println("hasPathTo(" + v + ") = " + hasPathTo(v));
					System.out.println("hasPathTo(" + w + ") = " + hasPathTo(w));
					
					return false;
				}
				if(hasPathTo(v) && (distTo[w] > distTo[v] + 1)){
					
					System.out.println("edge " + v + "-" + w);
					System.out.println("distTo[" + v + "] = " + distTo[v]);
					System.out.println("distTo[" + w + "] = " + distTo[w]);
					
					return false;
				}
					
			}
			
		}
		
		for(int w = 0; w < G.V(); w++){
			
			if(!hasPathTo(w) || w==s)
				continue;
			
			int v = edgeTo[w];
			if(distTo[w] != distTo[v] + 1){
				
				System.out.println("shortest path edge " + v + "-" + w);
				System.out.println("distTo[" + v + "] = " +distTo[v]);
				System.out.println("distTo[" + w + "] = " + distTo[w]);
				
				return false;
			}
			
		}
		
		return true;
		
	}
	
	public static void main(String[] args){
		
		Graph G = new Graph(scanner);
		
		int s = Integer.parseInt(args[0]);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
		
		for(int v = 0; v < G.V(); v++){
			
			if(bfs.hasPathTo(v)){
				
				System.out.printf("%d to %d (%d): ", s, v, bfs.distTo(v));
				
				for(int x : bfs.pathTo(v)){
					if(x == s)
						System.out.print(x);
					else
						System.out.print("-" + x);
				}
				
				System.out.println();
				
			}
			else{
				System.out.printf("%d to %d (-): not connected\n", s, v);
			}	
		}	
	}	
}
























