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
 * Criteria class for the {@link mx.com.tordavi.domain.CPendiente} entity. This class is used
 * in {@link mx.com.tordavi.web.rest.CPendienteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /c-pendientes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CPendienteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter comentarios;

    private LongFilter idUsuarioCreacion;

    private InstantFilter fechaCreacion;

    private LongFilter idUsuarioActualizacion;

    private InstantFilter fechaActualizacion;

    private StringFilter notas;

    private IntegerFilter estatus;

    private IntegerFilter borrado;

    private LongFilter clienteId;

    private LongFilter reservacionId;

    public CPendienteCriteria() {
    }

    public CPendienteCriteria(CPendienteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.comentarios = other.comentarios == null ? null : other.comentarios.copy();
        this.idUsuarioCreacion = other.idUsuarioCreacion == null ? null : other.idUsuarioCreacion.copy();
        this.fechaCreacion = other.fechaCreacion == null ? null : other.fechaCreacion.copy();
        this.idUsuarioActualizacion = other.idUsuarioActualizacion == null ? null : other.idUsuarioActualizacion.copy();
        this.fechaActualizacion = other.fechaActualizacion == null ? null : other.fechaActualizacion.copy();
        this.notas = other.notas == null ? null : other.notas.copy();
        this.estatus = other.estatus == null ? null : other.estatus.copy();
        this.borrado = other.borrado == null ? null : other.borrado.copy();
        this.clienteId = other.clienteId == null ? null : other.clienteId.copy();
        this.reservacionId = other.reservacionId == null ? null : other.reservacionId.copy();
    }

    @Override
    public CPendienteCriteria copy() {
        return new CPendienteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
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

    public LongFilter getReservacionId() {
        return reservacionId;
    }

    public void setReservacionId(LongFilter reservacionId) {
        this.reservacionId = reservacionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CPendienteCriteria that = (CPendienteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(comentarios, that.comentarios) &&
            Objects.equals(idUsuarioCreacion, that.idUsuarioCreacion) &&
            Objects.equals(fechaCreacion, that.fechaCreacion) &&
            Objects.equals(idUsuarioActualizacion, that.idUsuarioActualizacion) &&
            Objects.equals(fechaActualizacion, that.fechaActualizacion) &&
            Objects.equals(notas, that.notas) &&
            Objects.equals(estatus, that.estatus) &&
            Objects.equals(borrado, that.borrado) &&
            Objects.equals(clienteId, that.clienteId) &&
            Objects.equals(reservacionId, that.reservacionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        comentarios,
        idUsuarioCreacion,
        fechaCreacion,
        idUsuarioActualizacion,
        fechaActualizacion,
        notas,
        estatus,
        borrado,
        clienteId,
        reservacionId
        );
    }

    @Override
    public String toString() {
        return "CPendienteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (comentarios != null ? "comentarios=" + comentarios + ", " : "") +
                (idUsuarioCreacion != null ? "idUsuarioCreacion=" + idUsuarioCreacion + ", " : "") +
                (fechaCreacion != null ? "fechaCreacion=" + fechaCreacion + ", " : "") +
                (idUsuarioActualizacion != null ? "idUsuarioActualizacion=" + idUsuarioActualizacion + ", " : "") +
                (fechaActualizacion != null ? "fechaActualizacion=" + fechaActualizacion + ", " : "") +
                (notas != null ? "notas=" + notas + ", " : "") +
                (estatus != null ? "estatus=" + estatus + ", " : "") +
                (borrado != null ? "borrado=" + borrado + ", " : "") +
                (clienteId != null ? "clienteId=" + clienteId + ", " : "") +
                (reservacionId != null ? "reservacionId=" + reservacionId + ", " : "") +
            "}";
    }

}
