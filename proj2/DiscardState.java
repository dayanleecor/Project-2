package proj2;

public class DiscardState implements State{
	
	/**
	* Changes the state to discard state.
	*/
	public void doAction(Context context){
		context.setState(this);
	}
	
	/**
	* Takes a card and displays that it has been discarded.
	* Then, calls the doAction function with no parameters
	* to change state.
	*/
	public void doAction(Context context, Card card) {
		System.out.println("Discarded: " + card);
		doAction(context);
	}
	
	/**
	* Returns the string Discard State to compare it in the
	* action listener.
	*/
	public String toString(){
		return "Discard State";
	}
}