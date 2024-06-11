package comandi;

import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	/** costruzione comando
	 * @param istruzione
	 * @return comando da eseguire
	 * @throws Exception 
	 * */
	@Override
	public Comando costruisciComando (String istruzione) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (istruzione);
		String nome = null;
		String parametro = null;
		Comando comando = null;

		//imposta nome comando
		if (scan.hasNext())
			nome = scan.next();

		//imposta nome comando
		if (scan.hasNext())
			parametro = scan.next();
		
		//elenco comandi
//		if (nome == null)
//			comando = new ComandoNonValido();
//		else if (nome.equals("vai"))
//			comando = new ComandoVai();
//		else if (nome.equals("prendi"))
//			comando = new ComandoPrendi();
//		else if (nome.equals("posa"))
//			comando = new ComandoPosa();
//		else if (nome.equals("aiuto"))
//			comando = new ComandoAiuto();
//		else if (nome.equals("fine"))
//			comando = new ComandoFine();
//		else if (nome.equals("guarda"))
//			comando = new ComandoGuarda();
//		else if (nome.equals ("borsa"))
//			comando = new ComandoBorsa();
//		else comando = new ComandoNonValido();
		
		try {
		StringBuilder nomeClasse
		= new StringBuilder("comandi.Comando");
		nomeClasse.append( Character.toUpperCase(nome.charAt(0)) );
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoV’
		nomeClasse.append( nome.substring(1) ) ;
		// es. nomeClasse: ‘it.uniroma3.diadia.comandi.ComandoVai’	
		comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
		//set parametro del comando
		comando.setParametro(parametro);

		//ritorna il comando
		return comando;
		} catch (ClassNotFoundException cnfe) {
			return new ComandoNonValido();
		}catch (NoClassDefFoundError ncdfe) {
			return new ComandoNonValido();
		}
	}

}
