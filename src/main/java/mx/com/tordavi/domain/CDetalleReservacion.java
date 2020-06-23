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
 * entidad CDetalleReservacion.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_detalle_reservacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CDetalleReservacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "precio")
    private Double precio;

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
    @Max(value = 9)
    @Column(name = "estatus", nullable = false)
    private Integer estatus;

    @NotNull
    @Max(value = 1)
    @Column(name = "borrado", nullable = false)
    private Integer borrado;

    @ManyToOne
    @JsonIgnoreProperties("cDetalleReservacions")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cDetalleReservacions")
    private CPasajero pasajero;

    @ManyToOne
    @JsonIgnoreProperties("cDetalleReservacions")
    private CReservacion reservacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecio() {
        return precio;
    }

    public CDetalleReservacion precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CDetalleReservacion idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CDetalleReservacion fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CDetalleReservacion idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CDetalleReservacion fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CDetalleReservacion notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CDetalleReservacion estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CDetalleReservacion borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CDetalleReservacion cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CPasajero getPasajero() {
        return pasajero;
    }

    public CDetalleReservacion pasajero(CPasajero cPasajero) {
        this.pasajero = cPasajero;
        return this;
    }

    public void setPasajero(CPasajero cPasajero) {
        this.pasajero = cPasajero;
    }

    public CReservacion getReservacion() {
        return reservacion;
    }

    public CDetalleReservacion reservacion(CReservacion cReservacion) {
        this.reservacion = cReservacion;
        return this;
    }

    public void setReservacion(CReservacion cReservacion) {
        this.reservacion = cReservacion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CDetalleReservacion)) {
            return false;
        }
        return id != null && id.equals(((CDetalleReservacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CDetalleReservacion{" +
            "id=" + getId() +
            ", precio=" + getPrecio() +
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
