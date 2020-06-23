package mx.com.tordavi.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CDocumentoViajeMapperTest {

    private CDocumentoViajeMapper cDocumentoViajeMapper;

    @BeforeEach
    public void setUp() {
        cDocumentoViajeMapper = new CDocumentoViajeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(cDocumentoViajeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(cDocumentoViajeMapper.fromId(null)).isNull();
    }
}
