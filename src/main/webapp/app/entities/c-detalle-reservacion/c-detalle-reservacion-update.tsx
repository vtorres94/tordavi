import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICCliente } from 'app/shared/model/c-cliente.model';
import { getEntities as getCClientes } from 'app/entities/c-cliente/c-cliente.reducer';
import { ICPasajero } from 'app/shared/model/c-pasajero.model';
import { getEntities as getCPasajeros } from 'app/entities/c-pasajero/c-pasajero.reducer';
import { ICReservacion } from 'app/shared/model/c-reservacion.model';
import { getEntities as getCReservacions } from 'app/entities/c-reservacion/c-reservacion.reducer';
import { getEntity, updateEntity, createEntity, reset } from './c-detalle-reservacion.reducer';
import { ICDetalleReservacion } from 'app/shared/model/c-detalle-reservacion.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICDetalleReservacionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDetalleReservacionUpdate = (props: ICDetalleReservacionUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [pasajeroId, setPasajeroId] = useState('0');
  const [reservacionId, setReservacionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cDetalleReservacionEntity, cClientes, cPasajeros, cReservacions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-detalle-reservacion' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
    props.getCPasajeros();
    props.getCReservacions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.fechaCreacion = convertDateTimeToServer(values.fechaCreacion);
    values.fechaActualizacion = convertDateTimeToServer(values.fechaActualizacion);

    if (errors.length === 0) {
      const entity = {
        ...cDetalleReservacionEntity,
        ...values
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tordaviApp.cDetalleReservacion.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cDetalleReservacion.home.createOrEditLabel">Create or edit a CDetalleReservacion</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cDetalleReservacionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-detalle-reservacion-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-detalle-reservacion-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="precioLabel" for="c-detalle-reservacion-precio">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.precio">Precio</Translate>
                </Label>
                <AvField id="c-detalle-reservacion-precio" type="string" className="form-control" name="precio" />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-detalle-reservacion-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-detalle-reservacion-idUsuarioCreacion"
                  type="string"
                  className="form-control"
                  name="idUsuarioCreacion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 999999999999, errorMessage: translate('entity.validation.max', { max: 999999999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaCreacionLabel" for="c-detalle-reservacion-fechaCreacion">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-detalle-reservacion-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDetalleReservacionEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-detalle-reservacion-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-detalle-reservacion-idUsuarioActualizacion"
                  type="string"
                  className="form-control"
                  name="idUsuarioActualizacion"
                  validate={{
                    max: { value: 999999999999, errorMessage: translate('entity.validation.max', { max: 999999999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaActualizacionLabel" for="c-detalle-reservacion-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-detalle-reservacion-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDetalleReservacionEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-detalle-reservacion-notas">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-detalle-reservacion-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-detalle-reservacion-estatus">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-detalle-reservacion-estatus"
                  type="string"
                  className="form-control"
                  name="estatus"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 9, errorMessage: translate('entity.validation.max', { max: 9 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="borradoLabel" for="c-detalle-reservacion-borrado">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-detalle-reservacion-borrado"
                  type="string"
                  className="form-control"
                  name="borrado"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 1, errorMessage: translate('entity.validation.max', { max: 1 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="c-detalle-reservacion-cliente">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-detalle-reservacion-cliente" type="select" className="form-control" name="clienteId">
                  <option value="" key="0" />
                  {cClientes
                    ? cClientes.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.cliente}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="c-detalle-reservacion-pasajero">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.pasajero">Pasajero</Translate>
                </Label>
                <AvInput id="c-detalle-reservacion-pasajero" type="select" className="form-control" name="pasajeroId">
                  <option value="" key="0" />
                  {cPasajeros
                    ? cPasajeros.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.nombreCompleto}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="c-detalle-reservacion-reservacion">
                  <Translate contentKey="tordaviApp.cDetalleReservacion.reservacion">Reservacion</Translate>
                </Label>
                <AvInput id="c-detalle-reservacion-reservacion" type="select" className="form-control" name="reservacionId">
                  <option value="" key="0" />
                  {cReservacions
                    ? cReservacions.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.claveReservacion}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/c-detalle-reservacion" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  cClientes: storeState.cCliente.entities,
  cPasajeros: storeState.cPasajero.entities,
  cReservacions: storeState.cReservacion.entities,
  cDetalleReservacionEntity: storeState.cDetalleReservacion.entity,
  loading: storeState.cDetalleReservacion.loading,
  updating: storeState.cDetalleReservacion.updating,
  updateSuccess: storeState.cDetalleReservacion.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getCPasajeros,
  getCReservacions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDetalleReservacionUpdate);
