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
import { getEntity, updateEntity, createEntity, reset } from './c-pasajero.reducer';
import { ICPasajero } from 'app/shared/model/c-pasajero.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICPasajeroUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CPasajeroUpdate = (props: ICPasajeroUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cPasajeroEntity, cClientes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-pasajero' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
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
        ...cPasajeroEntity,
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
          <h2 id="tordaviApp.cPasajero.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cPasajero.home.createOrEditLabel">Create or edit a CPasajero</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cPasajeroEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-pasajero-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-pasajero-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nombreCompletoLabel" for="c-pasajero-nombreCompleto">
                  <Translate contentKey="tordaviApp.cPasajero.nombreCompleto">Nombre Completo</Translate>
                </Label>
                <AvField
                  id="c-pasajero-nombreCompleto"
                  type="text"
                  name="nombreCompleto"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nombreLabel" for="c-pasajero-nombre">
                  <Translate contentKey="tordaviApp.cPasajero.nombre">Nombre</Translate>
                </Label>
                <AvField
                  id="c-pasajero-nombre"
                  type="text"
                  name="nombre"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="segundoNombreLabel" for="c-pasajero-segundoNombre">
                  <Translate contentKey="tordaviApp.cPasajero.segundoNombre">Segundo Nombre</Translate>
                </Label>
                <AvField
                  id="c-pasajero-segundoNombre"
                  type="text"
                  name="segundoNombre"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="apellidoLabel" for="c-pasajero-apellido">
                  <Translate contentKey="tordaviApp.cPasajero.apellido">Apellido</Translate>
                </Label>
                <AvField
                  id="c-pasajero-apellido"
                  type="text"
                  name="apellido"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="segundoApellidoLabel" for="c-pasajero-segundoApellido">
                  <Translate contentKey="tordaviApp.cPasajero.segundoApellido">Segundo Apellido</Translate>
                </Label>
                <AvField
                  id="c-pasajero-segundoApellido"
                  type="text"
                  name="segundoApellido"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="edadLabel" for="c-pasajero-edad">
                  <Translate contentKey="tordaviApp.cPasajero.edad">Edad</Translate>
                </Label>
                <AvField
                  id="c-pasajero-edad"
                  type="string"
                  className="form-control"
                  name="edad"
                  validate={{
                    max: { value: 999, errorMessage: translate('entity.validation.max', { max: 999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="curpLabel" for="c-pasajero-curp">
                  <Translate contentKey="tordaviApp.cPasajero.curp">Curp</Translate>
                </Label>
                <AvField
                  id="c-pasajero-curp"
                  type="text"
                  name="curp"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ciudadaniaLabel" for="c-pasajero-ciudadania">
                  <Translate contentKey="tordaviApp.cPasajero.ciudadania">Ciudadania</Translate>
                </Label>
                <AvField
                  id="c-pasajero-ciudadania"
                  type="text"
                  name="ciudadania"
                  validate={{
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-pasajero-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cPasajero.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-pasajero-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-pasajero-fechaCreacion">
                  <Translate contentKey="tordaviApp.cPasajero.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-pasajero-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cPasajeroEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-pasajero-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cPasajero.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-pasajero-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-pasajero-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cPasajero.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-pasajero-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cPasajeroEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-pasajero-notas">
                  <Translate contentKey="tordaviApp.cPasajero.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-pasajero-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-pasajero-estatus">
                  <Translate contentKey="tordaviApp.cPasajero.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-pasajero-estatus"
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
                <Label id="borradoLabel" for="c-pasajero-borrado">
                  <Translate contentKey="tordaviApp.cPasajero.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-pasajero-borrado"
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
                <Label for="c-pasajero-cliente">
                  <Translate contentKey="tordaviApp.cPasajero.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-pasajero-cliente" type="select" className="form-control" name="clienteId">
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
              <Button tag={Link} id="cancel-save" to="/c-pasajero" replace color="info">
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
  cPasajeroEntity: storeState.cPasajero.entity,
  loading: storeState.cPasajero.loading,
  updating: storeState.cPasajero.updating,
  updateSuccess: storeState.cPasajero.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CPasajeroUpdate);
