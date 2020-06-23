package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CPasajeroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CPasajero} and its DTO {@link CPasajeroDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class})
public interface CPasajeroMapper extends EntityMapper<CPasajeroDTO, CPasajero> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    CPasajeroDTO toDto(CPasajero cPasajero);

    @Mapping(source = "clienteId", target = "cliente")
    CPasajero toEntity(CPasajeroDTO cPasajeroDTO);

    default CPasajero fromId(Long id) {
        if (id == null) {
            return null;
        }
        CPasajero cPasajero = new CPasajero();
        cPasajero.setId(id);
        return cPasajero;
    }
}
