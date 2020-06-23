package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CDireccionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CDireccion} and its DTO {@link CDireccionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CPasajeroMapper.class})
public interface CDireccionMapper extends EntityMapper<CDireccionDTO, CDireccion> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "pasajero.id", target = "pasajeroId")
    @Mapping(source = "pasajero.nombreCompleto", target = "pasajeroNombreCompleto")
    CDireccionDTO toDto(CDireccion cDireccion);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "pasajeroId", target = "pasajero")
    CDireccion toEntity(CDireccionDTO cDireccionDTO);

    default CDireccion fromId(Long id) {
        if (id == null) {
            return null;
        }
        CDireccion cDireccion = new CDireccion();
        cDireccion.setId(id);
        return cDireccion;
    }
}
