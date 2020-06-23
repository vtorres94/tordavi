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
 * entidad CCorrida.
 * @author Vladimir Torres.
 */
@Entity
@Table(name = "c_corrida")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CCorrida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "clave_corrida", length = 20, nullable = false)
    private String claveCorrida;

    @NotNull
    @Size(max = 5)
    @Column(name = "hora_salida", length = 5, nullable = false)
    private String horaSalida;

    @NotNull
    @Size(max = 5)
    @Column(name = "hora_lllegada", length = 5, nullable = false)
    private String horaLllegada;

    @NotNull
    @Column(name = "conexion", nullable = false)
    private Boolean conexion;

    @Size(max = 30)
    @Column(name = "lugar_conexion", length = 30)
    private String lugarConexion;

    @Size(max = 50)
    @Column(name = "dias_salida", length = 50)
    private String diasSalida;

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
    @JsonIgnoreProperties("cCorridas")
    private CCliente cliente;

    @ManyToOne
    @JsonIgnoreProperties("cCorridas")
    private CAutobus autobus;

    @ManyToOne
    @JsonIgnoreProperties("cCorridas")
    private CLugarParada lugarSalida;

    @ManyToOne
    @JsonIgnoreProperties("cCorridas")
    private CLugarParada lugarLlegada;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaveCorrida() {
        return claveCorrida;
    }

    public CCorrida claveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
        return this;
    }

    public void setClaveCorrida(String claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public CCorrida horaSalida(String horaSalida) {
        this.horaSalida = horaSalida;
        return this;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLllegada() {
        return horaLllegada;
    }

    public CCorrida horaLllegada(String horaLllegada) {
        this.horaLllegada = horaLllegada;
        return this;
    }

    public void setHoraLllegada(String horaLllegada) {
        this.horaLllegada = horaLllegada;
    }

    public Boolean isConexion() {
        return conexion;
    }

    public CCorrida conexion(Boolean conexion) {
        this.conexion = conexion;
        return this;
    }

    public void setConexion(Boolean conexion) {
        this.conexion = conexion;
    }

    public String getLugarConexion() {
        return lugarConexion;
    }

    public CCorrida lugarConexion(String lugarConexion) {
        this.lugarConexion = lugarConexion;
        return this;
    }

    public void setLugarConexion(String lugarConexion) {
        this.lugarConexion = lugarConexion;
    }

    public String getDiasSalida() {
        return diasSalida;
    }

    public CCorrida diasSalida(String diasSalida) {
        this.diasSalida = diasSalida;
        return this;
    }

    public void setDiasSalida(String diasSalida) {
        this.diasSalida = diasSalida;
    }

    public String getComentarios() {
        return comentarios;
    }

    public CCorrida comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public CCorrida idUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
        return this;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public CCorrida fechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public CCorrida idUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
        return this;
    }

    public void setIdUsuarioActualizacion(Long idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Instant getFechaActualizacion() {
        return fechaActualizacion;
    }

    public CCorrida fechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
        return this;
    }

    public void setFechaActualizacion(Instant fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public CCorrida notas(String notas) {
        this.notas = notas;
        return this;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public CCorrida estatus(Integer estatus) {
        this.estatus = estatus;
        return this;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Integer getBorrado() {
        return borrado;
    }

    public CCorrida borrado(Integer borrado) {
        this.borrado = borrado;
        return this;
    }

    public void setBorrado(Integer borrado) {
        this.borrado = borrado;
    }

    public CCliente getCliente() {
        return cliente;
    }

    public CCorrida cliente(CCliente cCliente) {
        this.cliente = cCliente;
        return this;
    }

    public void setCliente(CCliente cCliente) {
        this.cliente = cCliente;
    }

    public CAutobus getAutobus() {
        return autobus;
    }

    public CCorrida autobus(CAutobus cAutobus) {
        this.autobus = cAutobus;
        return this;
    }

    public void setAutobus(CAutobus cAutobus) {
        this.autobus = cAutobus;
    }

    public CLugarParada getLugarSalida() {
        return lugarSalida;
    }

    public CCorrida lugarSalida(CLugarParada cLugarParada) {
        this.lugarSalida = cLugarParada;
        return this;
    }

    public void setLugarSalida(CLugarParada cLugarParada) {
        this.lugarSalida = cLugarParada;
    }

    public CLugarParada getLugarLlegada() {
        return lugarLlegada;
    }

    public CCorrida lugarLlegada(CLugarParada cLugarParada) {
        this.lugarLlegada = cLugarParada;
        return this;
    }

    public void setLugarLlegada(CLugarParada cLugarParada) {
        this.lugarLlegada = cLugarParada;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CCorrida)) {
            return false;
        }
        return id != null && id.equals(((CCorrida) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CCorrida{" +
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
            "}";
    }
}
