
//��������
//����˼�룺����������һ�ַ��ε������㷨������һ������ֳ����������飬�������ֶ������򡣿�������͹鲢�����ǻ�����
//����������������ķ�ʽ���ǵ����������鶼����ʱ��������Ҳ����Ȼ������

//�з�ʹ�������������������
//1������ĳ��j,a[j]�Ѿ��Ŷ���
//2��a[lo]��a[j-1]�е�����Ԫ�ض�������a[j]��
//3��a[j+1]��a[hi]�е�����Ԫ�ض���С��a[j]��

//�зֵĲ��ԣ�
//�������ȡa[lo]��Ϊ�з�Ԫ�أ����Ǹ����ᱻ�Ŷ���Ԫ�أ�Ȼ�����Ǵ��������˿�ʼ����ɨ��ֱ���ҵ�һ�����ڵ�������Ԫ�أ���
//��������Ҷ˿�ʼ����ɨ��ֱ���ҵ�һ��С�ڵ�������Ԫ�أ�������Ԫ����Ȼ��û���Ŷ��ģ���˽������ǵ�λ�á���˼��������Ǿ�
//���Ա�֤��ָ��i�����Ԫ�ض��������з�Ԫ�أ���ָ��j���Ҳ�Ԫ�ض���С���з�Ԫ�ء�������ָ������ʱ������ֻ��Ҫ���з�Ԫ��a[lo]
//�������������Ҳ��Ԫ��a[j]����Ȼ�󷵻�j���ɡ�

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
		int j = partition(a, lo, hi);       //�������ָ��������λ�ã�����з�
		sort(a, lo, j-1);					//���з�����Ԫ������
		sort(a, j+1, hi);					//���з��Ҳ��Ԫ������
		assert isSorted(a, lo, hi);
	}
	
	//�зֵĹ���
	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo;
		int j = hi + 1;
		Comparable v = a[lo];
		
		while(true){
			
			//��ָ�����Ԫ�أ�ֱ����һ����a[lo]С��Ԫ��
			while(less(a[++i], v))
				if(i == hi)
					break;
				
			//��ָ�����ֱ���ҵ���a[lo]���Ԫ��	
			while(less(v, a[--j]))
				if(j == lo)
					break;
				
			if(i >= j)
				break;
			
			exch(a, i, j);
		}
		
		exch(a, lo, j);
		
		return j;  //�����зֵ�λ��
	}
	
	//ѡ����������С��K��Ԫ��
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
	
	//���������ԭʼ˳��
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