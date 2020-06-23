package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CCorridaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CCorridaDTO.class);
        CCorridaDTO cCorridaDTO1 = new CCorridaDTO();
        cCorridaDTO1.setId(1L);
        CCorridaDTO cCorridaDTO2 = new CCorridaDTO();
        assertThat(cCorridaDTO1).isNotEqualTo(cCorridaDTO2);
        cCorridaDTO2.setId(cCorridaDTO1.getId());
        assertThat(cCorridaDTO1).isEqualTo(cCorridaDTO2);
        cCorridaDTO2.setId(2L);
        assertThat(cCorridaDTO1).isNotEqualTo(cCorridaDTO2);
        cCorridaDTO1.setId(null);
        assertThat(cCorridaDTO1).isNotEqualTo(cCorridaDTO2);
    }
}
