package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CVentaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CVentaDTO.class);
        CVentaDTO cVentaDTO1 = new CVentaDTO();
        cVentaDTO1.setId(1L);
        CVentaDTO cVentaDTO2 = new CVentaDTO();
        assertThat(cVentaDTO1).isNotEqualTo(cVentaDTO2);
        cVentaDTO2.setId(cVentaDTO1.getId());
        assertThat(cVentaDTO1).isEqualTo(cVentaDTO2);
        cVentaDTO2.setId(2L);
        assertThat(cVentaDTO1).isNotEqualTo(cVentaDTO2);
        cVentaDTO1.setId(null);
        assertThat(cVentaDTO1).isNotEqualTo(cVentaDTO2);
    }
}
