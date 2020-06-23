package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CPasajeroTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CPasajero.class);
        CPasajero cPasajero1 = new CPasajero();
        cPasajero1.setId(1L);
        CPasajero cPasajero2 = new CPasajero();
        cPasajero2.setId(cPasajero1.getId());
        assertThat(cPasajero1).isEqualTo(cPasajero2);
        cPasajero2.setId(2L);
        assertThat(cPasajero1).isNotEqualTo(cPasajero2);
        cPasajero1.setId(null);
        assertThat(cPasajero1).isNotEqualTo(cPasajero2);
    }
}
