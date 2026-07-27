package net.towerquest.engine.entity.components;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import net.towerquest.engine.entity.Component;

@Retention(RUNTIME)
@Target(TYPE)
public @interface DependsOn {
	Class<? extends Component>[] value();
}
