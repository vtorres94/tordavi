package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CPasajeroMapperTest {

    private CPasajeroMapper cPasajeroMapper;

    @BeforeEach
    public void setUp() {
        cPasajeroMapper = new CPasajeroMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cPasajeroMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cPasajeroMapper.fromId(null)).isNull();
    }
}
