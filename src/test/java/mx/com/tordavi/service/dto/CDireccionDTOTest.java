package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDireccionDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDireccionDTO.class);
        CDireccionDTO cDireccionDTO1 = new CDireccionDTO();
        cDireccionDTO1.setId(1L);
        CDireccionDTO cDireccionDTO2 = new CDireccionDTO();
        assertThat(cDireccionDTO1).isNotEqualTo(cDireccionDTO2);
        cDireccionDTO2.setId(cDireccionDTO1.getId());
        assertThat(cDireccionDTO1).isEqualTo(cDireccionDTO2);
        cDireccionDTO2.setId(2L);
        assertThat(cDireccionDTO1).isNotEqualTo(cDireccionDTO2);
        cDireccionDTO1.setId(null);
        assertThat(cDireccionDTO1).isNotEqualTo(cDireccionDTO2);
    }
}
