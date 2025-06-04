package net.towerquest.save;

/**
 * Used in an {@code ISerializable} to indicate that all of the class's field should be serialized.
 * Default Java modifiers like {@code transient} will still be ignored, use the annotations instead.
 * This is not inherited, so it only applies to the fields declared in the class that alo declares the annotation.
 * 
 * @see net.towerquest.save.Transient
 */

public @interface SerializeAll {

}
