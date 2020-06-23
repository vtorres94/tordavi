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
import { getEntity, updateEntity, createEntity, reset } from './c-documento-viaje.reducer';
import { ICDocumentoViaje } from 'app/shared/model/c-documento-viaje.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICDocumentoViajeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDocumentoViajeUpdate = (props: ICDocumentoViajeUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [pasajeroId, setPasajeroId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cDocumentoViajeEntity, cClientes, cPasajeros, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-documento-viaje' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
    props.getCPasajeros();
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
        ...cDocumentoViajeEntity,
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
          <h2 id="tordaviApp.cDocumentoViaje.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cDocumentoViaje.home.createOrEditLabel">Create or edit a CDocumentoViaje</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cDocumentoViajeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-documento-viaje-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-documento-viaje-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="claveDocumentoLabel" for="c-documento-viaje-claveDocumento">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.claveDocumento">Clave Documento</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-claveDocumento"
                  type="text"
                  name="claveDocumento"
                  validate={{
                    maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="documentoLabel" for="c-documento-viaje-documento">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.documento">Documento</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-documento"
                  type="text"
                  name="documento"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-documento-viaje-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-documento-viaje-fechaCreacion">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-documento-viaje-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDocumentoViajeEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-documento-viaje-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-documento-viaje-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-documento-viaje-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDocumentoViajeEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-documento-viaje-notas">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-documento-viaje-estatus">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-estatus"
                  type="string"
                  className="form-control"
                  name="estatus"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 1, errorMessage: translate('entity.validation.max', { max: 1 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="borradoLabel" for="c-documento-viaje-borrado">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-documento-viaje-borrado"
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
                <Label for="c-documento-viaje-cliente">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-documento-viaje-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-documento-viaje-pasajero">
                  <Translate contentKey="tordaviApp.cDocumentoViaje.pasajero">Pasajero</Translate>
                </Label>
                <AvInput id="c-documento-viaje-pasajero" type="select" className="form-control" name="pasajeroId">
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
              <Button tag={Link} id="cancel-save" to="/c-documento-viaje" replace color="info">
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
  cDocumentoViajeEntity: storeState.cDocumentoViaje.entity,
  loading: storeState.cDocumentoViaje.loading,
  updating: storeState.cDocumentoViaje.updating,
  updateSuccess: storeState.cDocumentoViaje.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getCPasajeros,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDocumentoViajeUpdate);
