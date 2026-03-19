package SwingSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import net.towerquest.system.KeyboardEventHandler;

public class KeyListenerEventHandler implements KeyboardEventHandler, KeyListener {
	private Consumer<KeyCode> onKeyPressed = null, onKeyReleased = null;
	private Consumer<Character> onKeyTyped = null;
	private Set<KeyCode> heldKeys = new HashSet<KeyCode>(),
			changedKeys = new HashSet<KeyCode>();
	
	public KeyCode translate(KeyEvent event) {
		// TODO implement
		return null;
	}
	
	// KeyListener methods
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		KeyCode translated = translate(arg0);
		heldKeys.add(translated);
		changedKeys.add(translated);
		onKeyPressed.accept(translated);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		KeyCode translated = translate(arg0);
		heldKeys.remove(translated);
		changedKeys.add(translated);
		onKeyReleased.accept(translated);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		onKeyTyped.accept(arg0.getKeyChar());
	}
	
	// KeyEventHandler methods

	@Override
	public void update() {
		// TODO Auto-generated method stub
		changedKeys.clear();
	}

	@Override
	public void onKeyPressed(Consumer<KeyCode> fn) {
		onKeyPressed = fn;
	}

	@Override
	public void onKeyReleased(Consumer<KeyCode> fn) {
		onKeyReleased = fn;
	}

	@Override
	public void onKeyTyped(Consumer<Character> fn) {
		onKeyTyped = fn;
	}

	@Override
	public boolean isKeyDown(KeyCode key) {
		return heldKeys.contains(key);
	}

	@Override
	public Set<KeyCode> getChangedKeys() {
		return new HashSet<KeyCode>(changedKeys);
	}

}
