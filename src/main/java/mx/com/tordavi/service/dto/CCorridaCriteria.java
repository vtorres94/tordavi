package mx.com.tordavi.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.InstantFilter;

/**
 * Criteria class for the {@link mx.com.tordavi.domain.CCorrida} entity. This class is used
 * in {@link mx.com.tordavi.web.rest.CCorridaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /c-corridas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CCorridaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter claveCorrida;

    private StringFilter horaSalida;

    private StringFilter horaLllegada;

    private BooleanFilter conexion;

    private StringFilter lugarConexion;

    private StringFilter diasSalida;

    private StringFilter comentarios;

    private LongFilter idUsuarioCreacion;

    private InstantFilter fechaCreacion;

    private LongFilter idUsuarioActualizacion;

    private InstantFilter fechaActualizacion;

    private StringFilter notas;

    private IntegerFilter estatus;

    private IntegerFilter borrado;

    private LongFilter clienteId;

    private LongFilter autobusId;

    private LongFilter lugarSalidaId;

    private LongFilter lugarLlegadaId;

    public CCorridaCriteria() {
    }

    public CCorridaCriteria(CCorridaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.claveCorrida = other.claveCorrida == null ? null : other.claveCorrida.copy();
        this.horaSalida = other.horaSalida == null ? null : other.horaSalida.copy();
        this.horaLllegada = other.horaLllegada == null ? null : other.horaLllegada.copy();
        this.conexion = other.conexion == null ? null : other.conexion.copy();
        this.lugarConexion = other.lugarConexion == null ? null : other.lugarConexion.copy();
        this.diasSalida = other.diasSalida == null ? null : other.diasSalida.copy();
        this.comentarios = other.comentarios == null ? null : other.comentarios.copy();
        this.idUsuarioCreacion = other.idUsuarioCreacion == null ? null : other.idUsuarioCreacion.copy();
        this.fechaCreacion = other.fechaCreacion == null ? null : other.fechaCreacion.copy();
        this.idUsuarioActualizacion = other.idUsuarioActualizacion == null ? null : other.idUsuarioActualizacion.copy();
        this.fechaActualizacion = other.fechaActualizacion == null ? null : other.fechaActualizacion.copy();
        this.notas = other.notas == null ? null : other.notas.copy();
        this.estatus = other.estatus == null ? null : other.estatus.copy();
        this.borrado = other.borrado == null ? null : other.borrado.copy();
        this.clienteId = other.clienteId == null ? null : other.clienteId.copy();
        this.autobusId = other.autobusId == null ? null : other.autobusId.copy();
        this.lugarSalidaId = other.lugarSalidaId == null ? null : other.lugarSalidaId.copy();
        this.lugarLlegadaId = other.lugarLlegadaId == null ? null : other.lugarLlegadaId.copy();
    }

    @Override
    public CCorridaCriteria copy() {
        return new CCorridaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getClaveCorrida() {
        return claveCorrida;
    }

    public void setClaveCorrida(StringFilter claveCorrida) {
        this.claveCorrida = claveCorrida;
    }

    public StringFilter getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(StringFilter horaSalida) {
        this.horaSalida = horaSalida;
    }

    public StringFilter getHoraLllegada() {
        return horaLllegada;
    }

    public void setHoraLllegada(StringFilter horaLllegada) {
        this.horaLllegada = horaLllegada;
    }

    public BooleanFilter getConexion() {
        return conexion;
    }

    public void setConexion(BooleanFilter conexion) {
        this.conexion = conexion;
    }

    public StringFilter getLugarConexion() {
        return lugarConexion;
    }

    public void setLugarConexion(StringFilter lugarConexion) {
        this.lugarConexion = lugarConexion;
    }

    public StringFilter getDiasSalida() {
        return diasSalida;
    }

    public void setDiasSalida(StringFilter diasSalida) {
        this.diasSalida = diasSalida;
    }

    public StringFilter getComentarios() {
        return comentarios;
    }

    public void setComentarios(StringFilter comentarios) {
        this.comentarios = comentarios;
    }

    public LongFilter getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(LongFilter idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public InstantFilter getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(InstantFilter fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LongFilter getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(LongFilter idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public InstantFilter getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(InstantFilter fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public StringFilter getNotas() {
        return notas;
    }

    public void setNotas(StringFilter notas) {
        this.notas = notas;
    }

    public IntegerFilter getEstatus() {
        return estatus;
    }

    public void setEstatus(IntegerFilter estatus) {
        this.estatus = estatus;
    }

    public IntegerFilter getBorrado() {
        return borrado;
    }

    public void setBorrado(IntegerFilter borrado) {
        this.borrado = borrado;
    }

    public LongFilter getClienteId() {
        return clienteId;
    }

    public void setClienteId(LongFilter clienteId) {
        this.clienteId = clienteId;
    }

    public LongFilter getAutobusId() {
        return autobusId;
    }

    public void setAutobusId(LongFilter autobusId) {
        this.autobusId = autobusId;
    }

    public LongFilter getLugarSalidaId() {
        return lugarSalidaId;
    }

    public void setLugarSalidaId(LongFilter lugarSalidaId) {
        this.lugarSalidaId = lugarSalidaId;
    }

    public LongFilter getLugarLlegadaId() {
        return lugarLlegadaId;
    }

    public void setLugarLlegadaId(LongFilter lugarLlegadaId) {
        this.lugarLlegadaId = lugarLlegadaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CCorridaCriteria that = (CCorridaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(claveCorrida, that.claveCorrida) &&
            Objects.equals(horaSalida, that.horaSalida) &&
            Objects.equals(horaLllegada, that.horaLllegada) &&
            Objects.equals(conexion, that.conexion) &&
            Objects.equals(lugarConexion, that.lugarConexion) &&
            Objects.equals(diasSalida, that.diasSalida) &&
            Objects.equals(comentarios, that.comentarios) &&
            Objects.equals(idUsuarioCreacion, that.idUsuarioCreacion) &&
            Objects.equals(fechaCreacion, that.fechaCreacion) &&
            Objects.equals(idUsuarioActualizacion, that.idUsuarioActualizacion) &&
            Objects.equals(fechaActualizacion, that.fechaActualizacion) &&
            Objects.equals(notas, that.notas) &&
            Objects.equals(estatus, that.estatus) &&
            Objects.equals(borrado, that.borrado) &&
            Objects.equals(clienteId, that.clienteId) &&
            Objects.equals(autobusId, that.autobusId) &&
            Objects.equals(lugarSalidaId, that.lugarSalidaId) &&
            Objects.equals(lugarLlegadaId, that.lugarLlegadaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        claveCorrida,
        horaSalida,
        horaLllegada,
        conexion,
        lugarConexion,
        diasSalida,
        comentarios,
        idUsuarioCreacion,
        fechaCreacion,
        idUsuarioActualizacion,
        fechaActualizacion,
        notas,
        estatus,
        borrado,
        clienteId,
        autobusId,
        lugarSalidaId,
        lugarLlegadaId
        );
    }

    @Override
    public String toString() {
        return "CCorridaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (claveCorrida != null ? "claveCorrida=" + claveCorrida + ", " : "") +
                (horaSalida != null ? "horaSalida=" + horaSalida + ", " : "") +
                (horaLllegada != null ? "horaLllegada=" + horaLllegada + ", " : "") +
                (conexion != null ? "conexion=" + conexion + ", " : "") +
                (lugarConexion != null ? "lugarConexion=" + lugarConexion + ", " : "") +
                (diasSalida != null ? "diasSalida=" + diasSalida + ", " : "") +
                (comentarios != null ? "comentarios=" + comentarios + ", " : "") +
                (idUsuarioCreacion != null ? "idUsuarioCreacion=" + idUsuarioCreacion + ", " : "") +
                (fechaCreacion != null ? "fechaCreacion=" + fechaCreacion + ", " : "") +
                (idUsuarioActualizacion != null ? "idUsuarioActualizacion=" + idUsuarioActualizacion + ", " : "") +
                (fechaActualizacion != null ? "fechaActualizacion=" + fechaActualizacion + ", " : "") +
                (notas != null ? "notas=" + notas + ", " : "") +
                (estatus != null ? "estatus=" + estatus + ", " : "") +
                (borrado != null ? "borrado=" + borrado + ", " : "") +
                (clienteId != null ? "clienteId=" + clienteId + ", " : "") +
                (autobusId != null ? "autobusId=" + autobusId + ", " : "") +
                (lugarSalidaId != null ? "lugarSalidaId=" + lugarSalidaId + ", " : "") +
                (lugarLlegadaId != null ? "lugarLlegadaId=" + lugarLlegadaId + ", " : "") +
            "}";
    }

}
