package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CClienteMapperTest {

    private CClienteMapper cClienteMapper;

    @BeforeEach
    public void setUp() {
        cClienteMapper = new CClienteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cClienteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cClienteMapper.fromId(null)).isNull();
    }
}
