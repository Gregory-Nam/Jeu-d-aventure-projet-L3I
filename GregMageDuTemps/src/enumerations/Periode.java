package enumerations;

public enum Periode {
	PERIODE_1("Periode_1"), PERIODE_2("Periode_2"), PERIODE_3("Periode_3"), PERIODE_OBJECTIF("Periode_objectif");
	
	private final String periode;
	private static final Periode[] periodes = values();
	private Periode(String s) {
		this.periode = s;
	}
	
	public Periode precente() {
		return ordinal() != 0 ? periodes[ordinal() - 1] : periodes[0];
	}
	
	public Periode suivante() {
		return ordinal() != periodes.length ? periodes[ordinal() + 1] : periodes[ordinal()];
	}
	
	@Override
	public String toString() {
		return periode;
	}
	
}
