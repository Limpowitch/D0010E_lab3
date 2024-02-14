package Lab3.kontroll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Lab3.modell.Nivå;
import Lab3.modell.Väderstreck;

public class Tangentbordslyssnare implements KeyListener {
	private Lab3.modell.Nivå enNivå;

	public Tangentbordslyssnare(Lab3.modell.Nivå enNivå) {
		this.enNivå = enNivå;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO: Skriv klar denna metod som automatiskt anropas så snart
		// användaren tryckt på en tangent på tangentbordet. Metoden ska
		// översätta tangenten till ett väderstreck och sen anropa hoppaÅt i
		// enNivå med detta väderstreck. Här ska
		//
		// w betyda "hoppa åt NORR",
		// d betyda "hoppa åt ÖSTER",
		// s betyda "hoppa åt SÖDER" och
		// a betyda "hoppa åt VÄSTER".
		int keyPressed = e.getKeyCode();

		switch(keyPressed) {
		case KeyEvent.VK_W:
			enNivå.hoppaÅt(Väderstreck.NORR);
			break;
		case KeyEvent.VK_A:
			enNivå.hoppaÅt(Väderstreck.VÄSTER);
			break;
		case KeyEvent.VK_S:
			enNivå.hoppaÅt(Väderstreck.SÖDER);
			break;
		case KeyEvent.VK_D:
			enNivå.hoppaÅt(Väderstreck.ÖSTER);
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyTyped finns i
		// gränssnittet KeyListener.
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyReleased finns is
		// gränssnittet KeyListener.
	}
}