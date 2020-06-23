package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CCorridaMapperTest {

    private CCorridaMapper cCorridaMapper;

    @BeforeEach
    public void setUp() {
        cCorridaMapper = new CCorridaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cCorridaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cCorridaMapper.fromId(null)).isNull();
    }
}
