package Protocol;

public class ApplicationProtocol {
	//Instance constant that initiates the server's response
	private static final int READY = 0;
    private static final int ROLLDICE = 1;
    private static final int BUYPROPERTY = 2;
    private static final int ENDTURN = 3;

    //Initially set state to READY
    private int state = READY;

    public String processInput(String theInput) {
    	//Declare a string theOutput
        String theOutput = null;
        
        //If the state is initially WAITING then it sends a response to the client
        if(state == READY) {
        	//Ask the user if they would like to start
            theOutput = "Would you like to start your turn? [Y/n]";
            //Update game state
            state = ROLLDICE;
        //If the state is ROLLDICE then the user rolls for how many spaces they move
        } else if(state == ROLLDICE) {
        	//If the user chooses to start their turn then roll dice
        	if(theInput.equalsIgnoreCase("y")) {
        		//Create a roll dice function
        		int diceNumber = 7;
        		//Move user diceNumber spaces and ask if they want to do the interaction with that block
        		theOutput = "You move " + diceNumber + " spaces forward.  Would you like to buy the property? [y/n]";
        		//Update game state
        		state = BUYPROPERTY;
        	} else {
        		//User must start turn lol
        		theOutput = "Too bad, it's still your turn.";
        	}
        //If the state is BUYPROPERTY then the user chooses if they want to buy the property or not (they actually have a choice here
        } else if(state == BUYPROPERTY) {
        	//If the user chose to buy a property change his/her data
        	if(theInput.equalsIgnoreCase("y")) {
        		//subtract player's money by the property value
        		theOutput = "You bought the property.";
        		//Update game state
        		state = ENDTURN;
        	//If the user chose to not buy a property just print out they didnt buy the property
        	} else if (theInput.equalsIgnoreCase("n")) {
        		theOutput = "You didn't buy the property";
        		//Update game state
        		state = ENDTURN;
        	} else {
        		//The user must interact with the block otherwise they are forced to go through that entire bologna again
        		theOutput = "Too bad, it's still your turn.";
        	}
        //If the state is ENDTURN then end the turn
        } else if(state == ENDTURN) {
        	//End turn
        	theOutput = "Turn ended.";       	
        } else {
        	//End turn
        	theOutput = "Turn ended.";
        }
        //Returns theOutput
        return theOutput;
    }
}
