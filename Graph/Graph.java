
import java.util.Scanner;

public class Graph{

	private static final String NEWLINE = System.getProperty("line.separator");
	
	private static Scanner scanner = new Scanner(System.in);
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	
	//创建一个含有V个顶点但不含有边的图
	public Graph(int V){
		
		if(V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.E = E;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++){
			adj[v] = new Bag<Integer>();
		}
		
	}
	
	public Graph(Scanner scanner){
		
		this(scanner.nextInt());
		int E = scanner.nextInt();
		if(E < 0 )
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		
		for(int i = 0; i < E; i++){
			
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			addEdge(v,w);
		}
	}
	
	public Graph(Graph G){
		
		this(G.V());
		this.E = G.E();
		
		for(int v = 0; v < G.V(); v++){
			
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w : G.adj[v]){
				reverse.push(w);
			}
			for(int w : reverse){
				adj[v].add(w);
			}

		}
				
	}
	
	//返回顶点数
	public int V(){
		return V;
	}
	
	//返回边数
	public int E(){
		return E;
	}
	
	private void validateVertes(int v){
		if(v < 0 || v >= V){
			throw new IndexOutOfBoundsException("vertex " + v + " is not between  0 and " + (V-1));
		}
	}
	
	//向图中添加一条边v-w
	public void addEdge(int v, int w){
		validateVertes(v);
		validateVertes(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}
	
	public Iterable<Integer> adj(int v){
		validateVertes(v);
		return adj[v];
	}
	
	public int degree(int v){
		validateVertes(v);
		return adj[v].size();
	}
	
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for(int v = 0; v < V; v++){
			s.append(v + ": ");
			for(int w : adj[v]){
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	public static void main(String[] args){
		
		Graph G = new Graph(scanner);
		System.out.println(G);
	}
	
	
	
	
	
	
	
}