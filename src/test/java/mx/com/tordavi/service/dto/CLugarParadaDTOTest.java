package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CLugarParadaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CLugarParadaDTO.class);
        CLugarParadaDTO cLugarParadaDTO1 = new CLugarParadaDTO();
        cLugarParadaDTO1.setId(1L);
        CLugarParadaDTO cLugarParadaDTO2 = new CLugarParadaDTO();
        assertThat(cLugarParadaDTO1).isNotEqualTo(cLugarParadaDTO2);
        cLugarParadaDTO2.setId(cLugarParadaDTO1.getId());
        assertThat(cLugarParadaDTO1).isEqualTo(cLugarParadaDTO2);
        cLugarParadaDTO2.setId(2L);
        assertThat(cLugarParadaDTO1).isNotEqualTo(cLugarParadaDTO2);
        cLugarParadaDTO1.setId(null);
        assertThat(cLugarParadaDTO1).isNotEqualTo(cLugarParadaDTO2);
    }
}
