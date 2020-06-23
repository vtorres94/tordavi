package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CReservacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CReservacion.class);
        CReservacion cReservacion1 = new CReservacion();
        cReservacion1.setId(1L);
        CReservacion cReservacion2 = new CReservacion();
        cReservacion2.setId(cReservacion1.getId());
        assertThat(cReservacion1).isEqualTo(cReservacion2);
        cReservacion2.setId(2L);
        assertThat(cReservacion1).isNotEqualTo(cReservacion2);
        cReservacion1.setId(null);
        assertThat(cReservacion1).isNotEqualTo(cReservacion2);
    }
}
