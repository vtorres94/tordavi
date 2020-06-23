package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CLugarParadaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CLugarParada.class);
        CLugarParada cLugarParada1 = new CLugarParada();
        cLugarParada1.setId(1L);
        CLugarParada cLugarParada2 = new CLugarParada();
        cLugarParada2.setId(cLugarParada1.getId());
        assertThat(cLugarParada1).isEqualTo(cLugarParada2);
        cLugarParada2.setId(2L);
        assertThat(cLugarParada1).isNotEqualTo(cLugarParada2);
        cLugarParada1.setId(null);
        assertThat(cLugarParada1).isNotEqualTo(cLugarParada2);
    }
}
