package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CPasajeroDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CPasajeroDTO.class);
        CPasajeroDTO cPasajeroDTO1 = new CPasajeroDTO();
        cPasajeroDTO1.setId(1L);
        CPasajeroDTO cPasajeroDTO2 = new CPasajeroDTO();
        assertThat(cPasajeroDTO1).isNotEqualTo(cPasajeroDTO2);
        cPasajeroDTO2.setId(cPasajeroDTO1.getId());
        assertThat(cPasajeroDTO1).isEqualTo(cPasajeroDTO2);
        cPasajeroDTO2.setId(2L);
        assertThat(cPasajeroDTO1).isNotEqualTo(cPasajeroDTO2);
        cPasajeroDTO1.setId(null);
        assertThat(cPasajeroDTO1).isNotEqualTo(cPasajeroDTO2);
    }
}
