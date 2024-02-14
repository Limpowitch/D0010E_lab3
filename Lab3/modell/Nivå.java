package Lab3.modell;

import java.util.ArrayList;
import java.util.Observable;

// TODO: Gör så att klassen Nivå ärver Observable i paketet java.util. 
public class Nivå extends Observable {
	// TODO: Lägg till tillståndsvariabler för att hålla reda på nivåns rum och
	// i vilket rum som användaren "är".
	private ArrayList<Rum> rooms;
	private Rum currentRoom;
	
	
	public Nivå(Rum startrum, ArrayList<Rum> rum) {
		// TODO: Kopiera in startrum och rum in i tillståndsvariablerna.
		this.rooms = new ArrayList<>(rum);
		this.currentRoom = startrum;

		// TODO: Kontrollera att startrum finns med i rum. Om inte, kasta
		// undantag med lämpligt felmeddelande.
		if (!(rooms.contains(currentRoom))) {
			throw new RuntimeException("The current room does not exist");
		}
		
		// TODO: Kontrollera att inga rum överlappar varandra. Om det ändå är
		// fallet, kasta undantag med lämpligt felmeddelande.
		for (int i = 0; i < rooms.size(); i++) {
	        Rum room1 = rooms.get(i);
	        for (int j = i + 1; j < rooms.size(); j++) {
	            Rum room2 = rooms.get(j);
	            int room1Right = room1.returnUpperX() + room1.returnWidth();
	            int room1Bottom = room1.returnUpperY() + room1.returnHeight();
	            int room2Right = room2.returnUpperX() + room2.returnWidth();
	            int room2Bottom = room2.returnUpperY() + room2.returnHeight();
	            if (!(room1Right <= room2.returnUpperX() || room2Right <= room1.returnUpperX() ||
	                    room1Bottom <= room2.returnUpperY() || room2Bottom <= room1.returnUpperY())) {
	                throw new RuntimeException("Room overlap detected.");
	            }
	        }
	    }    
		
		
	}
	public ArrayList<Rum> returnRooms() {
		// TODO: Skriv en instansmetod som returnerar alla rummen. Denna behöver
		// Målarduk för att veta vilka rum som finns på nivån och som ska ritas ut.
		return this.rooms;
	}
	public Rum returnUserRoom() {
		// TODO Skriv en instansmetod som returnerar en referens till det rum som
		// användaren "är i".
		return this.currentRoom;
	}
		

	// TODO: Skriv klar instansmetoden hoppaÅt nedan så att den ändrar det rum
	// som användaren "är i" om det är möjligt genom att följa en gång från
	// rummet och i riktning väderstreck.
	//
	// Om väderstreck inte är en riktning i vilken det finns en gång, så ändras
	// inte rummet användaren "är i" (och inte heller kastas undantag). (Denna
	// metod använder kontrolldelen av programmet för att begära ett hopp till
	// angränsande rum efter att användaren tryckt på en tangent.)
	public void hoppaÅt(Väderstreck väderstreck) {
		if (currentRoom.finnsUtgångÅt(väderstreck)) {
			Rum newRoom = currentRoom.gångenÅt(väderstreck).returnRoomTo();
			
			currentRoom = newRoom;
			
			setChanged();
			notifyObservers();
		}

	}
}