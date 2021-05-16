package proj2;

public interface PileInterface {

	/**
	* Pushes a card onto the stack.
	*/
	public void addCardStack(Card card);
	
	/**
	* Pops a card from the stack.
	*/
	public Card removeCardStack();
	
	/**
	* Returns the card at the top of the stack.
	*/
	public Card peekCard();
	
	/**
	* Returns true if the stack is empty, false otherwise.
	*/
	public boolean isStackEmpty();
}