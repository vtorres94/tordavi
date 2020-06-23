package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CDetalleReservacionMapperTest {

    private CDetalleReservacionMapper cDetalleReservacionMapper;

    @BeforeEach
    public void setUp() {
        cDetalleReservacionMapper = new CDetalleReservacionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cDetalleReservacionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cDetalleReservacionMapper.fromId(null)).isNull();
    }
}
