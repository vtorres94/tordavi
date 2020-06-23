package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CDocumentoViajeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CDocumentoViaje} and its DTO {@link CDocumentoViajeDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CPasajeroMapper.class})
public interface CDocumentoViajeMapper extends EntityMapper<CDocumentoViajeDTO, CDocumentoViaje> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "pasajero.id", target = "pasajeroId")
    @Mapping(source = "pasajero.nombreCompleto", target = "pasajeroNombreCompleto")
    CDocumentoViajeDTO toDto(CDocumentoViaje cDocumentoViaje);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "pasajeroId", target = "pasajero")
    CDocumentoViaje toEntity(CDocumentoViajeDTO cDocumentoViajeDTO);

    default CDocumentoViaje fromId(Long id) {
        if (id == null) {
            return null;
        }
        CDocumentoViaje cDocumentoViaje = new CDocumentoViaje();
        cDocumentoViaje.setId(id);
        return cDocumentoViaje;
    }
}
