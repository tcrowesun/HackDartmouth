import utils.*;



public class ClientDriver {

	public static void main(String[] args) {
		
		
		ClientComm comm = new ClientComm("10.31.213.163");
		
		comm.send("50 100");
		
	}

}
