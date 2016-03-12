import java.util.Comparator;

//ѡ������
//˼·�����������е�ÿһ��Ԫ�أ���������������Ԫ��һ�αȽϣ�����С�ں���
//��С���Ǹ����ݣ����佻����


//Comparator��һ���Ƚ��������ǿ���ͨ��������д�������涨һ��������й���
//Comparator�ӿڰ�������������
//public interface Comparator<T>{
//	int compare(T o1, T o2);
//  boolean equals(Object obj);	
//}

//Comparable��һ������ӿڣ�һ����ʵ����Compare�ӿڣ��������֧������
//
// public interface Comparable<T>{
//		public int compareTo(T o);	
//}
//

public class Selection{
	
	private Selection(){
		
	}
	
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			
			int min = i;
			
			//�ҵ�i֮����С������
			for(int j = i + 1; j < N; j++){
				if(less(a[j], a[min]))
					min = j;
			}
			
			exch(a, i, min);   //����
			assert isSorted(a, 0, i); //����ȷ��0~i֮���Ԫ���������
		}
		
		assert isSorted(a);   //����ȷ����������a�е�����ʱ�����.
	}
	
	//���ձȽϹ���c������
	public static void sort(Object[] a, Comparator c){
		
		int N = a.length;
		
		for(int i = 0; i < N; i++){
			 
			 int min = i;
			 
			 for(int j = i+1; j < N; j++){
				
				if(less(c, a[j], a[min])
					 min = j;

			 }
			 
			 exch(a, i, min);
			 assert isSorted(a, c, 0, i);
		}
		
		assert isSorted(a, c);
		
	}
	
	private static boolean less(Comparable v, Comparable w){
		
		return v.compareTo(w) < 0;
		
	}
	
	private static boolean less(Comparator c, Object v, Object w){
		
		return c.compare(v,w) < 0;
		
	}
	
	private static void exch(Object[] a, int i, int j){
		Object swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}
	
	private static boolean isSorted(Comparable[] a){
		return isSorted(a, 0, a.length - 1);
	}
	
	private static boolean isSorted(Comparable[] a, int lo, int hi){
		
		for(int i = lo + 1; i <= hi; i++)
			if(less(a[i], a[i-1])
				return false;
			
		return true;
	}
	
	private static void show(Comparable[] a){
		
		for(int i = 0; i < a.length; i++){
		
			System.out.println(a[i]);
		
		}
	}
	
}