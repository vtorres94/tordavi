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
 * entidad CVenta.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_venta")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Max(value = 999999999999L)
    @Column(name = "vendedor_id", nullable = false)
    private Long vendedorId;

    @Column(name = "precio_total")
    private Double precioTotal;

    @NotNull
    @Column(name = "fecha_venta", nullable = false)
    private Instant fechaVenta;

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
    @JsonIgnoreProperties("cVentas")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cVentas")
    private CReservacion reservacion;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public CVenta vendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
        return this;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public CVenta precioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
        return this;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public Instant getFechaVenta() {
        return fechaVenta;
    }

    public CVenta fechaVenta(Instant fechaVenta) {
        this.fechaVenta = fechaVenta;
        return this;
    }

    public void setFechaVenta(Instant fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CVenta idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CVenta fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CVenta idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CVenta fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CVenta notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CVenta estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CVenta borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CVenta cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CReservacion getReservacion() {
        return reservacion;
    }

    public CVenta reservacion(CReservacion cReservacion) {
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
        if (!(o instanceof CVenta)) {
            return false;
        }
        return id != null && id.equals(((CVenta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CVenta{" +
            "id=" + getId() +
            ", vendedorId=" + getVendedorId() +
            ", precioTotal=" + getPrecioTotal() +
            ", fechaVenta='" + getFechaVenta() + "'" +
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
