package mx.com.tordavi.service.mapper;


import mx.com.tordavi.domain.*;
import mx.com.tordavi.service.dto.CCorridaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link CCorrida} and its DTO {@link CCorridaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CClienteMapper.class, CAutobusMapper.class, CLugarParadaMapper.class})
public interface CCorridaMapper extends EntityMapper<CCorridaDTO, CCorrida> {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "cliente.cliente", target = "clienteCliente")
    @Mapping(source = "autobus.id", target = "autobusId")
    @Mapping(source = "autobus.autobus", target = "autobusAutobus")
    @Mapping(source = "lugarSalida.id", target = "lugarSalidaId")
    @Mapping(source = "lugarSalida.claveLugarParada", target = "lugarSalidaClaveLugarParada")
    @Mapping(source = "lugarLlegada.id", target = "lugarLlegadaId")
    @Mapping(source = "lugarLlegada.claveLugarParada", target = "lugarLlegadaClaveLugarParada")
    CCorridaDTO toDto(CCorrida cCorrida);

    @Mapping(source = "clienteId", target = "cliente")
    @Mapping(source = "autobusId", target = "autobus")
    @Mapping(source = "lugarSalidaId", target = "lugarSalida")
    @Mapping(source = "lugarLlegadaId", target = "lugarLlegada")
    CCorrida toEntity(CCorridaDTO cCorridaDTO);

    default CCorrida fromId(Long id) {
        if (id == null) {
            return null;
        }
        CCorrida cCorrida = new CCorrida();
        cCorrida.setId(id);
        return cCorrida;
    }
}
