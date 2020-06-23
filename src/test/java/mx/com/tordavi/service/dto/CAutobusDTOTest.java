package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CAutobusDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CAutobusDTO.class);
        CAutobusDTO cAutobusDTO1 = new CAutobusDTO();
        cAutobusDTO1.setId(1L);
        CAutobusDTO cAutobusDTO2 = new CAutobusDTO();
        assertThat(cAutobusDTO1).isNotEqualTo(cAutobusDTO2);
        cAutobusDTO2.setId(cAutobusDTO1.getId());
        assertThat(cAutobusDTO1).isEqualTo(cAutobusDTO2);
        cAutobusDTO2.setId(2L);
        assertThat(cAutobusDTO1).isNotEqualTo(cAutobusDTO2);
        cAutobusDTO1.setId(null);
        assertThat(cAutobusDTO1).isNotEqualTo(cAutobusDTO2);
    }
}
