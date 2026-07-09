package net.towerquest.LWJGLSystem;

import java.io.File;
import java.util.Collection;

import org.lwjgl.openal.AL10;

import net.towerquest.towerquest.system.SoundSystem;

public class OpenALSoundSystem implements SoundSystem<OpenALSoundBuffer> {

	@Override
	public OpenALSoundBuffer loadSound(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int playSound(OpenALSoundBuffer sound) {
		AL10.alSourcePlay(sound.sourceId);
		return 0;
	}

	@Override
	public int loopSound(OpenALSoundBuffer sound, int count) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OpenALSoundBuffer getSound(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getPosition(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isPlaying(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pauseSound(int id) {
		AL10.alSourcePause(id);
	}

	@Override
	public void stopSound(int id) {
		AL10.alSourceStop(id);
	}

	@Override
	public Collection<Integer> getSounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Integer> getSounds(OpenALSoundBuffer sound) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub
	}

	
}
