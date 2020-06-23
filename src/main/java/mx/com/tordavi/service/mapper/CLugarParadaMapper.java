package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CLugarParadaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CLugarParada} and its DTO {@link CLugarParadaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class})
public interface CLugarParadaMapper extends EntityMapper<CLugarParadaDTO, CLugarParada> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    CLugarParadaDTO toDto(CLugarParada cLugarParada);

    @Mapping(source = "clienteId", target = "cliente")
    CLugarParada toEntity(CLugarParadaDTO cLugarParadaDTO);

    default CLugarParada fromId(Long id) {
        if (id == null) {
            return null;
        }
        CLugarParada cLugarParada = new CLugarParada();
        cLugarParada.setId(id);
        return cLugarParada;
    }
}
