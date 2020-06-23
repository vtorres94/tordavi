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
import { getEntity, updateEntity, createEntity, reset } from './c-direccion.reducer';
import { ICDireccion } from 'app/shared/model/c-direccion.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICDireccionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDireccionUpdate = (props: ICDireccionUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [pasajeroId, setPasajeroId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cDireccionEntity, cClientes, cPasajeros, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-direccion' + props.location.search);
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
        ...cDireccionEntity,
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
          <h2 id="tordaviApp.cDireccion.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cDireccion.home.createOrEditLabel">Create or edit a CDireccion</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cDireccionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-direccion-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-direccion-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="direccionCompleteLabel" for="c-direccion-direccionComplete">
                  <Translate contentKey="tordaviApp.cDireccion.direccionComplete">Direccion Complete</Translate>
                </Label>
                <AvField
                  id="c-direccion-direccionComplete"
                  type="text"
                  name="direccionComplete"
                  validate={{
                    maxLength: { value: 120, errorMessage: translate('entity.validation.maxlength', { max: 120 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="direccionLabel" for="c-direccion-direccion">
                  <Translate contentKey="tordaviApp.cDireccion.direccion">Direccion</Translate>
                </Label>
                <AvField
                  id="c-direccion-direccion"
                  type="text"
                  name="direccion"
                  validate={{
                    maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="numExteriorLabel" for="c-direccion-numExterior">
                  <Translate contentKey="tordaviApp.cDireccion.numExterior">Num Exterior</Translate>
                </Label>
                <AvField
                  id="c-direccion-numExterior"
                  type="text"
                  name="numExterior"
                  validate={{
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="numInteriorLabel" for="c-direccion-numInterior">
                  <Translate contentKey="tordaviApp.cDireccion.numInterior">Num Interior</Translate>
                </Label>
                <AvField
                  id="c-direccion-numInterior"
                  type="text"
                  name="numInterior"
                  validate={{
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="codigoPostalLabel" for="c-direccion-codigoPostal">
                  <Translate contentKey="tordaviApp.cDireccion.codigoPostal">Codigo Postal</Translate>
                </Label>
                <AvField
                  id="c-direccion-codigoPostal"
                  type="string"
                  className="form-control"
                  name="codigoPostal"
                  validate={{
                    max: { value: 99999999, errorMessage: translate('entity.validation.max', { max: 99999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ciudadLabel" for="c-direccion-ciudad">
                  <Translate contentKey="tordaviApp.cDireccion.ciudad">Ciudad</Translate>
                </Label>
                <AvField
                  id="c-direccion-ciudad"
                  type="text"
                  name="ciudad"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estadoLabel" for="c-direccion-estado">
                  <Translate contentKey="tordaviApp.cDireccion.estado">Estado</Translate>
                </Label>
                <AvField
                  id="c-direccion-estado"
                  type="text"
                  name="estado"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="paisLabel" for="c-direccion-pais">
                  <Translate contentKey="tordaviApp.cDireccion.pais">Pais</Translate>
                </Label>
                <AvField
                  id="c-direccion-pais"
                  type="text"
                  name="pais"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-direccion-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cDireccion.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-direccion-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-direccion-fechaCreacion">
                  <Translate contentKey="tordaviApp.cDireccion.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-direccion-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDireccionEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-direccion-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cDireccion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-direccion-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-direccion-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cDireccion.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-direccion-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cDireccionEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-direccion-notas">
                  <Translate contentKey="tordaviApp.cDireccion.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-direccion-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-direccion-estatus">
                  <Translate contentKey="tordaviApp.cDireccion.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-direccion-estatus"
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
                <Label id="borradoLabel" for="c-direccion-borrado">
                  <Translate contentKey="tordaviApp.cDireccion.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-direccion-borrado"
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
                <Label for="c-direccion-cliente">
                  <Translate contentKey="tordaviApp.cDireccion.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-direccion-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-direccion-pasajero">
                  <Translate contentKey="tordaviApp.cDireccion.pasajero">Pasajero</Translate>
                </Label>
                <AvInput id="c-direccion-pasajero" type="select" className="form-control" name="pasajeroId">
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
              <Button tag={Link} id="cancel-save" to="/c-direccion" replace color="info">
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
  cDireccionEntity: storeState.cDireccion.entity,
  loading: storeState.cDireccion.loading,
  updating: storeState.cDireccion.updating,
  updateSuccess: storeState.cDireccion.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(CDireccionUpdate);
