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
 * entidad CReservacion.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_reservacion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CReservacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 10)
    @Column(name = "clave_reservacion", length = 10)
    private String claveReservacion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "num_pasajeros")
    private Integer numPasajeros;

    @Size(max = 300)
    @Column(name = "comentarios", length = 300)
    private String comentarios;

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
    @JsonIgnoreProperties("cReservacions")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cReservacions")
    private CPasajero pasajeroPrincipal;

    @ManyToOne
    @JsonIgnoreProperties("cReservacions")
    private CCorrida corrida;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveReservacion() {
        return claveReservacion;
    }

    public CReservacion claveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
        return this;
    }

    public void setClaveReservacion(String claveReservacion) {
        this.claveReservacion = claveReservacion;
    }

    public Double getPrecio() {
        return precio;
    }

    public CReservacion precio(Double precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getNumPasajeros() {
        return numPasajeros;
    }

    public CReservacion numPasajeros(Integer numPasajeros) {
        this.numPasajeros = numPasajeros;
        return this;
    }

    public void setNumPasajeros(Integer numPasajeros) {
        this.numPasajeros = numPasajeros;
    }

    public String getComentarios() {
        return comentarios;
    }

    public CReservacion comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CReservacion idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CReservacion fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CReservacion idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CReservacion fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CReservacion notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CReservacion estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CReservacion borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CReservacion cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CPasajero getPasajeroPrincipal() {
        return pasajeroPrincipal;
    }

    public CReservacion pasajeroPrincipal(CPasajero cPasajero) {
        this.pasajeroPrincipal = cPasajero;
        return this;
    }

    public void setPasajeroPrincipal(CPasajero cPasajero) {
        this.pasajeroPrincipal = cPasajero;
    }

    public CCorrida getCorrida() {
        return corrida;
    }

    public CReservacion corrida(CCorrida cCorrida) {
        this.corrida = cCorrida;
        return this;
    }

    public void setCorrida(CCorrida cCorrida) {
        this.corrida = cCorrida;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CReservacion)) {
            return false;
        }
        return id != null && id.equals(((CReservacion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CReservacion{" +
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
            "}";
    }
}
