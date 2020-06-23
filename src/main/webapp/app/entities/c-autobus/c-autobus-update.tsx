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
import { getEntity, updateEntity, createEntity, reset } from './c-autobus.reducer';
import { ICAutobus } from 'app/shared/model/c-autobus.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICAutobusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CAutobusUpdate = (props: ICAutobusUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cAutobusEntity, cClientes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-autobus' + props.location.search);
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
        ...cAutobusEntity,
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
          <h2 id="tordaviApp.cAutobus.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cAutobus.home.createOrEditLabel">Create or edit a CAutobus</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cAutobusEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-autobus-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-autobus-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="autobusLabel" for="c-autobus-autobus">
                  <Translate contentKey="tordaviApp.cAutobus.autobus">Autobus</Translate>
                </Label>
                <AvField
                  id="c-autobus-autobus"
                  type="text"
                  name="autobus"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-autobus-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cAutobus.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-autobus-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-autobus-fechaCreacion">
                  <Translate contentKey="tordaviApp.cAutobus.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-autobus-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cAutobusEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-autobus-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cAutobus.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-autobus-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-autobus-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cAutobus.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-autobus-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cAutobusEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-autobus-notas">
                  <Translate contentKey="tordaviApp.cAutobus.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-autobus-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-autobus-estatus">
                  <Translate contentKey="tordaviApp.cAutobus.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-autobus-estatus"
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
                <Label id="borradoLabel" for="c-autobus-borrado">
                  <Translate contentKey="tordaviApp.cAutobus.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-autobus-borrado"
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
                <Label for="c-autobus-cliente">
                  <Translate contentKey="tordaviApp.cAutobus.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-autobus-cliente" type="select" className="form-control" name="clienteId">
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
              <Button tag={Link} id="cancel-save" to="/c-autobus" replace color="info">
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
  cAutobusEntity: storeState.cAutobus.entity,
  loading: storeState.cAutobus.loading,
  updating: storeState.cAutobus.updating,
  updateSuccess: storeState.cAutobus.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(CAutobusUpdate);
