package Lab061;
import java.util.Scanner;
//import sql.impl.*;
//import sql.op.*;
import Lab06.data;

public class Sqlinjectionn {

	public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter UserID\n");
	int userId = scan.nextInt();
	System.out.println("Enter Password\n");
	String pswd = sc.nextLine();
	//System.out.println("Please Wait\n");
	data.getConnection(userId, pswd);
	scan.close();
	sc.close();
	}
	}
