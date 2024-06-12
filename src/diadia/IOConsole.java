package diadia;
import java.util.Scanner;

/*
 * Classe Console
 * centralizza l'input e l'output
 * 
 * */

public class IOConsole implements IO{
	
	public void mostraMessaggioNoCapo(String msg) {
		System.out.print(msg);
	}
	
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
//		scannerDiLinee.close();
		return riga;
	}
}