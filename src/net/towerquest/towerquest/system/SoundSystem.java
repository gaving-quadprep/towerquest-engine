package net.towerquest.towerquest.system;

import java.io.File;
import java.util.Collection;

public interface SoundSystem<SoundType extends Sound> {
	// TODO decide whether to use mp3, wav or ogg files, also maybe don't use file
	public SoundType loadSound(File file);
	/** Plays the sound once. Returns an ID number that can be used to stop or start the sound. */
	public int playSound(SoundType sound);
	/** Loops the sound {@code count} times. If {@code count} is 0, the sound will loop until it is stopped. */
	public int loopSound(SoundType sound, int count);
	/** Returns the sound file playing associated with this ID. */
	public SoundType getSound(int id);
	/** Returns the position in seconds of the sound associated with this ID. */
	public float getPosition(int id);
	/** Returns whether the sound associated with this ID is currently playing. */
	public boolean isPlaying(int id);
	/** Pauses the sound associated with the ID number provided. */
	public void pauseSound(int id);
	/** Stops the sound associated with the ID number provided. This is different as it also frees the resource. */
	public void stopSound(int id);
	/** Gets all sounds currently playing or paused. */
	public Collection<Integer> getSounds();
	/** Gets all sounds of one file. */
	public Collection<Integer> getSounds(SoundType sound);
	/** Stops all sounds currently playing. */
	public default void stopAll() {
		Collection<Integer> sounds = getSounds();
		sounds.forEach((i) -> stopSound(i));
	}
	/** Stops all sounds of one file. */
	public default void stopAll(SoundType sound) {
		Collection<Integer> sounds = getSounds(sound);
		sounds.forEach((i) -> stopSound(i));
	}
	
	/** Used when the program exits to free resources. */
	public void exit();
}
