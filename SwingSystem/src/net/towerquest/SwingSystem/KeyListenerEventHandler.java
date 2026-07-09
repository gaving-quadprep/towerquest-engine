package net.towerquest.SwingSystem;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import net.towerquest.towerquest.system.KeyboardEventHandler;
import net.towerquest.towerquest.system.KeyboardEventHandler.KeyCode;
import net.towerquest.towerquest.util.BiHashMap;
import net.towerquest.towerquest.util.BiMap;

public class KeyListenerEventHandler implements KeyboardEventHandler, KeyListener {
	private Consumer<KeyCode> onKeyPressed = null, onKeyReleased = null;
	private Consumer<Character> onKeyTyped = null;
	private Set<KeyCode> heldKeys = new HashSet<KeyCode>(),
			changedKeys = new HashSet<KeyCode>();
	BiMap<Integer, KeyCode> keyCodes = new BiHashMap<Integer, KeyCode>();
	
	KeyListenerEventHandler() {
		keyCodes.put(VK_A, KeyCode.KEY_A);
		keyCodes.put(VK_B, KeyCode.KEY_B);
		keyCodes.put(VK_C, KeyCode.KEY_C);
		keyCodes.put(VK_D, KeyCode.KEY_D);
		keyCodes.put(VK_E, KeyCode.KEY_E);
		
		// TODO finish

		keyCodes.put(VK_UP, KeyCode.KEY_UP);
		keyCodes.put(VK_DOWN, KeyCode.KEY_DOWN);
		keyCodes.put(VK_LEFT, KeyCode.KEY_LEFT);
		keyCodes.put(VK_RIGHT, KeyCode.KEY_RIGHT);
	}
	
	// KeyListener methods
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		KeyCode translated = keyCodes.get(arg0.getKeyCode());
		heldKeys.add(translated);
		changedKeys.add(translated);
		if(onKeyPressed != null)
			onKeyPressed.accept(translated);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		KeyCode translated = keyCodes.get(arg0.getKeyCode());
		heldKeys.remove(translated);
		changedKeys.add(translated);
		if(onKeyReleased != null)
			onKeyReleased.accept(translated);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(onKeyTyped != null)
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
