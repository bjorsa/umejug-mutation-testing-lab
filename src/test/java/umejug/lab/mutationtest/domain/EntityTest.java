package umejug.lab.mutationtest.domain;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;

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
