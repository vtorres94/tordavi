package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CDireccionTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CDireccion.class);
        CDireccion cDireccion1 = new CDireccion();
        cDireccion1.setId(1L);
        CDireccion cDireccion2 = new CDireccion();
        cDireccion2.setId(cDireccion1.getId());
        assertThat(cDireccion1).isEqualTo(cDireccion2);
        cDireccion2.setId(2L);
        assertThat(cDireccion1).isNotEqualTo(cDireccion2);
        cDireccion1.setId(null);
        assertThat(cDireccion1).isNotEqualTo(cDireccion2);
    }
}
