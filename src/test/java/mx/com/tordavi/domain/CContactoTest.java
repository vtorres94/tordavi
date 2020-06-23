package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CContactoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CContacto.class);
        CContacto cContacto1 = new CContacto();
        cContacto1.setId(1L);
        CContacto cContacto2 = new CContacto();
        cContacto2.setId(cContacto1.getId());
        assertThat(cContacto1).isEqualTo(cContacto2);
        cContacto2.setId(2L);
        assertThat(cContacto1).isNotEqualTo(cContacto2);
        cContacto1.setId(null);
        assertThat(cContacto1).isNotEqualTo(cContacto2);
    }
}
