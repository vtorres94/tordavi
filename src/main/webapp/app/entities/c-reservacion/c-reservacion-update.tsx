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
import { ICCorrida } from 'app/shared/model/c-corrida.model';
import { getEntities as getCCorridas } from 'app/entities/c-corrida/c-corrida.reducer';
import { getEntity, updateEntity, createEntity, reset } from './c-reservacion.reducer';
import { ICReservacion } from 'app/shared/model/c-reservacion.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICReservacionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CReservacionUpdate = (props: ICReservacionUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [pasajeroPrincipalId, setPasajeroPrincipalId] = useState('0');
  const [corridaId, setCorridaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cReservacionEntity, cClientes, cPasajeros, cCorridas, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-reservacion' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
    props.getCPasajeros();
    props.getCCorridas();
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
        ...cReservacionEntity,
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
          <h2 id="tordaviApp.cReservacion.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cReservacion.home.createOrEditLabel">Create or edit a CReservacion</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cReservacionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-reservacion-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-reservacion-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="claveReservacionLabel" for="c-reservacion-claveReservacion">
                  <Translate contentKey="tordaviApp.cReservacion.claveReservacion">Clave Reservacion</Translate>
                </Label>
                <AvField
                  id="c-reservacion-claveReservacion"
                  type="text"
                  name="claveReservacion"
                  validate={{
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="precioLabel" for="c-reservacion-precio">
                  <Translate contentKey="tordaviApp.cReservacion.precio">Precio</Translate>
                </Label>
                <AvField id="c-reservacion-precio" type="string" className="form-control" name="precio" />
              </AvGroup>
              <AvGroup>
                <Label id="numPasajerosLabel" for="c-reservacion-numPasajeros">
                  <Translate contentKey="tordaviApp.cReservacion.numPasajeros">Num Pasajeros</Translate>
                </Label>
                <AvField id="c-reservacion-numPasajeros" type="string" className="form-control" name="numPasajeros" />
              </AvGroup>
              <AvGroup>
                <Label id="comentariosLabel" for="c-reservacion-comentarios">
                  <Translate contentKey="tordaviApp.cReservacion.comentarios">Comentarios</Translate>
                </Label>
                <AvField
                  id="c-reservacion-comentarios"
                  type="text"
                  name="comentarios"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-reservacion-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cReservacion.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-reservacion-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-reservacion-fechaCreacion">
                  <Translate contentKey="tordaviApp.cReservacion.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-reservacion-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cReservacionEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-reservacion-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cReservacion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-reservacion-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-reservacion-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cReservacion.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-reservacion-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cReservacionEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-reservacion-notas">
                  <Translate contentKey="tordaviApp.cReservacion.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-reservacion-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-reservacion-estatus">
                  <Translate contentKey="tordaviApp.cReservacion.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-reservacion-estatus"
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
                <Label id="borradoLabel" for="c-reservacion-borrado">
                  <Translate contentKey="tordaviApp.cReservacion.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-reservacion-borrado"
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
                <Label for="c-reservacion-cliente">
                  <Translate contentKey="tordaviApp.cReservacion.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-reservacion-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-reservacion-pasajeroPrincipal">
                  <Translate contentKey="tordaviApp.cReservacion.pasajeroPrincipal">Pasajero Principal</Translate>
                </Label>
                <AvInput id="c-reservacion-pasajeroPrincipal" type="select" className="form-control" name="pasajeroPrincipalId">
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
                <Label for="c-reservacion-corrida">
                  <Translate contentKey="tordaviApp.cReservacion.corrida">Corrida</Translate>
                </Label>
                <AvInput id="c-reservacion-corrida" type="select" className="form-control" name="corridaId">
                  <option value="" key="0" />
                  {cCorridas
                    ? cCorridas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.claveCorrida}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/c-reservacion" replace color="info">
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
  cCorridas: storeState.cCorrida.entities,
  cReservacionEntity: storeState.cReservacion.entity,
  loading: storeState.cReservacion.loading,
  updating: storeState.cReservacion.updating,
  updateSuccess: storeState.cReservacion.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getCPasajeros,
  getCCorridas,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CReservacionUpdate);
