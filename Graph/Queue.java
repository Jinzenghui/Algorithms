import java.util.Iterator;
import java.util.NoSuchElementException;


//�������Ƚ��ȳ���ԭ����Ҫʵ��������ͷ��ɾ��Ԫ�أ�������β������Ԫ��

public class Queue<Item> implements Iterable<Item>{

	//ʵ����������㣬�������кͳ���
	private Node<Item> first;
	private Node<Item> last;
	private int N;
	
	//���Ĺ��캯��
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	//ʵ����һ������
	public Queue(){
		first = null;
		last = null;
		N = 0;
	}
	
	//�������Ƿ�Ϊ��
	public boolean isEmpty(){
		return first == null;
	}
	
	//���ض��еĴ�С
	public int size(){
		return N;
	}
	
	//���ض���ͷ���ĵ�һ��Ԫ��
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Queue underflow");
		
		return first.item;
	}

	//���к������ڶ��е�β������
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
	
	//���к������ڶ��е�ͷ��������
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
	
	//������ת����String
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item item:this)
			s.append(item + " ");
		return s.toString();
	}
	
	//ʵ�ֶ��еĵ����ӿ�
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