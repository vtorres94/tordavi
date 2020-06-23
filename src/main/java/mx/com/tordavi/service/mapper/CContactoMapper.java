package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CContactoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CContacto} and its DTO {@link CContactoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CPasajeroMapper.class})
public interface CContactoMapper extends EntityMapper<CContactoDTO, CContacto> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "pasajero.id", target = "pasajeroId")
    @Mapping(source = "pasajero.nombreCompleto", target = "pasajeroNombreCompleto")
    CContactoDTO toDto(CContacto cContacto);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "pasajeroId", target = "pasajero")
    CContacto toEntity(CContactoDTO cContactoDTO);

    default CContacto fromId(Long id) {
        if (id == null) {
            return null;
        }
        CContacto cContacto = new CContacto();
        cContacto.setId(id);
        return cContacto;
    }
}
