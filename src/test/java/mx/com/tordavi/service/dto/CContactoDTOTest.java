package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CContactoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CContactoDTO.class);
        CContactoDTO cContactoDTO1 = new CContactoDTO();
        cContactoDTO1.setId(1L);
        CContactoDTO cContactoDTO2 = new CContactoDTO();
        assertThat(cContactoDTO1).isNotEqualTo(cContactoDTO2);
        cContactoDTO2.setId(cContactoDTO1.getId());
        assertThat(cContactoDTO1).isEqualTo(cContactoDTO2);
        cContactoDTO2.setId(2L);
        assertThat(cContactoDTO1).isNotEqualTo(cContactoDTO2);
        cContactoDTO1.setId(null);
        assertThat(cContactoDTO1).isNotEqualTo(cContactoDTO2);
    }
}
