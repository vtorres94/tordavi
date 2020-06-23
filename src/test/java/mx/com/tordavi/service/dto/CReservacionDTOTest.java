package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CReservacionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CReservacionDTO.class);
        CReservacionDTO cReservacionDTO1 = new CReservacionDTO();
        cReservacionDTO1.setId(1L);
        CReservacionDTO cReservacionDTO2 = new CReservacionDTO();
        assertThat(cReservacionDTO1).isNotEqualTo(cReservacionDTO2);
        cReservacionDTO2.setId(cReservacionDTO1.getId());
        assertThat(cReservacionDTO1).isEqualTo(cReservacionDTO2);
        cReservacionDTO2.setId(2L);
        assertThat(cReservacionDTO1).isNotEqualTo(cReservacionDTO2);
        cReservacionDTO1.setId(null);
        assertThat(cReservacionDTO1).isNotEqualTo(cReservacionDTO2);
    }
}
