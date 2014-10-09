package model;

public class CardReader{
	
	private boolean validated;

	public CardReader(){
		  
		validated = false;
	}

	public boolean validate(String ID, int password, StudentCollection students){
		
		if (students.get(ID)!=null){
			if (students.get(ID).getPassword()==password){
				validated=true;
			}
			else{
				validated=false;
			}
		}
		else{
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
		
		return validated;
	}
}
