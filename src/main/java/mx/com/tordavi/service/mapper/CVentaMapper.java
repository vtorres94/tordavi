package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CVentaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CVenta} and its DTO {@link CVentaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CReservacionMapper.class})
public interface CVentaMapper extends EntityMapper<CVentaDTO, CVenta> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "reservacion.id", target = "reservacionId")
    @Mapping(source = "reservacion.claveReservacion", target = "reservacionClaveReservacion")
    CVentaDTO toDto(CVenta cVenta);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "reservacionId", target = "reservacion")
    CVenta toEntity(CVentaDTO cVentaDTO);

    default CVenta fromId(Long id) {
        if (id == null) {
            return null;
        }
        CVenta cVenta = new CVenta();
        cVenta.setId(id);
        return cVenta;
    }
}
