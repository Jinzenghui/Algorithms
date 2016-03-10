import java.util.Scanner;

public class StackSample{
	
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args){
		
		Stack<String> s = new Stack<String>();
		
		while(scanner.hasNext()){
			String item = scanner.next();
			if(!item.equals("-"))
				s.push(item);
			else if(!s.isEmpty())
				System.out.print(s.pop()+" ");
		}
		System.out.println("(" + s.size() + " left on stack)");
	}

}