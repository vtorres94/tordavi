package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CCorridaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CCorrida.class);
        CCorrida cCorrida1 = new CCorrida();
        cCorrida1.setId(1L);
        CCorrida cCorrida2 = new CCorrida();
        cCorrida2.setId(cCorrida1.getId());
        assertThat(cCorrida1).isEqualTo(cCorrida2);
        cCorrida2.setId(2L);
        assertThat(cCorrida1).isNotEqualTo(cCorrida2);
        cCorrida1.setId(null);
        assertThat(cCorrida1).isNotEqualTo(cCorrida2);
    }
}
