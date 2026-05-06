
package net.towerquest.LWJGLSystem;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import org.lwjgl.input.Keyboard;

import net.towerquest.system.KeyboardEventHandler;
import net.towerquest.util.BiHashMap;
import net.towerquest.util.BiMap;

public class LWJGLKeyboard implements KeyboardEventHandler {
	private Consumer<KeyCode> onKeyUp = null, onKeyDown = null;
	private Consumer<Character> onKeyTyped = null;
	private Set<KeyCode> changedKeys = new HashSet<KeyCode>();
	BiMap<Integer, KeyCode> keyCodes = new BiHashMap<Integer, KeyCode>();
	
	public LWJGLKeyboard() {
		
		keyCodes.put(Keyboard.KEY_0, KeyCode.KEY_0);
		keyCodes.put(Keyboard.KEY_1, KeyCode.KEY_1);
		keyCodes.put(Keyboard.KEY_2, KeyCode.KEY_2);
		keyCodes.put(Keyboard.KEY_3, KeyCode.KEY_3);
		keyCodes.put(Keyboard.KEY_4, KeyCode.KEY_4);
		keyCodes.put(Keyboard.KEY_5, KeyCode.KEY_5);
		keyCodes.put(Keyboard.KEY_6, KeyCode.KEY_6);
		keyCodes.put(Keyboard.KEY_7, KeyCode.KEY_7);
		keyCodes.put(Keyboard.KEY_8, KeyCode.KEY_8);
		keyCodes.put(Keyboard.KEY_9, KeyCode.KEY_9);

		
		keyCodes.put(Keyboard.KEY_A, KeyCode.KEY_A);
		keyCodes.put(Keyboard.KEY_B, KeyCode.KEY_B);
		keyCodes.put(Keyboard.KEY_C, KeyCode.KEY_C);
		keyCodes.put(Keyboard.KEY_D, KeyCode.KEY_D);
		keyCodes.put(Keyboard.KEY_E, KeyCode.KEY_E);
		keyCodes.put(Keyboard.KEY_F, KeyCode.KEY_F);
		keyCodes.put(Keyboard.KEY_G, KeyCode.KEY_G);
		keyCodes.put(Keyboard.KEY_H, KeyCode.KEY_H);
		keyCodes.put(Keyboard.KEY_I, KeyCode.KEY_I);
		keyCodes.put(Keyboard.KEY_J, KeyCode.KEY_J);
		keyCodes.put(Keyboard.KEY_K, KeyCode.KEY_K);
		keyCodes.put(Keyboard.KEY_L, KeyCode.KEY_L);
		keyCodes.put(Keyboard.KEY_M, KeyCode.KEY_M);
		keyCodes.put(Keyboard.KEY_N, KeyCode.KEY_N);
		keyCodes.put(Keyboard.KEY_O, KeyCode.KEY_O);
		keyCodes.put(Keyboard.KEY_P, KeyCode.KEY_P);
		keyCodes.put(Keyboard.KEY_Q, KeyCode.KEY_Q);
		keyCodes.put(Keyboard.KEY_R, KeyCode.KEY_R);
		keyCodes.put(Keyboard.KEY_S, KeyCode.KEY_S);
		keyCodes.put(Keyboard.KEY_T, KeyCode.KEY_T);
		keyCodes.put(Keyboard.KEY_U, KeyCode.KEY_U);
		keyCodes.put(Keyboard.KEY_V, KeyCode.KEY_V);
		keyCodes.put(Keyboard.KEY_W, KeyCode.KEY_W);
		keyCodes.put(Keyboard.KEY_X, KeyCode.KEY_X);
		keyCodes.put(Keyboard.KEY_Y, KeyCode.KEY_Y);
		keyCodes.put(Keyboard.KEY_Z, KeyCode.KEY_Z);
		

		keyCodes.put(Keyboard.KEY_LEFT, KeyCode.KEY_LEFT);
		keyCodes.put(Keyboard.KEY_RIGHT, KeyCode.KEY_RIGHT);
		keyCodes.put(Keyboard.KEY_UP, KeyCode.KEY_UP);
		keyCodes.put(Keyboard.KEY_DOWN, KeyCode.KEY_DOWN);
		
		keyCodes.put(Keyboard.KEY_LSHIFT, KeyCode.KEY_LSHIFT);
		keyCodes.put(Keyboard.KEY_RSHIFT, KeyCode.KEY_RSHIFT);
		keyCodes.put(Keyboard.KEY_LMENU, KeyCode.KEY_ALT);
		keyCodes.put(Keyboard.KEY_RMENU, KeyCode.KEY_ALT);
		
	}
	
	@Override
	public void update() {
		changedKeys.clear();
		Keyboard.poll();
		while(Keyboard.next()) {
			int eventKey = Keyboard.getEventKey();
			KeyCode keyCode = keyCodes.get(eventKey);
			changedKeys.add(keyCode);
			if(Keyboard.getEventKeyState()) {
				if(onKeyDown != null)
					onKeyDown.accept(keyCode);
				if(onKeyTyped != null)
					onKeyTyped.accept(Keyboard.getEventCharacter());
			} else {
				if(onKeyUp != null)
					onKeyUp.accept(keyCode);
			}
		}
	}

	@Override
	public void onKeyPressed(Consumer<KeyCode> fn) {
		onKeyDown = fn;
	}

	@Override
	public void onKeyReleased(Consumer<KeyCode> fn) {
		onKeyUp = fn;
	}

	@Override
	public void onKeyTyped(Consumer<Character> fn) {
		onKeyTyped = fn;
	}

	@Override
	public boolean isKeyDown(KeyCode key) {
		for(int tk : keyCodes.getAllReverse(key))
			if (Keyboard.isKeyDown(tk))
				return true;
		return false;
	}

	@Override
	public Set<KeyCode> getChangedKeys() {
		return changedKeys;
	}

}
