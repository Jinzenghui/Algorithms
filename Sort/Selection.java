import java.util.Comparator;

//选择排序
//思路：遍历数据中的每一个元素，并将它和其后面的元素一次比较，若其小于后面
//最小的那个数据，则将其交换，


//Comparator是一个比较器，我们可以通过控制重写它，来规定一个类的排列规则
//Comparator接口包括两个函数：
//public interface Comparator<T>{
//	int compare(T o1, T o2);
//  boolean equals(Object obj);	
//}

//Comparable是一个排序接口，一个类实现了Compare接口，代表该类支持排序
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
			
			//找到i之后最小的数据
			for(int j = i + 1; j < N; j++){
				if(less(a[j], a[min]))
					min = j;
			}
			
			exch(a, i, min);   //交换
			assert isSorted(a, 0, i); //断言确定0~i之间的元素是有序的
		}
		
		assert isSorted(a);   //断言确定数据类型a中的数据时有序的.
	}
	
	//按照比较规则c来排序
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