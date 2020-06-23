import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './c-cliente.reducer';
import { ICCliente } from 'app/shared/model/c-cliente.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICClienteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CClienteUpdate = (props: ICClienteUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cClienteEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-cliente' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
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
        ...cClienteEntity,
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
          <h2 id="tordaviApp.cCliente.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cCliente.home.createOrEditLabel">Create or edit a CCliente</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cClienteEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-cliente-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-cliente-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="claveClienteLabel" for="c-cliente-claveCliente">
                  <Translate contentKey="tordaviApp.cCliente.claveCliente">Clave Cliente</Translate>
                </Label>
                <AvField
                  id="c-cliente-claveCliente"
                  type="text"
                  name="claveCliente"
                  validate={{
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="clienteLabel" for="c-cliente-cliente">
                  <Translate contentKey="tordaviApp.cCliente.cliente">Cliente</Translate>
                </Label>
                <AvField
                  id="c-cliente-cliente"
                  type="text"
                  name="cliente"
                  validate={{
                    maxLength: { value: 60, errorMessage: translate('entity.validation.maxlength', { max: 60 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="logoLabel" for="c-cliente-logo">
                  <Translate contentKey="tordaviApp.cCliente.logo">Logo</Translate>
                </Label>
                <AvField id="c-cliente-logo" type="text" name="logo" />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-cliente-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cCliente.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-cliente-idUsuarioCreacion"
                  type="string"
                  className="form-control"
                  name="idUsuarioCreacion"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 9999999999, errorMessage: translate('entity.validation.max', { max: 9999999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaCreacionLabel" for="c-cliente-fechaCreacion">
                  <Translate contentKey="tordaviApp.cCliente.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-cliente-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cClienteEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-cliente-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cCliente.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-cliente-idUsuarioActualizacion"
                  type="string"
                  className="form-control"
                  name="idUsuarioActualizacion"
                  validate={{
                    max: { value: 9999999999, errorMessage: translate('entity.validation.max', { max: 9999999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fechaActualizacionLabel" for="c-cliente-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cCliente.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-cliente-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cClienteEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-cliente-notas">
                  <Translate contentKey="tordaviApp.cCliente.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-cliente-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-cliente-estatus">
                  <Translate contentKey="tordaviApp.cCliente.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-cliente-estatus"
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
                <Label id="borradoLabel" for="c-cliente-borrado">
                  <Translate contentKey="tordaviApp.cCliente.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-cliente-borrado"
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
              <Button tag={Link} id="cancel-save" to="/c-cliente" replace color="info">
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
  cClienteEntity: storeState.cCliente.entity,
  loading: storeState.cCliente.loading,
  updating: storeState.cCliente.updating,
  updateSuccess: storeState.cCliente.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CClienteUpdate);
