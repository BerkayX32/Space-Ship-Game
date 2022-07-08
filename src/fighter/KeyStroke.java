package fighter;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyStroke implements KeyListener {
	
	public boolean up;
	public boolean down;
	public boolean left;
	public boolean right;
	public boolean fire;
	
	private boolean[] keys;
	
	public KeyStroke() {
		keys = new boolean[256];
	}
	
	//define keystroke buttons
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left= keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		fire = keys[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

}
