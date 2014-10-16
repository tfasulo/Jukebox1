/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Card Reader validates the student ID and password, making sure that it matches, otherwise the user is not able to log in.
 */

package model;

public class CardReader{
	
	//boolean that is true if username and password match. Else returns false.
	
	private boolean validated;
	
	//constructor for cardReader() which automatically sets validated to false so a user cannot log in.

	public CardReader(){

		validated = false;
	}
	
	/*this method does the checking to make sure that the username matches the correct password.
	 *If it does not then it returns validated false, else it returns true and the user is allowed
	 *to log in.
	 */

	public boolean validate(String ID, String password, StudentCollection students){
		
		if (students.getStudent(ID)!=null){
			if (students.getStudent(ID).getPassword().equals(password)){
				
				students.getStudent(ID).setLoggedIn(true);
				validated=true;
			}
			else{
				
				students.getStudent(ID).setLoggedIn(false);
				validated=false;
			}
		}
		else{
			
			validated=false;
		}
		
		return validated;
	}
}
