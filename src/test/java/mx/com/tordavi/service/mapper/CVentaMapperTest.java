package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CVentaMapperTest {

    private CVentaMapper cVentaMapper;

    @BeforeEach
    public void setUp() {
        cVentaMapper = new CVentaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cVentaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cVentaMapper.fromId(null)).isNull();
    }
}
