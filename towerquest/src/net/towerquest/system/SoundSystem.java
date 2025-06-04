package net.towerquest.system;

import java.io.File;
import java.util.Collection;

public abstract class SoundSystem<SoundType extends Sound> {
	// TODO decide whether to use mp3, wav or ogg files, also maybe don't use file
	public abstract Sound loadSound(File file);
	/** Plays the sound once. Returns an ID number that can be used to stop or start the sound. */
	public abstract int playSound(Sound sound);
	/** Loops the sound {@code count} times. If {@code count} is 0, the sound will loop until it is stopped. */
	public abstract int loopSound(Sound sound, int count);
	/** Returns the sound file playing associated with this ID. */
	public abstract Sound getSound(int id);
	/** Returns the position in seconds of the sound associated with this ID. */
	public abstract float getPosition(int id);
	/** Returns whether the sound associated with this ID is currently playing. */
	public abstract boolean isPlaying(int id);
	/** Pauses the sound associated with the ID number provided. */
	public abstract void pauseSound(int id);
	/** Stops the sound associated with the ID number provided. This is different as it also frees the resource. */
	public abstract void stopSound(int id);
	/** Gets all sounds currently playing or paused. */
	public abstract Collection<Integer> getSounds();
	/** Gets all sounds of one file. */
	public abstract Collection<Integer> getSounds(Sound sound);
	/** Stops all sounds currently playing. */
	public void stopAll() {
		Collection<Integer> sounds = getSounds();
		sounds.forEach((i) -> stopSound(i));
	}
	/** Stops all sounds of one file. */
	public void stopAll(Sound sound) {
		Collection<Integer> sounds = getSounds(sound);
		sounds.forEach((i) -> stopSound(i));
	}
	
	/** Used when the program exits to free resources. */
	public abstract void exit();
}
