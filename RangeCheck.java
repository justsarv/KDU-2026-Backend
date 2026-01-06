

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;

/**
 * Constraint annotation + validation system (integrated)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeCheck {

    int min();
    int max();

    /**
     * Validation engine
     */
    class Validator {

        public static void validate(Object config) {
            Class<?> clazz = config.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(RangeCheck.class)) {
                    field.setAccessible(true);
                    RangeCheck range = field.getAnnotation(RangeCheck.class);

                    try {
                        int value = field.getInt(config);

                        if (value < range.min() || value > range.max()) {
                            throw new ConfigValidationException(
                                "Invalid value for " + field.getName() +
                                ": " + value +
                                " (allowed range: " +
                                range.min() + " - " + range.max() + ")"
                            );
                        }

                        SystemConfig.logSuccess(
                            field.getName() + " validated successfully"
                        );

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * Custom unchecked exception
     */
    class ConfigValidationException extends RuntimeException {
        public ConfigValidationException(String message) {
            super(message);
        }
    }
}
