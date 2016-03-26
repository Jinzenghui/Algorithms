import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	
	private Node<Item> first;
	private int N;
	
	//一个节点Node对象含有两个实例变量，类型分别为Item(参数类型)和Node。
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	//初始化一个栈的实例，为null;
	public Stack(){
		first = null;
		N = 0;
	}
	
	//检测栈是否为空
	public boolean isEmpty(){
		return first ==null;
	}
	
	//获取栈的大小
	public int size(){
		return N;
	}
	
	//向栈中压入一个元素。
	//在链表的开头插入一个结点。首先将first保存在oldfirst中，
	//然后将一个新结点赋予first，将first的next设为oldfirst,栈的大小加1。
	public void push(Item item){
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	//在栈中，删除一个元素
	//栈是后进先出的原则，所以需要在链表头删除一个结点。
	//只需要将first只想first.next即可。栈的大小减1.
	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	//获得栈头的元素。
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		return first.item;
	}
	
	//将栈对象转变成String输出
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item item:this)
			s.append(item + " ");
		
		return s.toString();
	}
	
	//实现可迭代接口。Iterator类必须含有两个方法：hasNext()(返回一个布尔值)
	//和next()(返回集合中的一个泛型元素)
	public Iterator<Item> iterator(){
		return new ListIterator<Item>(first);
	}

	private class ListIterator<Item> implements Iterator<Item>{
		
		private Node<Item> current;
		
		public ListIterator(Node<Item> first){
			current = first;
		}
		
		public boolean hasNext(){
			return current != null;
		}
		
		public void remove(){
			throw new UnsupportedOperationException();
		}
		
		public Item next(){
			if(!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}
	
}