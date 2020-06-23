package mx.com.tordavi.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CClienteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CClienteDTO.class);
        CClienteDTO cClienteDTO1 = new CClienteDTO();
        cClienteDTO1.setId(1L);
        CClienteDTO cClienteDTO2 = new CClienteDTO();
        assertThat(cClienteDTO1).isNotEqualTo(cClienteDTO2);
        cClienteDTO2.setId(cClienteDTO1.getId());
        assertThat(cClienteDTO1).isEqualTo(cClienteDTO2);
        cClienteDTO2.setId(2L);
        assertThat(cClienteDTO1).isNotEqualTo(cClienteDTO2);
        cClienteDTO1.setId(null);
        assertThat(cClienteDTO1).isNotEqualTo(cClienteDTO2);
    }
}
