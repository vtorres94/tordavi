package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CAutobusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CAutobus} and its DTO {@link CAutobusDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class})
public interface CAutobusMapper extends EntityMapper<CAutobusDTO, CAutobus> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    CAutobusDTO toDto(CAutobus cAutobus);

    @Mapping(source = "clienteId", target = "cliente")
    CAutobus toEntity(CAutobusDTO cAutobusDTO);

    default CAutobus fromId(Long id) {
        if (id == null) {
            return null;
        }
        CAutobus cAutobus = new CAutobus();
        cAutobus.setId(id);
        return cAutobus;
    }
}
