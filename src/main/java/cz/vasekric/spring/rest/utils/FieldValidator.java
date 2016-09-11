package cz.vasekric.spring.rest.utils;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Richard Vašek.
 */
@Component
public class FieldValidator {

    /**
     *
     * @param o
     * @param fieldNames
     * @throws ValidationException if not valid
     */
    public void validate(@NotNull Class o, @NotNull List<String> fieldNames) {
        // How I fucking low lambdas and streams, but for performance reasons, I am not using them here.
        val publicFields = o.getFields();
        val privateFields = o.getDeclaredFields();
        val fields = this.concat(publicFields, privateFields);
        for(String fieldName: fieldNames) {
            for(int i = 0; i < fields.length; i++) {
                val field = fields[i];
                if(field.getName().equals(fieldName)) {
                    break;
                }

                // Last element, did not "break;" out so it was not found.
                if(i == fields.length-1) {
                    throw new ValidationException(fieldName + " is not valid field in " + o.getSimpleName());
                }
            }
        }
    }

    // http://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
    private Field[] concat(Field[] a, Field[] b) {
        int aLen = a.length;
        int bLen = b.length;
        Field[] c= new Field[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}
