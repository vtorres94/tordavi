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
import { ICReservacion } from 'app/shared/model/c-reservacion.model';
import { getEntities as getCReservacions } from 'app/entities/c-reservacion/c-reservacion.reducer';
import { getEntity, updateEntity, createEntity, reset } from './c-venta.reducer';
import { ICVenta } from 'app/shared/model/c-venta.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICVentaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CVentaUpdate = (props: ICVentaUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [reservacionId, setReservacionId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cVentaEntity, cClientes, cReservacions, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-venta' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
    props.getCReservacions();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.fechaVenta = convertDateTimeToServer(values.fechaVenta);
    values.fechaCreacion = convertDateTimeToServer(values.fechaCreacion);
    values.fechaActualizacion = convertDateTimeToServer(values.fechaActualizacion);

    if (errors.length === 0) {
      const entity = {
        ...cVentaEntity,
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
          <h2 id="tordaviApp.cVenta.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cVenta.home.createOrEditLabel">Create or edit a CVenta</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cVentaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-venta-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-venta-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="vendedorIdLabel" for="c-venta-vendedorId">
                  <Translate contentKey="tordaviApp.cVenta.vendedorId">Vendedor Id</Translate>
                </Label>
                <AvField
                  id="c-venta-vendedorId"
                  type="string"
                  className="form-control"
                  name="vendedorId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    max: { value: 999999999999, errorMessage: translate('entity.validation.max', { max: 999999999999 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="precioTotalLabel" for="c-venta-precioTotal">
                  <Translate contentKey="tordaviApp.cVenta.precioTotal">Precio Total</Translate>
                </Label>
                <AvField id="c-venta-precioTotal" type="string" className="form-control" name="precioTotal" />
              </AvGroup>
              <AvGroup>
                <Label id="fechaVentaLabel" for="c-venta-fechaVenta">
                  <Translate contentKey="tordaviApp.cVenta.fechaVenta">Fecha Venta</Translate>
                </Label>
                <AvInput
                  id="c-venta-fechaVenta"
                  type="datetime-local"
                  className="form-control"
                  name="fechaVenta"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cVentaEntity.fechaVenta)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-venta-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cVenta.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-venta-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-venta-fechaCreacion">
                  <Translate contentKey="tordaviApp.cVenta.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-venta-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cVentaEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-venta-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cVenta.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-venta-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-venta-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cVenta.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-venta-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cVentaEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-venta-notas">
                  <Translate contentKey="tordaviApp.cVenta.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-venta-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-venta-estatus">
                  <Translate contentKey="tordaviApp.cVenta.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-venta-estatus"
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
                <Label id="borradoLabel" for="c-venta-borrado">
                  <Translate contentKey="tordaviApp.cVenta.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-venta-borrado"
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
                <Label for="c-venta-cliente">
                  <Translate contentKey="tordaviApp.cVenta.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-venta-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-venta-reservacion">
                  <Translate contentKey="tordaviApp.cVenta.reservacion">Reservacion</Translate>
                </Label>
                <AvInput id="c-venta-reservacion" type="select" className="form-control" name="reservacionId">
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
              <Button tag={Link} id="cancel-save" to="/c-venta" replace color="info">
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
  cReservacions: storeState.cReservacion.entities,
  cVentaEntity: storeState.cVenta.entity,
  loading: storeState.cVenta.loading,
  updating: storeState.cVenta.updating,
  updateSuccess: storeState.cVenta.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getCReservacions,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CVentaUpdate);
