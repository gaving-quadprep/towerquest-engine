package net.towerquest.towerquest.mod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import net.towerquest.towerquest.GameInstance;
import net.towerquest.towerquest.util.Logger;
import net.towerquest.towerquest.util.Logger.Level;

public class ModLoader {
	GameInstance game;
	List<String> directoriesToSearch = new ArrayList<String>();
	List<TowerQuestMod> mods = new ArrayList<TowerQuestMod>();
	public ModLoader(GameInstance game) {
		this.game = game;
		String[] dirs = System.getProperty("net.towerquest.moddirs", "")
				.split(File.pathSeparator);
		for (String str : dirs) {
			if (!str.equals(""))
				directoriesToSearch.add(str);
		}
	}
	public boolean isModCompatible(ModInfo info) {
		if (info.minMajorVersion > game.majorVersion || info.maxMajorVersion < game.majorVersion)
			return false;
		if (info.minMajorVersion == game.majorVersion)
			return game.minorVersion >= info.minMinorVersion;
		if (info.maxMajorVersion == game.majorVersion)
			return game.minorVersion <= info.maxMinorVersion;
		return true;
	}
	public void loadMod(File jarFile) {
		try {
			loadMod(jarFile.toURI().toURL());
		} catch (MalformedURLException e) {
			game.logger.logException(Level.ERROR, "Malformed mod URL:", e);
		}
	}
	public void loadMod(URL jarUrl) {
		try (URLClassLoader classLoader = new URLClassLoader(new URL[] {jarUrl}, 
				ModLoader.class.getClassLoader())) {
			Class<?> tqModClass = Class.forName("TQModEntry", true, classLoader);
			TowerQuestMod mod = (TowerQuestMod) tqModClass.getConstructor(GameInstance.class)
					.newInstance(game);
			ModInfo info = mod.getModInfo();
			if (!isModCompatible(info)) {
				throw new IncompatibleModException(info);
			}
			
			mod.init(game);
			mods.add(mod);
			
		} catch (IOException e) {
			game.logger.logException(Level.ERROR, e);
		} catch (ClassNotFoundException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Jar file %s does not contain mod information", jarUrl.getFile()), e);
		} catch (InstantiationException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Mod in jar file %s cannot be instantiated", jarUrl.getFile()), e);
		} catch (IllegalAccessException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Mod in jar file %s has a private constructor", jarUrl.getFile()), e);
		} catch (InvocationTargetException e) {
			game.logger.logException(Level.ERROR, "Exception in constructor: ", e.getCause());
		} catch (NoSuchMethodException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Mod in jar file %s does not have a constructor with the correct parameters", 
					jarUrl.getFile()), e);
		} catch (ClassCastException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Mod in jar file %s does not implement the TowerQuestMod interface", 
					jarUrl.getFile()), e);
		} catch (IncompatibleModException e) {
			game.logger.logException(Level.ERROR, e);
		} catch (Exception e) { // catch-all in case the mod returns an error while initializing
			game.logger.logException(Level.ERROR, e);
		}
	}
	public void loadMods(String directory) {
		Path path = Paths.get(directory);
		try (Stream<Path> stream = Files.walk(path)) {
			stream.filter(Files::isRegularFile)
            .filter(path2 -> path2.toString().endsWith(".jar"))
            .forEach(path2 -> {
            	// idk why the for loop outside can't catch the exception
				try {
					loadMod(path2.toUri().toURL());
				} catch (MalformedURLException e) {
					game.logger.logException(Level.ERROR, "Malformed mod URL:", e);
				}
			});
		} catch (IOException e) {
			game.logger.logException(Level.ERROR, String.format(
					"Couldn't search directory %s for jar files", directory), e);
		}
	}
}
