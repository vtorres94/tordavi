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
 * Criteria class for the {@link mx.com.tordavi.domain.CContacto} entity. This class is used
 * in {@link mx.com.tordavi.web.rest.CContactoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /c-contactos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CContactoCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreContacto;

    private StringFilter telefono;

    private StringFilter area;

    private LongFilter idUsuarioCreacion;

    private InstantFilter fechaCreacion;

    private LongFilter idUsuarioActualizacion;

    private InstantFilter fechaActualizacion;

    private StringFilter notas;

    private IntegerFilter estatus;

    private IntegerFilter borrado;

    private LongFilter clienteId;

    private LongFilter pasajeroId;

    public CContactoCriteria() {
    }

    public CContactoCriteria(CContactoCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombreContacto = other.nombreContacto == null ? null : other.nombreContacto.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.area = other.area == null ? null : other.area.copy();
        this.idUsuarioCreacion = other.idUsuarioCreacion == null ? null : other.idUsuarioCreacion.copy();
        this.fechaCreacion = other.fechaCreacion == null ? null : other.fechaCreacion.copy();
        this.idUsuarioActualizacion = other.idUsuarioActualizacion == null ? null : other.idUsuarioActualizacion.copy();
        this.fechaActualizacion = other.fechaActualizacion == null ? null : other.fechaActualizacion.copy();
        this.notas = other.notas == null ? null : other.notas.copy();
        this.estatus = other.estatus == null ? null : other.estatus.copy();
        this.borrado = other.borrado == null ? null : other.borrado.copy();
        this.clienteId = other.clienteId == null ? null : other.clienteId.copy();
        this.pasajeroId = other.pasajeroId == null ? null : other.pasajeroId.copy();
    }

    @Override
    public CContactoCriteria copy() {
        return new CContactoCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(StringFilter nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getArea() {
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
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

    public LongFilter getPasajeroId() {
        return pasajeroId;
    }

    public void setPasajeroId(LongFilter pasajeroId) {
        this.pasajeroId = pasajeroId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CContactoCriteria that = (CContactoCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreContacto, that.nombreContacto) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(area, that.area) &&
            Objects.equals(idUsuarioCreacion, that.idUsuarioCreacion) &&
            Objects.equals(fechaCreacion, that.fechaCreacion) &&
            Objects.equals(idUsuarioActualizacion, that.idUsuarioActualizacion) &&
            Objects.equals(fechaActualizacion, that.fechaActualizacion) &&
            Objects.equals(notas, that.notas) &&
            Objects.equals(estatus, that.estatus) &&
            Objects.equals(borrado, that.borrado) &&
            Objects.equals(clienteId, that.clienteId) &&
            Objects.equals(pasajeroId, that.pasajeroId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreContacto,
        telefono,
        area,
        idUsuarioCreacion,
        fechaCreacion,
        idUsuarioActualizacion,
        fechaActualizacion,
        notas,
        estatus,
        borrado,
        clienteId,
        pasajeroId
        );
    }

    @Override
    public String toString() {
        return "CContactoCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreContacto != null ? "nombreContacto=" + nombreContacto + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (area != null ? "area=" + area + ", " : "") +
                (idUsuarioCreacion != null ? "idUsuarioCreacion=" + idUsuarioCreacion + ", " : "") +
                (fechaCreacion != null ? "fechaCreacion=" + fechaCreacion + ", " : "") +
                (idUsuarioActualizacion != null ? "idUsuarioActualizacion=" + idUsuarioActualizacion + ", " : "") +
                (fechaActualizacion != null ? "fechaActualizacion=" + fechaActualizacion + ", " : "") +
                (notas != null ? "notas=" + notas + ", " : "") +
                (estatus != null ? "estatus=" + estatus + ", " : "") +
                (borrado != null ? "borrado=" + borrado + ", " : "") +
                (clienteId != null ? "clienteId=" + clienteId + ", " : "") +
                (pasajeroId != null ? "pasajeroId=" + pasajeroId + ", " : "") +
            "}";
    }

}
