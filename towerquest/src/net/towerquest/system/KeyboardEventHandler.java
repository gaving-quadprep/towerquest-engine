package net.towerquest.system;

import java.util.Set;
import java.util.function.Consumer;

public interface KeyboardEventHandler {
	// TODO figure out how to handle different key ids without relying on massive switch statements
	public static enum KeyCode {
		NONE,
		
		KEY_LEFT,	KEY_RIGHT,	KEY_UP,		KEY_DOWN,
		KEY_ESC, 	KEY_TAB,
		KEY_PGUP,	KEY_PGDN,	KEY_HOME,	KEY_END,
		KEY_SPACE,
		KEY_BACKSPACE,	KEY_DELETE,

		// i decided i'm not going to do left/right alt/ctrl
		KEY_LSHIFT,	KEY_RSHIFT,	KEY_ALT,	KEY_CTRL,
		
		/* key_plus is only on numpad, use key_equals also or check for shift */
		KEY_MINUS,	KEY_PLUS,	KEY_EQUALS,
		
		KEY_BACKTICK,	KEY_PERIOD,	KEY_COMMA,	KEY_SEMICOLON,
		KEY_FORWARD_SLASH,	KEY_BACKSLASH,	
		KEY_LEFT_BRACKET, KEY_RIGHT_BRACKET,
		
		KEY_A,	KEY_B,	KEY_C,
		KEY_D,	KEY_E,	KEY_F,
		KEY_G,	KEY_H,	KEY_I,
		KEY_J,	KEY_K,	KEY_L,
		KEY_M,	KEY_N,	KEY_O,
		KEY_P,	KEY_Q,	KEY_R,
		KEY_S,	KEY_T,	KEY_U,
		KEY_V,	KEY_W,	KEY_X,
		KEY_Y,	KEY_Z,
		
		KEY_1,	KEY_2,	KEY_3,
		KEY_4,	KEY_5,	KEY_6,
		KEY_7,	KEY_8,	KEY_9,
		KEY_0,

		KEY_F1,		KEY_F2,		KEY_F3,
		KEY_F4,		KEY_F5,		KEY_F6,
		KEY_F7,		KEY_F8,		KEY_F9,
		KEY_F10,	KEY_F11,	KEY_F12,
	}
	
	/** should be called every frame, checks fo new key presses */
	public void update();
	/** only one function can be used at once, set to null to clear */
	public void onKeyPressed(Consumer<KeyCode> fn);
	public void onKeyReleased(Consumer<KeyCode> fn);
	/** used for text input because of keyboard layouts */
	public void onKeyTyped(Consumer<Character> fn);
	public boolean isKeyDown(KeyCode key);
	public Set<KeyCode> getChangedKeys();
	
}
