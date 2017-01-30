package umejug.lab.mutationtest.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValueObjectTest {

    @Test
    public void shouldConsiderTheSameObjectAsEqual() {
        TestValueObject object = new TestValueObject("a", 1, 0, "b");
        assertThat(object).isEqualTo(object);
    }

    @Test
    public void shouldConsiderNullAsNotEqual() {
        TestValueObject object = new TestValueObject("a", 1, 0, "b");
        assertThat(object).isNotEqualTo(null);
    }

    @Test
    public void shouldConsiderValueObjectsOfTheSameClassWithTheSameFieldValuesAsEqual() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 0, "b");
        assertThat(object1).isEqualTo(object2);
    }

    @Test
    public void shouldIgnoreTransientFieldsWhenComparing() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 0, "c");
        assertThat(object1).isEqualTo(object2);
    }

    @Test
    public void shouldConsiderValueObjectsOfTheSameClassNonEqualWhenTheyHoldDifferentFieldValues() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 1, "b");
        assertThat(object1).isNotEqualTo(object2);
    }

    @Test
    public void shouldConsiderNullFieldValuesDifferentFromNonNullFieldValues() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject(null, 1, 0, "b");
        assertThat(object1).isNotEqualTo(object2);
    }

    @Test
    public void shouldConsiderClassesFromTheSameHierarchyWithTheSameFieldValuesAsEqual() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObjectSubSameFields object2 = new TestValueObjectSubSameFields("a", 1, 0, "b");
        assertThat(object1).isEqualTo(object2);
    }

    @Test
    public void shouldConsiderClassesFromTheSameHierarchyWithDifferingFieldsAsNotEqual() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObjectSubAdditionalField object2 = new TestValueObjectSubAdditionalField("a", 1, 0, "b");
        assertThat(object1).isNotEqualTo(object2);
    }

    @Test
    public void shouldProduceTheSameHashCodeForObjectsOfTheSameClassWithSameFieldValues() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 0, "b");
        assertThat(object1.hashCode()).isEqualTo(object2.hashCode());
    }

    @Test
    public void shouldIgnoreTransientFieldsWhenProducingHashCode() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 0, "c");
        assertThat(object1.hashCode()).isEqualTo(object2.hashCode());
    }

    @Test
    public void shouldProduceDifferentHashCodesForDifferentFieldValues() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject("a", 1, 1, "b");
        assertThat(object1.hashCode()).isNotEqualTo(object2.hashCode());
    }

    @Test
    public void shouldProduceDifferentHashCodesForNullFieldValueFields() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObject object2 = new TestValueObject(null, 1, 0, "b");
        assertThat(object1.hashCode()).isNotEqualTo(object2.hashCode());
    }

    @Test
    public void shouldProduceTheSameHashCodeForClassesFromTheSameHierarchyWithTheSameFieldValues() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObjectSubSameFields object2 = new TestValueObjectSubSameFields("a", 1, 0, "b");
        assertThat(object1.hashCode()).isEqualTo(object2.hashCode());
    }

    @Test
    public void shouldProduceDifferentHashCodesForClassesFromTheSameHierarchyWithDifferingFields() {
        TestValueObject object1 = new TestValueObject("a", 1, 0, "b");
        TestValueObjectSubAdditionalField object2 = new TestValueObjectSubAdditionalField("a", 1, 0, "b");
        assertThat(object1.hashCode()).isNotEqualTo(object2.hashCode());
    }

    @SuppressWarnings("unused")
    private static class TestValueObject extends ValueObject {
        private final String string;
        private final Integer integer;
        private final int primitiveInt;

        private final transient String transientString;

        public TestValueObject(String string, Integer integer, int primitiveInt, String transientString) {
            this.string = string;
            this.integer = integer;
            this.primitiveInt = primitiveInt;
            this.transientString = transientString;
        }
    }

    private static class TestValueObjectSubSameFields extends TestValueObject {

        public TestValueObjectSubSameFields(String string, Integer integer, int primitiveInt, String transientString) {
            super(string, integer, primitiveInt, transientString);
        }
    }

    @SuppressWarnings("unused")
    private static class TestValueObjectSubAdditionalField extends TestValueObject {

        private String additional;

        public TestValueObjectSubAdditionalField(String string, Integer integer, int primitiveInt, String transientString) {
            super(string, integer, primitiveInt, transientString);
            additional = "c";
        }
    }
}
