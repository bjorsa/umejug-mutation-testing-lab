package umejug.lab.mutationtest.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EntityTest {

    @Test
    public void shouldBeAbleToCreateAnEntityGivenAnIdentifier() {
        Identifier identifier = new Identifier();
        TestEntity entity = new TestEntity(identifier);
        assertThat(entity.getIdentifier()).isEqualTo(identifier);
    }

    @Test
    public void shouldBeConsideredEqualWhenComparedToItself() {
        TestEntity entity = new TestEntity(new Identifier());
        assertThat(entity).isEqualTo(entity);
    }

    @Test
    public void shouldBeEqualIfIdentifierIsEqual() {
        Identifier identifier = new Identifier();
        TestEntity entity = new TestEntity(identifier);
        assertThat(entity).isEqualTo(new TestEntity(identifier));
    }

    @Test
    public void shouldProduceTheSameHashCodeAsAnotherEntityWithTheSameIdentifier() {
        Identifier identifier = new Identifier();
        Identifier testIdentifier = new Identifier(identifier.getUuid());
        TestEntity entity = new TestEntity(identifier);
        TestEntity testEntity = new TestEntity(testIdentifier);
        assertThat(testEntity.hashCode()).isEqualTo(entity.hashCode());
    }

    @Test
    public void shouldThrowWhenCreatedWithNullIdentifier() {
        assertThatThrownBy(() -> new TestEntity(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("TestEntity identifier can't be null");
    }

    private static class TestEntity extends Entity {

        public TestEntity(Identifier identifier) {
            super(identifier);
        }
    }
}
