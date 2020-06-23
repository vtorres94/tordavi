package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CAutobusMapperTest {

    private CAutobusMapper cAutobusMapper;

    @BeforeEach
    public void setUp() {
        cAutobusMapper = new CAutobusMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cAutobusMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cAutobusMapper.fromId(null)).isNull();
    }
}
