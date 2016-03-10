import java.util.Scanner;

public class QueueSample{
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args){
		
		Queue<String> q = new Queue<String>();
		
		while(scanner.hasNext()){
			String item = scanner.next();
			if(!item.equals("-"))
				q.enqueue(item);
			else if(!q.isEmpty())
				System.out.print(q.dequeue() + " ");
			
		}
		
		System.out.println("(" + q.size() + " left on queue)");		
		
	}
	
}