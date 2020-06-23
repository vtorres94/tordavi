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
 * entidad CDocumentoViaje.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_documento_viaje")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CDocumentoViaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 60)
    @Column(name = "clave_documento", length = 60)
    private String claveDocumento;

    @Size(max = 30)
    @Column(name = "documento", length = 30)
    private String documento;

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
    @JsonIgnoreProperties("cDocumentoViajes")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cDocumentoViajes")
    private CPasajero pasajero;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveDocumento() {
        return claveDocumento;
    }

    public CDocumentoViaje claveDocumento(String claveDocumento) {
        this.claveDocumento = claveDocumento;
        return this;
    }

    public void setClaveDocumento(String claveDocumento) {
        this.claveDocumento = claveDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public CDocumentoViaje documento(String documento) {
        this.documento = documento;
        return this;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CDocumentoViaje idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CDocumentoViaje fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CDocumentoViaje idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CDocumentoViaje fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CDocumentoViaje notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CDocumentoViaje estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CDocumentoViaje borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CDocumentoViaje cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CPasajero getPasajero() {
        return pasajero;
    }

    public CDocumentoViaje pasajero(CPasajero cPasajero) {
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
        if (!(o instanceof CDocumentoViaje)) {
            return false;
        }
        return id != null && id.equals(((CDocumentoViaje) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CDocumentoViaje{" +
            "id=" + getId() +
            ", claveDocumento='" + getClaveDocumento() + "'" +
            ", documento='" + getDocumento() + "'" +
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
