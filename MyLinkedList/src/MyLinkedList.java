

public class MyLinkedList {
	ListNode head;
	ListNode tail;
	
	
	private  class ListNode
	{
		int val;
		ListNode next;
		public ListNode(int val) {this.val = val;}
		@Override
		public String toString() {return ""+this.val;}
	}
	
	
	public MyLinkedList() {
		// TODO Auto-generated constructor stub
		head = null;
		tail = null;
	}
	
	
	public MyLinkedList(int val)
	{
		head = new ListNode(val);
		tail = head;
	}
	
	
	void add(int newval)
	{
		if (head==null)
		{
			head = new ListNode(newval);
			tail = head;
		}
		else
		{
			tail.next = new ListNode(newval);
			tail = tail.next;
			
				

		}
		//System.out.println(head.val);
	}
	
	
	boolean contains(int target)
	{
		if (head!=null)
		{
			ListNode temp  = head;
			boolean end = false;
			while(!end)
			{
				if (temp.val == target)
				{
					return true;
				}
				else if (temp.next == null)
				{
					end = true;
				}
				else
					temp = temp.next;
			}

		}
		return false;
	}
	
	
	int get(int index)
	{
		int count = 0;
		ListNode temp = head;
		boolean end = false;
		while(!end)
		{


			if (count == index)
			{
				return temp.val;
			}


			if (temp.next==null || index<0)
				throw new IndexOutOfBoundsException();
			temp = temp.next;
			count++;

		}
		return 0;
	}
	
	
	int indexOf(int target)
	{
		int count = 0;
		ListNode temp = head;
		boolean end = false;
		while(!end)
		{
			if (temp.val==target)
			{
				return count; 
			}
			if (temp.next==null)
			{
				count = 0;
				end = true;
			}
			temp = temp.next;
			count++;
		}

		return -1;
	}
	
	
	void set(int newval, int index)
	{
		int count = 0;
		ListNode temp = head;
		boolean end = false;
		while(!end)
		{


			if (count == index)
			{
				end = true;
				temp.val = newval ;
			}


			if (temp.next==null || index<0)
				throw new IndexOutOfBoundsException();
			temp = temp.next;
			count++;

		}

	}
	
	
	int size()
	{
		if (head==null)
		{
			return 0;
		}
		int count = 1;
		ListNode temp = head;
		boolean end = false;
		while(!end)
		{
			if (temp.next==null)
				return count;
			else
			{
				temp = temp.next;
				count++;
			}
		}

		return -1;
		//return 0;
	}
	
	
	int sizeRecursive(ListNode temp)
	{
		if (head==null)
			return 0;
		int count = 0;
		if (temp.next==null)
		{
			return 1;
		}
		else
		{
			count+=1+sizeRecursive(temp.next);
		}
		return count;
	}
	
	
	boolean isEmpty()
	{
		if (this.size()==0)
		{
			return true;
		}

		return false;
	}

	int remove(int index)
	{
		if (index>this.size())
			throw new ArrayIndexOutOfBoundsException();
		ListNode temp = head;
		int val = temp.val; 
		for (int i=0; i<index-1; i++)
		{
			temp = temp.next;
			//val = temp.val;
		}
		if (index == 0)
		{
			head = temp.next;
		}
		else
		{
			val = temp.next.val;
			temp.next = temp.next.next;
		}
		return val;
	}
	void add(int newval, int index)
	{
		ListNode temp = head;
		ListNode temp2 = temp.next;
		for(int i=0; i<index-1;i++)
		{
			temp = temp.next;
			temp2 = temp.next;
		}
		temp.next = new ListNode(newval);
		temp.next.next = temp2;
	}
	@Override
	public String toString()
	{
		if(head==null)
		{
			return "[]";
		}
		String res = "[";
		//System.out.println(head);
		ListNode temp = head;

		while(temp.next!=null)
		{

			res += temp.val+", ";
			temp = temp.next;

		}

		res+=temp.val+"]";
		return res;

	}
}
