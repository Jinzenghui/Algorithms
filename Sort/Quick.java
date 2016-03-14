
//快速排序
//排序思想：快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立排序。快速排序和归并排序是互补的
//快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了

//切分使数组满足的三个条件：
//1：对于某个j,a[j]已经排定；
//2：a[lo]到a[j-1]中的所有元素都不大于a[j]；
//3：a[j+1]到a[hi]中的所有元素都不小于a[j]；

//切分的策略：
//先随意地取a[lo]作为切分元素，即那个将会被排定的元素，然后我们从数组的左端开始向右扫描直到找到一个大于等于它的元素，再
//从数组的右端开始向左扫描直到找到一个小于等于它的元素，这两个元素显然是没有排定的，因此交换它们的位置。如此继续，我们就
//可以保证左指针i的左侧元素都不大于切分元素，右指针j的右侧元素都不小于切分元素。当两个指针相遇时，我们只需要将切分元素a[lo]
//和左子数组最右侧的元素a[j]交换然后返回j即可。

import java.util.Random;

public class Quick{
	
	private Quick(){}
	
	public static void sort(Comparable[] a){
		shuffle(a);
		sort(a, 0, a.length - 1);
		assert isSorted(a);
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partition(a, lo, hi);       //获得左右指针相遇的位置，完成切分
		sort(a, lo, j-1);					//对切分左侧的元素排序
		sort(a, j+1, hi);					//对切分右侧的元素排序
		assert isSorted(a, lo, hi);
	}
	
	//切分的过程
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		
		while(true){
			
			//左指针遍历元素，直到找一个比a[lo]小的元素
			while(less(a[++i], v))
				if(i == hi)
					break;
				
			//右指针遍历直到找到比a[lo]大的元素	
			while(less(v, a[--j]))
				if(j == lo)
					break;
				
			if(i >= j)
				break;
			
			exch(a, i, j);
		}
		
		exch(a, lo, j);
		
		return j;  //返回切分的位置
	}
	
	//选出数组中最小的K个元素
	public static Comparable select(Comparable[] a, int k){
		
		if(k < 0 || k >= a.length){
			throw new IndexOutOfBoundsException("Selected element out of bounds");
		}
		
		shuffle(a);
		int lo = 0, hi = a.length - 1;
		while(hi > lo){
			int i = partition(a, lo, hi);
			if(i > k)
				hi = i - 1;
			else if(i < k)
				lo = i + 1;
			else return a[i];
		}
		
		return a[lo];
	}
	
	private static boolean less(Comparable v, Comparable w){
		
		return v.compareTo(w) < 0;
		
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
		
		for(int i = lo; i <= hi; i++)
			if(less(a[i], a[i-1]))
				return false;
		
		return true;
	}
	
	private static void show(Comparable[] a){
		
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i]);
		
	}
	
	public static int uniform(int n){
		if(n <= 0)
			throw new IllegalArgumentException("Parameter N must be positive");
		
		return random.nextInt(n);
	}
	
	//打乱数组的原始顺序
	public static void shuffle(int[] a){
		
		if(a == null)
			throw new NullPointerException("argument array is null");
		
		int n = a.length;
		for(int i=0; i < n; i++){
			int r = i + uniform(n-i);
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}
	
}