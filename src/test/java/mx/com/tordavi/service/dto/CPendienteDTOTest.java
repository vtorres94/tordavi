package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CPendienteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CPendienteDTO.class);
        CPendienteDTO cPendienteDTO1 = new CPendienteDTO();
        cPendienteDTO1.setId(1L);
        CPendienteDTO cPendienteDTO2 = new CPendienteDTO();
        assertThat(cPendienteDTO1).isNotEqualTo(cPendienteDTO2);
        cPendienteDTO2.setId(cPendienteDTO1.getId());
        assertThat(cPendienteDTO1).isEqualTo(cPendienteDTO2);
        cPendienteDTO2.setId(2L);
        assertThat(cPendienteDTO1).isNotEqualTo(cPendienteDTO2);
        cPendienteDTO1.setId(null);
        assertThat(cPendienteDTO1).isNotEqualTo(cPendienteDTO2);
    }
}
