package net.towerquest.save;

/**
 * Just does what the java keyword does, but for {@code ISerializable} classes with the {@code SerializeAll} annotation.
 * If, when serializing, a field with both a {@code Transient} and {@code SerializeMe} annotation is found,
 * the system will automatically eject any optical drive bays attached to it.
 */
@interface Transient {

}
