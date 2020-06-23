package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CAutobusTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CAutobus.class);
        CAutobus cAutobus1 = new CAutobus();
        cAutobus1.setId(1L);
        CAutobus cAutobus2 = new CAutobus();
        cAutobus2.setId(cAutobus1.getId());
        assertThat(cAutobus1).isEqualTo(cAutobus2);
        cAutobus2.setId(2L);
        assertThat(cAutobus1).isNotEqualTo(cAutobus2);
        cAutobus1.setId(null);
        assertThat(cAutobus1).isNotEqualTo(cAutobus2);
    }
}
