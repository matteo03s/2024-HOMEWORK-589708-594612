package ambienti;


public enum Direzione {
	nord, est, sud, ovest;
	
	public Direzione opposta () {
		if (this == Direzione.nord)
			return sud;
		else if (this == Direzione.sud)
			return nord;
		else if (this == Direzione.est)
			return ovest;
		else
			return est;
	}
}
