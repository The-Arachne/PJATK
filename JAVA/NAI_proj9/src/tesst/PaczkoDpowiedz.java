package tesst;

public class PaczkoDpowiedz {

	char character;
	String lrCombination;
	
	public PaczkoDpowiedz(char charAt, String fix) {
		character=charAt;
		lrCombination=fix;
	}
	
	@Override
	public String toString() {
		return "[ "+character+" ]: "+lrCombination;
	}
}
