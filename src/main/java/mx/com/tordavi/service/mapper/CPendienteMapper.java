package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CPendienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CPendiente} and its DTO {@link CPendienteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CReservacionMapper.class})
public interface CPendienteMapper extends EntityMapper<CPendienteDTO, CPendiente> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "reservacion.id", target = "reservacionId")
    @Mapping(source = "reservacion.claveReservacion", target = "reservacionClaveReservacion")
    CPendienteDTO toDto(CPendiente cPendiente);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "reservacionId", target = "reservacion")
    CPendiente toEntity(CPendienteDTO cPendienteDTO);

    default CPendiente fromId(Long id) {
        if (id == null) {
            return null;
        }
        CPendiente cPendiente = new CPendiente();
        cPendiente.setId(id);
        return cPendiente;
    }
}
