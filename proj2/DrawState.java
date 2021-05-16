package proj2;

public class DrawState implements State{

	/**
	* Changes the state to draw state.
	*/
	public void doAction(Context context){
		context.setState(this);
	}
	
	/**
	* Takes a card and displays that it has been added.
	* Then, calls the doAction function with no parameters
	* to change state.
	*/
	public void doAction(Context context, Card card) {
		System.out.println("Added: " + card);
		doAction(context);
	}
	
	/**
	* Returns the string Draw State to compare it in the
	* action listener.
	*/
	public String toString(){
		return "Draw State";
	}
}