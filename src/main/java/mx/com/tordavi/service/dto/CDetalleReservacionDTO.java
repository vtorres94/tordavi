package mx.com.tordavi.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mx.com.tordavi.domain.CDetalleReservacion} entity.
 */
@ApiModel(description = "entidad CDetalleReservacion. @author Vladimir Torres.")
public class CDetalleReservacionDTO implements Serializable {
    
    private Long id;

    private Double precio;

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

    private Long pasajeroId;

    private String pasajeroNombreCompleto;

    private Long reservacionId;

    private String reservacionClaveReservacion;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public Long getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(Long cPasajeroId) {
        this.pasajeroId = cPasajeroId;
    }

    public String getPasajeroNombreCompleto() {
        return pasajeroNombreCompleto;
    }

    public void setPasajeroNombreCompleto(String cPasajeroNombreCompleto) {
        this.pasajeroNombreCompleto = cPasajeroNombreCompleto;
    }

    public Long getReservacionId() {
        return reservacionId;
    }

    public void setReservacionId(Long cReservacionId) {
        this.reservacionId = cReservacionId;
    }

    public String getReservacionClaveReservacion() {
        return reservacionClaveReservacion;
    }

    public void setReservacionClaveReservacion(String cReservacionClaveReservacion) {
        this.reservacionClaveReservacion = cReservacionClaveReservacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CDetalleReservacionDTO cDetalleReservacionDTO = (CDetalleReservacionDTO) o;
        if (cDetalleReservacionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cDetalleReservacionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CDetalleReservacionDTO{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
            ", idUsuarioCreacion=" + getIdUsuarioCreacion() +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", idUsuarioActualizacion=" + getIdUsuarioActualizacion() +
            ", fechaActualizacion='" + getFechaActualizacion() + "'" +
            ", notas='" + getNotas() + "'" +
            ", estatus=" + getEstatus() +
            ", borrado=" + getBorrado() +
            ", clienteId=" + getClienteId() +
            ", clienteCliente='" + getClienteCliente() + "'" +
            ", pasajeroId=" + getPasajeroId() +
            ", pasajeroNombreCompleto='" + getPasajeroNombreCompleto() + "'" +
            ", reservacionId=" + getReservacionId() +
            ", reservacionClaveReservacion='" + getReservacionClaveReservacion() + "'" +
            "}";
    }
}
