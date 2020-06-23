package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDocumentoViajeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDocumentoViajeDTO.class);
        CDocumentoViajeDTO cDocumentoViajeDTO1 = new CDocumentoViajeDTO();
        cDocumentoViajeDTO1.setId(1L);
        CDocumentoViajeDTO cDocumentoViajeDTO2 = new CDocumentoViajeDTO();
        assertThat(cDocumentoViajeDTO1).isNotEqualTo(cDocumentoViajeDTO2);
        cDocumentoViajeDTO2.setId(cDocumentoViajeDTO1.getId());
        assertThat(cDocumentoViajeDTO1).isEqualTo(cDocumentoViajeDTO2);
        cDocumentoViajeDTO2.setId(2L);
        assertThat(cDocumentoViajeDTO1).isNotEqualTo(cDocumentoViajeDTO2);
        cDocumentoViajeDTO1.setId(null);
        assertThat(cDocumentoViajeDTO1).isNotEqualTo(cDocumentoViajeDTO2);
    }
}
