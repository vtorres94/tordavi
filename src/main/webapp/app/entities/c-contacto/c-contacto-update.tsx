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
import { getEntity, updateEntity, createEntity, reset } from './c-contacto.reducer';
import { ICContacto } from 'app/shared/model/c-contacto.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICContactoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CContactoUpdate = (props: ICContactoUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [pasajeroId, setPasajeroId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cContactoEntity, cClientes, cPasajeros, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-contacto' + props.location.search);
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
        ...cContactoEntity,
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
          <h2 id="tordaviApp.cContacto.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cContacto.home.createOrEditLabel">Create or edit a CContacto</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cContactoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-contacto-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-contacto-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nombreContactoLabel" for="c-contacto-nombreContacto">
                  <Translate contentKey="tordaviApp.cContacto.nombreContacto">Nombre Contacto</Translate>
                </Label>
                <AvField
                  id="c-contacto-nombreContacto"
                  type="text"
                  name="nombreContacto"
                  validate={{
                    maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="telefonoLabel" for="c-contacto-telefono">
                  <Translate contentKey="tordaviApp.cContacto.telefono">Telefono</Translate>
                </Label>
                <AvField
                  id="c-contacto-telefono"
                  type="text"
                  name="telefono"
                  validate={{
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="areaLabel" for="c-contacto-area">
                  <Translate contentKey="tordaviApp.cContacto.area">Area</Translate>
                </Label>
                <AvField
                  id="c-contacto-area"
                  type="text"
                  name="area"
                  validate={{
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-contacto-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cContacto.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-contacto-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-contacto-fechaCreacion">
                  <Translate contentKey="tordaviApp.cContacto.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-contacto-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cContactoEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-contacto-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cContacto.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-contacto-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-contacto-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cContacto.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-contacto-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cContactoEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-contacto-notas">
                  <Translate contentKey="tordaviApp.cContacto.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-contacto-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-contacto-estatus">
                  <Translate contentKey="tordaviApp.cContacto.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-contacto-estatus"
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
                <Label id="borradoLabel" for="c-contacto-borrado">
                  <Translate contentKey="tordaviApp.cContacto.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-contacto-borrado"
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
                <Label for="c-contacto-cliente">
                  <Translate contentKey="tordaviApp.cContacto.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-contacto-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-contacto-pasajero">
                  <Translate contentKey="tordaviApp.cContacto.pasajero">Pasajero</Translate>
                </Label>
                <AvInput id="c-contacto-pasajero" type="select" className="form-control" name="pasajeroId">
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
              <Button tag={Link} id="cancel-save" to="/c-contacto" replace color="info">
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
  cContactoEntity: storeState.cContacto.entity,
  loading: storeState.cContacto.loading,
  updating: storeState.cContacto.updating,
  updateSuccess: storeState.cContacto.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(CContactoUpdate);
