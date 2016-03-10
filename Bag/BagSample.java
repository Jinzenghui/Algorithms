import java.util.Scanner;

public class BagSample{
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args){
		Bag<String> bag = new Bag<String>();
		while(scanner.hasNext()){
			String item = scanner.next();
			bag.add(item);
		}
		
		System.out.println("size of bag = " + bag.size());
		
		for(String s:bag){
			System.out.println(s);
		}
	}
	
}