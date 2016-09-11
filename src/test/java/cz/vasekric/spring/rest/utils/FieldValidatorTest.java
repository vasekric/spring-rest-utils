package cz.vasekric.spring.rest.utils;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;


/**
 * @author Richard Vašek.
 */
public class FieldValidatorTest {

    private FieldValidator fieldValidator;

    @Before
    public void setUp() throws Exception {
        this.fieldValidator = new FieldValidator();
    }


    @Test
    public void validateExistingFields() throws Exception {
        val fieldNames = Arrays.asList("baz", "fooId");

        this.fieldValidator.validate(Foo.class, fieldNames);
    }

    @Test
    public void validateOneExistingPrivateField() throws Exception {
        val fieldNames = Arrays.asList("baz");

        this.fieldValidator.validate(Foo.class, fieldNames);
    }

    @Test
    public void validateOneExistingPublicField() throws Exception {
        val fieldNames = Arrays.asList("fooId");

        this.fieldValidator.validate(Foo.class, fieldNames);
    }

    @Test(expected = ValidationException.class)
    public void validateNotExistingField() throws Exception {
        val fieldNames = Arrays.asList("baz", "doesNotExists");

        this.fieldValidator.validate(Foo.class, fieldNames);
    }


    private static class Foo {
        private String baz;
        public String fooId;
    }

}