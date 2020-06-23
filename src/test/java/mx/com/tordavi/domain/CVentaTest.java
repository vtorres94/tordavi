package mx.com.tordavi.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import mx.com.tordavi.web.rest.TestUtil;

public class CVentaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CVenta.class);
        CVenta cVenta1 = new CVenta();
        cVenta1.setId(1L);
        CVenta cVenta2 = new CVenta();
        cVenta2.setId(cVenta1.getId());
        assertThat(cVenta1).isEqualTo(cVenta2);
        cVenta2.setId(2L);
        assertThat(cVenta1).isNotEqualTo(cVenta2);
        cVenta1.setId(null);
        assertThat(cVenta1).isNotEqualTo(cVenta2);
    }
}
