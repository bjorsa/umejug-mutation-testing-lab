package umejug.lab.mutationtest.domain;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class IdentifierTest {

    @Test
    public void shouldThrowWhenCreatedWithANullString() {
        assertThatThrownBy(() -> new Identifier((String) null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullUUID() {
        assertThatThrownBy(() -> new Identifier((UUID) null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("uuid can't be null.");
    }

    @Test
    public void shouldBeAbleToRecreateAnEqualIdentifierUsingAnUUID() {
        Identifier original = new Identifier();
        assertThat(new Identifier(original.getUuid())).isNotSameAs(original).isEqualTo(original);
    }

    @Test
    public void shouldBeAbleToRecreateAnEqualIdentifierUsingAnUUIDString() {
        Identifier original = new Identifier();
        assertThat(new Identifier(original.getUuid().toString())).isNotSameAs(original).isEqualTo(original);
    }
}
