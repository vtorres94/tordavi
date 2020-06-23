package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CPendienteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CPendiente.class);
        CPendiente cPendiente1 = new CPendiente();
        cPendiente1.setId(1L);
        CPendiente cPendiente2 = new CPendiente();
        cPendiente2.setId(cPendiente1.getId());
        assertThat(cPendiente1).isEqualTo(cPendiente2);
        cPendiente2.setId(2L);
        assertThat(cPendiente1).isNotEqualTo(cPendiente2);
        cPendiente1.setId(null);
        assertThat(cPendiente1).isNotEqualTo(cPendiente2);
    }
}
