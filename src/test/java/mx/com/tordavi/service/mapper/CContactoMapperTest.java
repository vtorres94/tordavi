package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CContactoMapperTest {

    private CContactoMapper cContactoMapper;

    @BeforeEach
    public void setUp() {
        cContactoMapper = new CContactoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cContactoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cContactoMapper.fromId(null)).isNull();
    }
}
