package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDocumentoViajeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDocumentoViaje.class);
        CDocumentoViaje cDocumentoViaje1 = new CDocumentoViaje();
        cDocumentoViaje1.setId(1L);
        CDocumentoViaje cDocumentoViaje2 = new CDocumentoViaje();
        cDocumentoViaje2.setId(cDocumentoViaje1.getId());
        assertThat(cDocumentoViaje1).isEqualTo(cDocumentoViaje2);
        cDocumentoViaje2.setId(2L);
        assertThat(cDocumentoViaje1).isNotEqualTo(cDocumentoViaje2);
        cDocumentoViaje1.setId(null);
        assertThat(cDocumentoViaje1).isNotEqualTo(cDocumentoViaje2);
    }
}
