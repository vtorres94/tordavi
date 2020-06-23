package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDetalleReservacionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDetalleReservacion.class);
        CDetalleReservacion cDetalleReservacion1 = new CDetalleReservacion();
        cDetalleReservacion1.setId(1L);
        CDetalleReservacion cDetalleReservacion2 = new CDetalleReservacion();
        cDetalleReservacion2.setId(cDetalleReservacion1.getId());
        assertThat(cDetalleReservacion1).isEqualTo(cDetalleReservacion2);
        cDetalleReservacion2.setId(2L);
        assertThat(cDetalleReservacion1).isNotEqualTo(cDetalleReservacion2);
        cDetalleReservacion1.setId(null);
        assertThat(cDetalleReservacion1).isNotEqualTo(cDetalleReservacion2);
    }
}
