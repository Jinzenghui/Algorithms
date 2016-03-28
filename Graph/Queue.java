import java.util.Iterator;
import java.util.NoSuchElementException;


//队列是先进先出的原则，所要实现在链表头部删除元素，在链表尾部插入元素

public class Queue<Item> implements Iterable<Item>{

	//实例化两个结点，用于入列和出列
	private Node<Item> first;
	private Node<Item> last;
	private int N;
	
	//结点的构造函数
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	//实例化一个队列
	public Queue(){
		first = null;
		last = null;
		N = 0;
	}
	
	//检测队列是否为空
	public boolean isEmpty(){
		return first == null;
	}
	
	//返回队列的大小
	public int size(){
		return N;
	}
	
	//返回队列头部的第一个元素
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		return first.item;
	}

	//入列函数，在队列的尾部入列
	public void enqueue(Item item){
		Node<Item> oldlast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		
		if(isEmpty())
			first = last;
		else 
			oldlast.next = last;
		
		N++;
	}
	
	//出列函数，在队列的头部操作。
	public Item dequeue(){
		
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		Item item = first.item;
		
		first = first.next;
		N--;
		
		if(isEmpty())
			last = null;
		
		return item;
	}
	
	//将队列转化成String
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item item:this)
			s.append(item + " ");
		return s.toString();
	}
	
	//实现队列的迭代接口
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
			current  = current.next;
			return item;
		}
		
	}
}