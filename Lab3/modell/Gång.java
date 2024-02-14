package Lab3.modell;

public class Gång {

	// TODO: Lägg till tillståndsvariabler för att hålla parametrarna till
	// konstruktorn.
	private Rum roomFrom;
	private Väderstreck directionFrom;
	private Rum roomTo;
	private Väderstreck directionTo;
	

	public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till,
			Väderstreck riktningInITill) {
		// TODO: Tilldela tillståndsvariablerna parametervärdena.
		this.roomFrom = från;
		this.directionFrom = riktningUtUrFrån;
		this.roomTo = till;
		this.directionTo = riktningInITill;
	}

	// TODO: Lägg till instansmetoder som returnerar tillståndsvariablernas
	// värden.
	public Rum returnRoomFrom() {
		return this.roomFrom;
	}
	
	public Rum returnRoomTo() {
		return this.roomTo;
	}
	
	public Väderstreck returnDirectionFrom() {
		return this.directionFrom;
	}
	
	public Väderstreck returnDirectionTo() {
		return this.directionTo;
	}
	
	
}