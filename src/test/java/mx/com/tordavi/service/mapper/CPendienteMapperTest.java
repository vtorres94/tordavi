package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CPendienteMapperTest {

    private CPendienteMapper cPendienteMapper;

    @BeforeEach
    public void setUp() {
        cPendienteMapper = new CPendienteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cPendienteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cPendienteMapper.fromId(null)).isNull();
    }
}
