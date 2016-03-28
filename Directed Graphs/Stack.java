import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	
	private Node<Item> first;
	private int N;
	
	//һ���ڵ�Node����������ʵ�����������ͷֱ�ΪItem(��������)��Node��
	private static class Node<Item>{
		private Item item;
		private Node<Item> next;
	}
	
	//��ʼ��һ��ջ��ʵ����Ϊnull;
	public Stack(){
		first = null;
		N = 0;
	}
	
	//���ջ�Ƿ�Ϊ��
	public boolean isEmpty(){
		return first ==null;
	}
	
	//��ȡջ�Ĵ�С
	public int size(){
		return N;
	}
	
	//��ջ��ѹ��һ��Ԫ�ء�
	//������Ŀ�ͷ����һ����㡣���Ƚ�first������oldfirst�У�
	//Ȼ��һ���½�㸳��first����first��next��Ϊoldfirst,ջ�Ĵ�С��1��
	public void push(Item item){
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	//��ջ�У�ɾ��һ��Ԫ��
	//ջ�Ǻ���ȳ���ԭ��������Ҫ������ͷɾ��һ����㡣
	//ֻ��Ҫ��firstֻ��first.next���ɡ�ջ�Ĵ�С��1.
	public Item pop(){
		if(isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		Item item = first.item;
		first = first.next;
		N--;
		return item;
	}
	
	//���ջͷ��Ԫ�ء�
	public Item peek(){
		if(isEmpty())
			throw new NoSuchElementException("Stack underflow");
		
		return first.item;
	}
	
	//��ջ����ת���String���
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item item:this)
			s.append(item + " ");
		
		return s.toString();
	}
	
	//ʵ�ֿɵ����ӿڡ�Iterator����뺬������������hasNext()(����һ������ֵ)
	//��next()(���ؼ����е�һ������Ԫ��)
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