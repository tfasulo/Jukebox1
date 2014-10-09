package model;

public class CardReader{
	
	private boolean validated;

	public CardReader(){
		
		validated = false;
	}

	public /*boolean*/ void validate(String ID, String password, StudentCollection students){
		
		if (students.getStudent(ID)!=null){
			
			if (students.getStudent(ID).getPassword()==password){
			
				students.getStudent(ID).setLoggedIn(true);
				validated=true;
			}
			
			else{
				
				students.getStudent(ID).setLoggedIn(false);
				validated=false;
			}
		}
		
		else{
			
			students.getStudent(ID).setLoggedIn(false);
			validated=false;
		}
		
//		if(ID == "Ali"){
//			if(password == 1111){
//				validated = true;
//			}
//			else{
//				validated = false;
//			}
//		}
//		
//		else if(ID == "Chris"){
//			if(password == 2222){
//				validated = true;
//			}
//			else{
//				validated = false;
//			}
//		}
//		
//		else if(ID == "River"){
//			if(password == 3333){
//				validated = true;
//			}
//			else{
//				validated = false;
//			}
//		}
//		
//		else if(ID == "Ryan"){
//			if(password == 4444){
//				validated = true;
//			}
//			else{
//				validated = false;
//			}
//		}
//		else{
//			validated = false;
//		}
		
		//return validated;
	}
}
