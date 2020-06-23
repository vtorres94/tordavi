package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDetalleReservacionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDetalleReservacionDTO.class);
        CDetalleReservacionDTO cDetalleReservacionDTO1 = new CDetalleReservacionDTO();
        cDetalleReservacionDTO1.setId(1L);
        CDetalleReservacionDTO cDetalleReservacionDTO2 = new CDetalleReservacionDTO();
        assertThat(cDetalleReservacionDTO1).isNotEqualTo(cDetalleReservacionDTO2);
        cDetalleReservacionDTO2.setId(cDetalleReservacionDTO1.getId());
        assertThat(cDetalleReservacionDTO1).isEqualTo(cDetalleReservacionDTO2);
        cDetalleReservacionDTO2.setId(2L);
        assertThat(cDetalleReservacionDTO1).isNotEqualTo(cDetalleReservacionDTO2);
        cDetalleReservacionDTO1.setId(null);
        assertThat(cDetalleReservacionDTO1).isNotEqualTo(cDetalleReservacionDTO2);
    }
}
