package Lab3.modell;

public enum Väderstreck {
	NORR(0), ÖSTER(1), SÖDER(2), VÄSTER(3);
	
	private int directionIndex;
	
	
	// TODO: Lägg till en konstruktor som tar ett heltal och sparar det i 
	// en dold heltalsvariabel (en instansvariabel). Skriv en instansmetod
	// index() som returnerar heltalsvariabeln. Ändra de fyra väderstrecken 
	// så att 
	// 
	// NORR.index() returnerar 0, 
	// ÖSTER.index() returnerar 1,
	// SÖDER.index() returnerar 2 och 
	// VÄSTER.index() returnerar 3. 
	
	private Väderstreck(int direction) {
		this.directionIndex = direction;
	}
	
	public int index() {
		return this.directionIndex;
	}
}