package mx.com.tordavi.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mx.com.tordavi.domain.CReservacion} entity.
 */
@ApiModel(description = "entidad CReservacion. @author Vladimir Torres.")
public class CReservacionDTO implements Serializable {
    
    private Long id;

    @Size(max = 10)
    private String claveReservacion;

    private Double precio;

    private Integer numPasajeros;

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

    private Long pasajeroPrincipalId;

    private String pasajeroPrincipalNombreCompleto;

    private Long corridaId;

    private String corridaClaveCorrida;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveReservacion() {
        return claveReservacion;
    }

    public void setClaveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNumPasajeros() {
        return numPasajeros;
    }

    public void setNumPasajeros(Integer numPasajeros) {
        this.numPasajeros = numPasajeros;
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

    public Long getPasajeroPrincipalId() {
        return pasajeroPrincipalId;
    }

    public void setPasajeroPrincipalId(Long cPasajeroId) {
        this.pasajeroPrincipalId = cPasajeroId;
    }

    public String getPasajeroPrincipalNombreCompleto() {
        return pasajeroPrincipalNombreCompleto;
    }

    public void setPasajeroPrincipalNombreCompleto(String cPasajeroNombreCompleto) {
        this.pasajeroPrincipalNombreCompleto = cPasajeroNombreCompleto;
    }

    public Long getCorridaId() {
        return corridaId;
    }

    public void setCorridaId(Long cCorridaId) {
        this.corridaId = cCorridaId;
    }

    public String getCorridaClaveCorrida() {
        return corridaClaveCorrida;
    }

    public void setCorridaClaveCorrida(String cCorridaClaveCorrida) {
        this.corridaClaveCorrida = cCorridaClaveCorrida;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CReservacionDTO cReservacionDTO = (CReservacionDTO) o;
        if (cReservacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cReservacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CReservacionDTO{" +
            "id=" + getId() +
            ", claveReservacion='" + getClaveReservacion() + "'" +
            ", precio=" + getPrecio() +
            ", numPasajeros=" + getNumPasajeros() +
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
            ", pasajeroPrincipalId=" + getPasajeroPrincipalId() +
            ", pasajeroPrincipalNombreCompleto='" + getPasajeroPrincipalNombreCompleto() + "'" +
            ", corridaId=" + getCorridaId() +
            ", corridaClaveCorrida='" + getCorridaClaveCorrida() + "'" +
            "}";
    }
}
