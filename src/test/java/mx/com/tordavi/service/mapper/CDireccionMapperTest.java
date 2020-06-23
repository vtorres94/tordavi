package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CDireccionMapperTest {

    private CDireccionMapper cDireccionMapper;

    @BeforeEach
    public void setUp() {
        cDireccionMapper = new CDireccionMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cDireccionMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cDireccionMapper.fromId(null)).isNull();
    }
}
