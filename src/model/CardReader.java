/* Authors: Trevor Fasulo, Jason Tom
 * Professor: Rick Mercer
 * TA: Travis Stratton
 * Description: Card Reader validates the student ID and password.
 */
package model;

public class CardReader{
	
	private boolean validated;

	public CardReader(){

		validated = false;
	}

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
