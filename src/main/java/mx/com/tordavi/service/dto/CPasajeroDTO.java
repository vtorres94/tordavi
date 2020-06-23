package mx.com.tordavi.service.dto;

import io.swagger.annotations.ApiModel;
import java.time.Instant;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mx.com.tordavi.domain.CPasajero} entity.
 */
@ApiModel(description = "entidad CPasajero. @author Vladimir Torres.")
public class CPasajeroDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 60)
    private String nombreCompleto;

    @NotNull
    @Size(max = 30)
    private String nombre;

    @Size(max = 30)
    private String segundoNombre;

    @NotNull
    @Size(max = 30)
    private String apellido;

    @Size(max = 30)
    private String segundoApellido;

    @Max(value = 999)
    private Integer edad;

    @Size(max = 30)
    private String curp;

    @Size(max = 20)
    private String ciudadania;

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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getCiudadania() {
        return ciudadania;
    }

    public void setCiudadania(String ciudadania) {
        this.ciudadania = ciudadania;
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

        CPasajeroDTO cPasajeroDTO = (CPasajeroDTO) o;
        if (cPasajeroDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cPasajeroDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CPasajeroDTO{" +
            "id=" + getId() +
            ", nombreCompleto='" + getNombreCompleto() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", segundoNombre='" + getSegundoNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", segundoApellido='" + getSegundoApellido() + "'" +
            ", edad=" + getEdad() +
            ", curp='" + getCurp() + "'" +
            ", ciudadania='" + getCiudadania() + "'" +
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
