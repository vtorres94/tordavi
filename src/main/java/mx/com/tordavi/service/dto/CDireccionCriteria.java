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
 * Criteria class for the {@link mx.com.tordavi.domain.CDireccion} entity. This class is used
 * in {@link mx.com.tordavi.web.rest.CDireccionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /c-direccions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CDireccionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter direccionComplete;

    private StringFilter direccion;

    private StringFilter numExterior;

    private StringFilter numInterior;

    private IntegerFilter codigoPostal;

    private StringFilter ciudad;

    private StringFilter estado;

    private StringFilter pais;

    private LongFilter idUsuarioCreacion;

    private InstantFilter fechaCreacion;

    private LongFilter idUsuarioActualizacion;

    private InstantFilter fechaActualizacion;

    private StringFilter notas;

    private IntegerFilter estatus;

    private IntegerFilter borrado;

    private LongFilter clienteId;

    private LongFilter pasajeroId;

    public CDireccionCriteria() {
    }

    public CDireccionCriteria(CDireccionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.direccionComplete = other.direccionComplete == null ? null : other.direccionComplete.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.numExterior = other.numExterior == null ? null : other.numExterior.copy();
        this.numInterior = other.numInterior == null ? null : other.numInterior.copy();
        this.codigoPostal = other.codigoPostal == null ? null : other.codigoPostal.copy();
        this.ciudad = other.ciudad == null ? null : other.ciudad.copy();
        this.estado = other.estado == null ? null : other.estado.copy();
        this.pais = other.pais == null ? null : other.pais.copy();
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
    public CDireccionCriteria copy() {
        return new CDireccionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDireccionComplete() {
        return direccionComplete;
    }

    public void setDireccionComplete(StringFilter direccionComplete) {
        this.direccionComplete = direccionComplete;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getNumExterior() {
        return numExterior;
    }

    public void setNumExterior(StringFilter numExterior) {
        this.numExterior = numExterior;
    }

    public StringFilter getNumInterior() {
        return numInterior;
    }

    public void setNumInterior(StringFilter numInterior) {
        this.numInterior = numInterior;
    }

    public IntegerFilter getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(IntegerFilter codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public StringFilter getCiudad() {
        return ciudad;
    }

    public void setCiudad(StringFilter ciudad) {
        this.ciudad = ciudad;
    }

    public StringFilter getEstado() {
        return estado;
    }

    public void setEstado(StringFilter estado) {
        this.estado = estado;
    }

    public StringFilter getPais() {
        return pais;
    }

    public void setPais(StringFilter pais) {
        this.pais = pais;
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
        final CDireccionCriteria that = (CDireccionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(direccionComplete, that.direccionComplete) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(numExterior, that.numExterior) &&
            Objects.equals(numInterior, that.numInterior) &&
            Objects.equals(codigoPostal, that.codigoPostal) &&
            Objects.equals(ciudad, that.ciudad) &&
            Objects.equals(estado, that.estado) &&
            Objects.equals(pais, that.pais) &&
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
        direccionComplete,
        direccion,
        numExterior,
        numInterior,
        codigoPostal,
        ciudad,
        estado,
        pais,
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
        return "CDireccionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (direccionComplete != null ? "direccionComplete=" + direccionComplete + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (numExterior != null ? "numExterior=" + numExterior + ", " : "") +
                (numInterior != null ? "numInterior=" + numInterior + ", " : "") +
                (codigoPostal != null ? "codigoPostal=" + codigoPostal + ", " : "") +
                (ciudad != null ? "ciudad=" + ciudad + ", " : "") +
                (estado != null ? "estado=" + estado + ", " : "") +
                (pais != null ? "pais=" + pais + ", " : "") +
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
