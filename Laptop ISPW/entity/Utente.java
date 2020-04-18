
package entity;

public class Utente {
	
		private String userid;
		private String password;
		private String type;
		
		public Utente(String userid,String password,String Type) {
			this.userid=userid;
			this.password=password;
			this.type=Type;
		}
		public Utente(String userid,String password){
			this.userid=userid;
			this.password=password;}
		
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
}
