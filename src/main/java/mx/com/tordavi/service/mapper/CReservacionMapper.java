package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CReservacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CReservacion} and its DTO {@link CReservacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CPasajeroMapper.class, CCorridaMapper.class})
public interface CReservacionMapper extends EntityMapper<CReservacionDTO, CReservacion> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "pasajeroPrincipal.id", target = "pasajeroPrincipalId")
    @Mapping(source = "pasajeroPrincipal.nombreCompleto", target = "pasajeroPrincipalNombreCompleto")
    @Mapping(source = "corrida.id", target = "corridaId")
    @Mapping(source = "corrida.claveCorrida", target = "corridaClaveCorrida")
    CReservacionDTO toDto(CReservacion cReservacion);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "pasajeroPrincipalId", target = "pasajeroPrincipal")
    @Mapping(source = "corridaId", target = "corrida")
    CReservacion toEntity(CReservacionDTO cReservacionDTO);

    default CReservacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        CReservacion cReservacion = new CReservacion();
        cReservacion.setId(id);
        return cReservacion;
    }
}
