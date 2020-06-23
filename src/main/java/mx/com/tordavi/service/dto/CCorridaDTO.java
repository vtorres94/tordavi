package mx.com.tordavi.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mx.com.tordavi.domain.CCorrida} entity.
 */
@ApiModel(description = "entidad CCorrida. @author Vladimir Torres.")
public class CCorridaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String claveCorrida;

    @NotNull
    @Size(max = 5)
    private String horaSalida;

    @NotNull
    @Size(max = 5)
    private String horaLllegada;

    @NotNull
    private Boolean conexion;

    @Size(max = 30)
    private String lugarConexion;

    @Size(max = 50)
    private String diasSalida;

    @Size(max = 300)
    private String comentarios;

    @NotNull
    @Max(value = 999999999999L)
    private Long idUsuarioCreacion;

    @NotNull
    private Instant fechaCreacion;

    @Max(value = 999999999999L)
    private Long idUsuarioActualizacion;

    private Instant fechaActualizacion;

    @Size(max = 300)
    private String notas;

    @NotNull
    @Max(value = 9)
    private Integer estatus;

    @NotNull
    @Max(value = 1)
    private Integer borrado;


    private Long clienteId;

    private String clienteCliente;

    private Long autobusId;

    private String autobusAutobus;

    private Long lugarSalidaId;

    private String lugarSalidaClaveLugarParada;

    private Long lugarLlegadaId;

    private String lugarLlegadaClaveLugarParada;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLllegada() {
        return horaLllegada;
    }

    public void setHoraLllegada(String horaLllegada) {
        this.horaLllegada = horaLllegada;
    }

    public Boolean isConexion() {
        return conexion;
    }

    public void setConexion(Boolean conexion) {
        this.conexion = conexion;
    }

    public String getLugarConexion() {
        return lugarConexion;
    }

    public void setLugarConexion(String lugarConexion) {
        this.lugarConexion = lugarConexion;
    }

    public String getDiasSalida() {
        return diasSalida;
    }

    public void setDiasSalida(String diasSalida) {
        this.diasSalida = diasSalida;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long cClienteId) {
        this.clienteId = cClienteId;
    }

    public String getClienteCliente() {
        return clienteCliente;
    }

    public void setClienteCliente(String cClienteCliente) {
        this.clienteCliente = cClienteCliente;
    }

    public Long getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(Long cAutobusId) {
        this.autobusId = cAutobusId;
    }

    public String getAutobusAutobus() {
        return autobusAutobus;
    }

    public void setAutobusAutobus(String cAutobusAutobus) {
        this.autobusAutobus = cAutobusAutobus;
    }

    public Long getLugarSalidaId() {
        return lugarSalidaId;
    }

    public void setLugarSalidaId(Long cLugarParadaId) {
        this.lugarSalidaId = cLugarParadaId;
    }

    public String getLugarSalidaClaveLugarParada() {
        return lugarSalidaClaveLugarParada;
    }

    public void setLugarSalidaClaveLugarParada(String cLugarParadaClaveLugarParada) {
        this.lugarSalidaClaveLugarParada = cLugarParadaClaveLugarParada;
    }

    public Long getLugarLlegadaId() {
        return lugarLlegadaId;
    }

    public void setLugarLlegadaId(Long cLugarParadaId) {
        this.lugarLlegadaId = cLugarParadaId;
    }

    public String getLugarLlegadaClaveLugarParada() {
        return lugarLlegadaClaveLugarParada;
    }

    public void setLugarLlegadaClaveLugarParada(String cLugarParadaClaveLugarParada) {
        this.lugarLlegadaClaveLugarParada = cLugarParadaClaveLugarParada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CCorridaDTO cCorridaDTO = (CCorridaDTO) o;
        if (cCorridaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cCorridaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CCorridaDTO{" +
            "id=" + getId() +
            ", claveCorrida='" + getClaveCorrida() + "'" +
            ", horaSalida='" + getHoraSalida() + "'" +
            ", horaLllegada='" + getHoraLllegada() + "'" +
            ", conexion='" + isConexion() + "'" +
            ", lugarConexion='" + getLugarConexion() + "'" +
            ", diasSalida='" + getDiasSalida() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            ", idUsuarioCreacion=" + getIdUsuarioCreacion() +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", idUsuarioActualizacion=" + getIdUsuarioActualizacion() +
            ", fechaActualizacion='" + getFechaActualizacion() + "'" +
            ", notas='" + getNotas() + "'" +
            ", estatus=" + getEstatus() +
            ", borrado=" + getBorrado() +
            ", clienteId=" + getClienteId() +
            ", clienteCliente='" + getClienteCliente() + "'" +
            ", autobusId=" + getAutobusId() +
            ", autobusAutobus='" + getAutobusAutobus() + "'" +
            ", lugarSalidaId=" + getLugarSalidaId() +
            ", lugarSalidaClaveLugarParada='" + getLugarSalidaClaveLugarParada() + "'" +
            ", lugarLlegadaId=" + getLugarLlegadaId() +
            ", lugarLlegadaClaveLugarParada='" + getLugarLlegadaClaveLugarParada() + "'" +
            "}";
    }
}
