package proj2;

public class Pile implements PileInterface {

	MyStack stack = new MyStack();
	
	/**
	* Pushes a card onto the stack.
	*/
	public void addCardStack(Card card) {
		stack.push(card);
	}
	
	/**
	* Pops a card from the stack.
	*/
	public Card removeCardStack() {
		return (Card)stack.pop();
	}
	
	/**
	* Returns the card at the top of the stack.
	*/
	public Card peekCard() {
		return (Card)stack.top();
	}
	
	/**
	* Returns true if the stack is empty, false otherwise.
	*/
	public boolean isStackEmpty() {
		return stack.isEmpty();
	}
	
}