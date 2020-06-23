package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CLugarParadaMapperTest {

    private CLugarParadaMapper cLugarParadaMapper;

    @BeforeEach
    public void setUp() {
        cLugarParadaMapper = new CLugarParadaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cLugarParadaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cLugarParadaMapper.fromId(null)).isNull();
    }
}
