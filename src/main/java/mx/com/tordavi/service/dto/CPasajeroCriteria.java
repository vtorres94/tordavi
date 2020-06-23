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
 * Criteria class for the {@link mx.com.tordavi.domain.CPasajero} entity. This class is used
 * in {@link mx.com.tordavi.web.rest.CPasajeroResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /c-pasajeros?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CPasajeroCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreCompleto;

    private StringFilter nombre;

    private StringFilter segundoNombre;

    private StringFilter apellido;

    private StringFilter segundoApellido;

    private IntegerFilter edad;

    private StringFilter curp;

    private StringFilter ciudadania;

    private LongFilter idUsuarioCreacion;

    private InstantFilter fechaCreacion;

    private LongFilter idUsuarioActualizacion;

    private InstantFilter fechaActualizacion;

    private StringFilter notas;

    private IntegerFilter estatus;

    private IntegerFilter borrado;

    private LongFilter clienteId;

    public CPasajeroCriteria() {
    }

    public CPasajeroCriteria(CPasajeroCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombreCompleto = other.nombreCompleto == null ? null : other.nombreCompleto.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.segundoNombre = other.segundoNombre == null ? null : other.segundoNombre.copy();
        this.apellido = other.apellido == null ? null : other.apellido.copy();
        this.segundoApellido = other.segundoApellido == null ? null : other.segundoApellido.copy();
        this.edad = other.edad == null ? null : other.edad.copy();
        this.curp = other.curp == null ? null : other.curp.copy();
        this.ciudadania = other.ciudadania == null ? null : other.ciudadania.copy();
        this.idUsuarioCreacion = other.idUsuarioCreacion == null ? null : other.idUsuarioCreacion.copy();
        this.fechaCreacion = other.fechaCreacion == null ? null : other.fechaCreacion.copy();
        this.idUsuarioActualizacion = other.idUsuarioActualizacion == null ? null : other.idUsuarioActualizacion.copy();
        this.fechaActualizacion = other.fechaActualizacion == null ? null : other.fechaActualizacion.copy();
        this.notas = other.notas == null ? null : other.notas.copy();
        this.estatus = other.estatus == null ? null : other.estatus.copy();
        this.borrado = other.borrado == null ? null : other.borrado.copy();
        this.clienteId = other.clienteId == null ? null : other.clienteId.copy();
    }

    @Override
    public CPasajeroCriteria copy() {
        return new CPasajeroCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(StringFilter nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(StringFilter segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public StringFilter getApellido() {
        return apellido;
    }

    public void setApellido(StringFilter apellido) {
        this.apellido = apellido;
    }

    public StringFilter getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(StringFilter segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public IntegerFilter getEdad() {
        return edad;
    }

    public void setEdad(IntegerFilter edad) {
        this.edad = edad;
    }

    public StringFilter getCurp() {
        return curp;
    }

    public void setCurp(StringFilter curp) {
        this.curp = curp;
    }

    public StringFilter getCiudadania() {
        return ciudadania;
    }

    public void setCiudadania(StringFilter ciudadania) {
        this.ciudadania = ciudadania;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CPasajeroCriteria that = (CPasajeroCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreCompleto, that.nombreCompleto) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(segundoNombre, that.segundoNombre) &&
            Objects.equals(apellido, that.apellido) &&
            Objects.equals(segundoApellido, that.segundoApellido) &&
            Objects.equals(edad, that.edad) &&
            Objects.equals(curp, that.curp) &&
            Objects.equals(ciudadania, that.ciudadania) &&
            Objects.equals(idUsuarioCreacion, that.idUsuarioCreacion) &&
            Objects.equals(fechaCreacion, that.fechaCreacion) &&
            Objects.equals(idUsuarioActualizacion, that.idUsuarioActualizacion) &&
            Objects.equals(fechaActualizacion, that.fechaActualizacion) &&
            Objects.equals(notas, that.notas) &&
            Objects.equals(estatus, that.estatus) &&
            Objects.equals(borrado, that.borrado) &&
            Objects.equals(clienteId, that.clienteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreCompleto,
        nombre,
        segundoNombre,
        apellido,
        segundoApellido,
        edad,
        curp,
        ciudadania,
        idUsuarioCreacion,
        fechaCreacion,
        idUsuarioActualizacion,
        fechaActualizacion,
        notas,
        estatus,
        borrado,
        clienteId
        );
    }

    @Override
    public String toString() {
        return "CPasajeroCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreCompleto != null ? "nombreCompleto=" + nombreCompleto + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (segundoNombre != null ? "segundoNombre=" + segundoNombre + ", " : "") +
                (apellido != null ? "apellido=" + apellido + ", " : "") +
                (segundoApellido != null ? "segundoApellido=" + segundoApellido + ", " : "") +
                (edad != null ? "edad=" + edad + ", " : "") +
                (curp != null ? "curp=" + curp + ", " : "") +
                (ciudadania != null ? "ciudadania=" + ciudadania + ", " : "") +
                (idUsuarioCreacion != null ? "idUsuarioCreacion=" + idUsuarioCreacion + ", " : "") +
                (fechaCreacion != null ? "fechaCreacion=" + fechaCreacion + ", " : "") +
                (idUsuarioActualizacion != null ? "idUsuarioActualizacion=" + idUsuarioActualizacion + ", " : "") +
                (fechaActualizacion != null ? "fechaActualizacion=" + fechaActualizacion + ", " : "") +
                (notas != null ? "notas=" + notas + ", " : "") +
                (estatus != null ? "estatus=" + estatus + ", " : "") +
                (borrado != null ? "borrado=" + borrado + ", " : "") +
                (clienteId != null ? "clienteId=" + clienteId + ", " : "") +
            "}";
    }

}
