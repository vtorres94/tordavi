package mx.com.tordavi.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mx.com.tordavi.domain.CLugarParada} entity.
 */
@ApiModel(description = "entidad CLugarParada. @author Vladimir Torres.")
public class CLugarParadaDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 20)
    private String claveLugarParada;

    @Size(max = 30)
    private String comunidad;

    @Size(max = 30)
    private String ciudad;

    @Size(max = 30)
    private String estado;

    @Size(max = 20)
    private String pais;

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
    @Max(value = 1)
    private Integer estatus;

    @NotNull
    @Max(value = 1)
    private Integer borrado;


    private Long clienteId;

    private String clienteCliente;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveLugarParada() {
        return claveLugarParada;
    }

    public void setClaveLugarParada(String claveLugarParada) {
        this.claveLugarParada = claveLugarParada;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CLugarParadaDTO cLugarParadaDTO = (CLugarParadaDTO) o;
        if (cLugarParadaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cLugarParadaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CLugarParadaDTO{" +
            "id=" + getId() +
            ", claveLugarParada='" + getClaveLugarParada() + "'" +
            ", comunidad='" + getComunidad() + "'" +
            ", ciudad='" + getCiudad() + "'" +
            ", estado='" + getEstado() + "'" +
            ", pais='" + getPais() + "'" +
            ", idUsuarioCreacion=" + getIdUsuarioCreacion() +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", idUsuarioActualizacion=" + getIdUsuarioActualizacion() +
            ", fechaActualizacion='" + getFechaActualizacion() + "'" +
            ", notas='" + getNotas() + "'" +
            ", estatus=" + getEstatus() +
            ", borrado=" + getBorrado() +
            ", clienteId=" + getClienteId() +
            ", clienteCliente='" + getClienteCliente() + "'" +
            "}";
    }
}
