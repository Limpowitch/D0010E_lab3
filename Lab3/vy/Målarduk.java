package Lab3.vy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Lab3.GlobalaKonstanter;
import Lab3.modell.Gång;
import Lab3.modell.Nivå;
import Lab3.modell.Rum;
import Lab3.modell.Väderstreck;
import Lab3.verktyg.Grafik;
import Lab3.verktyg.Punkt;

// TODO: Ändra nästa rad så att en Målarduk "är-en" JPanel. 
public class Målarduk extends JPanel{

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;
		// TODO: Sätt bakgrundsfärgen på this till MARKFÄRG.
		// TODO: Anropa metoden setFocusable på this och med argumentet true.
		// Detta behövs för att lyssnaren i programmet ska reagera.
		this.setBackground(GlobalaKonstanter.MARKFÄRG);
		this.setFocusable(true);
	}

	// TODO: Lätt till @Override på metoden nedan.
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Lägg till ett anrop till paintComponent i omedelbara
		// överklassen (JPanel). Skicka med g som argument.
		super.paintComponent(g);

		// TODO: Lägg till kod som ritar ut en grafisk vy av enNivå.
		//
		// För att underlätta finns hjälpmetoder som ska skrivas klara. Studera
		// noga bilderna i labbinstruktionen för att få fram formlerna för
		// bas- och pivotpunkternas koordinater. Använd ritmetoderna i klassen
		// Lab3.verktyg.Grafik. Anropa hjälpmetoderna från paintComponent.
		
		Graphics2D graphics = (Graphics2D) g;
		ArrayList<Rum> returnRooms = enNivå.returnRooms();
		
		for (int i = 0; i < returnRooms.size(); i++) {
			ritaRum(graphics, returnRooms.get(i));
			ritaGångarFrånRum(graphics, returnRooms.get(i));
		}
		
		ritaMarkörFörVarAnvändarenÄr(g);
	}

	private void ritaRum(Graphics g, Rum ettRum) {
	    g.setColor(GlobalaKonstanter.VÄGGFÄRG);
	    g.fillRect(ettRum.returnUpperX(), ettRum.returnUpperY(), ettRum.returnWidth(), ettRum.returnHeight());
	    g.setColor(ettRum.returnColor());
	    g.fillRect(ettRum.returnUpperX() + GlobalaKonstanter.VÄGGTJOCKLEK,
	    		ettRum.returnUpperY() + GlobalaKonstanter.VÄGGTJOCKLEK,
	    		ettRum.returnWidth() - GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK,
	    		ettRum.returnHeight() - GlobalaKonstanter.DUBBEL_VÄGGTJOCKLEK);
	}

	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		Väderstreck[] directions = Väderstreck.values();
		for (int j = 0; j < directions.length; j++) {
		    Väderstreck i = directions[j];
		    if (ettRum.finnsUtgångÅt(i)) {
		        ritaGång(g, ettRum.gångenÅt(i));
		    }
		}
	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		int posX;
		int posY;
		switch (enRiktning) {
		case NORR:
			posX=ettRum.returnUpperX() + (ettRum.returnWidth() / 2); //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + GlobalaKonstanter.VÄGGTJOCKLEK;
			return new Punkt(posX, posY);
		case SÖDER:
			posX=ettRum.returnUpperX() + (ettRum.returnWidth() / 2); //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + ettRum.returnHeight() - GlobalaKonstanter.VÄGGTJOCKLEK;
			return new Punkt(posX, posY);
		case VÄSTER:
			posX=ettRum.returnUpperX() + GlobalaKonstanter.VÄGGTJOCKLEK; //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + (ettRum.returnHeight() / 2);
			return new Punkt(posX, posY);
		case ÖSTER:
			posX=ettRum.returnUpperX() + ettRum.returnWidth() - GlobalaKonstanter.VÄGGTJOCKLEK; //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + (ettRum.returnHeight() / 2);
			return new Punkt(posX, posY);
		default:
			return null;
		}
	}

	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {
		int posX;
		int posY;
		switch (enRiktning) {
		case NORR:
			posX=ettRum.returnUpperX() + (ettRum.returnWidth() / 2); //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			return new Punkt(posX, posY);
		case SÖDER:
			posX=ettRum.returnUpperX() + (ettRum.returnWidth() / 2); //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + ettRum.returnHeight() - GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
			return new Punkt(posX, posY);
		case VÄSTER:
			posX=ettRum.returnUpperX() + GlobalaKonstanter.HALV_VÄGGTJOCKLEK; //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + (ettRum.returnHeight() / 2);
			return new Punkt(posX, posY);
		case ÖSTER:
			posX=ettRum.returnUpperX() + ettRum.returnWidth() - GlobalaKonstanter.HALV_VÄGGTJOCKLEK; //Vi får en punkt i mitten av X
			posY=ettRum.returnUpperY() + (ettRum.returnHeight() / 2);
			return new Punkt(posX, posY);
		default:
			return null;
		}
	}

	private void ritaGång(Graphics g, Gång enGång) {
		Punkt baspunkt1 = baspunkt(enGång.returnRoomFrom(), enGång.returnDirectionFrom());
		Punkt baspunkt2 = baspunkt(enGång.returnRoomTo(), enGång.returnDirectionTo());
		Punkt pivotpunkt1 = pivotpunkt(enGång.returnRoomFrom(), enGång.returnDirectionFrom());
		Punkt pivotpunkt2 = pivotpunkt(enGång.returnRoomTo(), enGång.returnDirectionTo());

		//Använd verktyg
		
		Grafik.drawThickLine(g, pivotpunkt1, baspunkt1, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.fillCircle(g, pivotpunkt1, GlobalaKonstanter.HALV_VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.drawThickLine(g, pivotpunkt1, pivotpunkt2, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.fillCircle(g, pivotpunkt1, GlobalaKonstanter.HALV_VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
		Grafik.drawThickLine(g, pivotpunkt2, baspunkt2, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);

	
	
	}
	

	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		Rum currentRoom = enNivå.returnUserRoom();
		
		int centreX = currentRoom.returnUpperX() + currentRoom.returnWidth() / 2;
		int centreY = currentRoom.returnUpperY() + currentRoom.returnHeight() / 2;
		Punkt centre = new Punkt(centreX, centreY);
		Grafik.fillCircle(g, centre, GlobalaKonstanter.ANVÄNDARRADIE, GlobalaKonstanter.ANVÄNDARFÄRG );
	    
	}
}