package Lab3.modell;

import java.awt.Color;

import Lab3.GlobalaKonstanter;

public class Rum {

	// TODO: Lägg till tillståndsvariabler.
	private Color floorcolor;
	private int width;
	private int height;
	private int upperX;
	private int upperY;
	private Gång[] pathsOutOf;

	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		// TODO: Kopiera parametrarna in i tillståndsvariablerna. (övX,övY) är
		// koordinaten för rummets övre vänstra hörn och lagras lämpligen som en
		// instans av klassen Punkt i paketet verktyg.
		this.floorcolor = golvfärg;
		this.width = bredd;
		this.height = höjd;
		this.upperX = övX;
		this.upperY = övY;
		this.pathsOutOf = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];
	}

	// TODO: Skriv "getters", metoder som returnerar tillståndsvariablernas
	// värden.
	public Color returnColor(){
		return floorcolor;
	}
	
	public int returnWidth(){
		return width;
	}
	
	public int returnHeight(){
		return height;
	}
	
	public int returnUpperX(){
		return upperX;
	}
	
	public int returnUpperY(){
		return upperY;
	}
	

	// TODO: Skriv instansmetoden
	//
	// finnsUtgångÅt(Väderstreck väderstreck)
	// som ska kontrollera om det från ett rum finns en utgång åt visst
	// väderstreck.
	public boolean finnsUtgångÅt(Väderstreck väderstreck) {
	    return pathsOutOf[väderstreck.index()] != null;
	}
	
	

	// TODO: Skriv instansmetoden
	//
	// Gång gångenÅt(Väderstreck väderstreck) som
	// returnerar den gång som leder från ett rum i riktning väderstreck. Om
	// sådan gång saknas ska ett undantag kastas med lämpligt felmeddelande.
	public Gång gångenÅt(Väderstreck väderstreck) {
		if(!(finnsUtgångÅt(väderstreck))) {
			throw new RuntimeException("There's no exit in " + väderstreck);
		}
		return pathsOutOf[väderstreck.index()];
	}
	

	// TODO: Skrivklar metoden nedan som kopplar ihop två rum med en gång.

	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån,
			Rum till, Väderstreck riktningInITill) {
		if (från.finnsUtgångÅt(riktningUtUrFrån) || till.finnsUtgångÅt(riktningInITill)) {
			throw new RuntimeException("Det finns redan en gång");
		}
		
		Gång pathFrom = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		Gång pathTo = new Gång(till, riktningInITill, från, riktningUtUrFrån);
		
		från.pathsOutOf[riktningUtUrFrån.index()] = pathFrom;
		till.pathsOutOf[riktningInITill.index()] = pathTo;
		
	}
	
}