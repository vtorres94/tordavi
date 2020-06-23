package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CReservacionMapperTest {

    private CReservacionMapper cReservacionMapper;

    @BeforeEach
    public void setUp() {
        cReservacionMapper = new CReservacionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cReservacionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cReservacionMapper.fromId(null)).isNull();
    }
}
