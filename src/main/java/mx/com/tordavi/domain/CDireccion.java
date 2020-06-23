package mx.com.tordavi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * entidad CDireccion.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_direccion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CDireccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 120)
    @Column(name = "direccion_complete", length = 120)
    private String direccionComplete;

    @Size(max = 60)
    @Column(name = "direccion", length = 60)
    private String direccion;

    @Size(max = 10)
    @Column(name = "num_exterior", length = 10)
    private String numExterior;

    @Size(max = 10)
    @Column(name = "num_interior", length = 10)
    private String numInterior;

    @Max(value = 99999999)
    @Column(name = "codigo_postal")
    private Integer codigoPostal;

    @Size(max = 30)
    @Column(name = "ciudad", length = 30)
    private String ciudad;

    @Size(max = 30)
    @Column(name = "estado", length = 30)
    private String estado;

    @Size(max = 30)
    @Column(name = "pais", length = 30)
    private String pais;

    @NotNull
    @Max(value = 999999999999L)
    @Column(name = "id_usuario_creacion", nullable = false)
    private Long idUsuarioCreacion;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private Instant fechaCreacion;

    @Max(value = 999999999999L)
    @Column(name = "id_usuario_actualizacion")
    private Long idUsuarioActualizacion;

    @Column(name = "fecha_actualizacion")
    private Instant fechaActualizacion;

    @Size(max = 300)
    @Column(name = "notas", length = 300)
    private String notas;

    @NotNull
    @Max(value = 1)
    @Column(name = "estatus", nullable = false)
    private Integer estatus;

    @NotNull
    @Max(value = 1)
    @Column(name = "borrado", nullable = false)
    private Integer borrado;

    @ManyToOne
    @JsonIgnoreProperties("cDireccions")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cDireccions")
    private CPasajero pasajero;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccionComplete() {
        return direccionComplete;
    }

    public CDireccion direccionComplete(String direccionComplete) {
        this.direccionComplete = direccionComplete;
        return this;
    }

    public void setDireccionComplete(String direccionComplete) {
        this.direccionComplete = direccionComplete;
    }

    public String getDireccion() {
        return direccion;
    }

    public CDireccion direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumExterior() {
        return numExterior;
    }

    public CDireccion numExterior(String numExterior) {
        this.numExterior = numExterior;
        return this;
    }

    public void setNumExterior(String numExterior) {
        this.numExterior = numExterior;
    }

    public String getNumInterior() {
        return numInterior;
    }

    public CDireccion numInterior(String numInterior) {
        this.numInterior = numInterior;
        return this;
    }

    public void setNumInterior(String numInterior) {
        this.numInterior = numInterior;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public CDireccion codigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public CDireccion ciudad(String ciudad) {
        this.ciudad = ciudad;
        return this;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public CDireccion estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public CDireccion pais(String pais) {
        this.pais = pais;
        return this;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CDireccion idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CDireccion fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CDireccion idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CDireccion fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CDireccion notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CDireccion estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CDireccion borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CDireccion cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CPasajero getPasajero() {
        return pasajero;
    }

    public CDireccion pasajero(CPasajero cPasajero) {
        this.pasajero = cPasajero;
        return this;
    }

    public void setPasajero(CPasajero cPasajero) {
        this.pasajero = cPasajero;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CDireccion)) {
            return false;
        }
        return id != null && id.equals(((CDireccion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CDireccion{" +
            "id=" + getId() +
            ", direccionComplete='" + getDireccionComplete() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", numExterior='" + getNumExterior() + "'" +
            ", numInterior='" + getNumInterior() + "'" +
            ", codigoPostal=" + getCodigoPostal() +
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
            "}";
    }
}
