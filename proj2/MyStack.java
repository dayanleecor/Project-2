package proj2;

public class MyStack<E> {
	protected E stackArr[];
	final private int maxSize = 52;
	private int top;
	
	public MyStack() {
		top = 0;
		stackArr = (E[]) new Object[maxSize];
	}
	
	/**
	* Pushes a value onto the stack.
	*/
	public void push(E e) {
		if (top <= maxSize) {
			stackArr[top++] = e;
		}
	}
	
	/**
	* Pops a value from the stack.
	*/
	public E pop() {
		if (top == 0) {return null;}
		return stackArr[--top];
	}
	
	/**
	* Returns the value at the top of the stack.
	*/
	public E top() {
		if (top == 0) {return null;}
		return stackArr[top - 1];
	}
	
	/**
	* Returns true if the stack is empty, false otherwise.
	*/
	public boolean isEmpty() {
		return top == 0;
	}

}