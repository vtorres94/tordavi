package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CDetalleReservacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CDetalleReservacion} and its DTO {@link CDetalleReservacionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CPasajeroMapper.class, CReservacionMapper.class})
public interface CDetalleReservacionMapper extends EntityMapper<CDetalleReservacionDTO, CDetalleReservacion> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "pasajero.id", target = "pasajeroId")
    @Mapping(source = "pasajero.nombreCompleto", target = "pasajeroNombreCompleto")
    @Mapping(source = "reservacion.id", target = "reservacionId")
    @Mapping(source = "reservacion.claveReservacion", target = "reservacionClaveReservacion")
    CDetalleReservacionDTO toDto(CDetalleReservacion cDetalleReservacion);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "pasajeroId", target = "pasajero")
    @Mapping(source = "reservacionId", target = "reservacion")
    CDetalleReservacion toEntity(CDetalleReservacionDTO cDetalleReservacionDTO);

    default CDetalleReservacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        CDetalleReservacion cDetalleReservacion = new CDetalleReservacion();
        cDetalleReservacion.setId(id);
        return cDetalleReservacion;
    }
}
