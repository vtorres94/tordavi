package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CClienteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CCliente} and its DTO {@link CClienteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CClienteMapper extends EntityMapper<CClienteDTO, CCliente> {



    default CCliente fromId(Long id) {
        if (id == null) {
            return null;
        }
        CCliente cCliente = new CCliente();
        cCliente.setId(id);
        return cCliente;
    }
}
