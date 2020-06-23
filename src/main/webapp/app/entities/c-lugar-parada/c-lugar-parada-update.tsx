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
import { getEntity, updateEntity, createEntity, reset } from './c-lugar-parada.reducer';
import { ICLugarParada } from 'app/shared/model/c-lugar-parada.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICLugarParadaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CLugarParadaUpdate = (props: ICLugarParadaUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cLugarParadaEntity, cClientes, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-lugar-parada' + props.location.search);
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
        ...cLugarParadaEntity,
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
          <h2 id="tordaviApp.cLugarParada.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cLugarParada.home.createOrEditLabel">Create or edit a CLugarParada</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cLugarParadaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-lugar-parada-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-lugar-parada-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="claveLugarParadaLabel" for="c-lugar-parada-claveLugarParada">
                  <Translate contentKey="tordaviApp.cLugarParada.claveLugarParada">Clave Lugar Parada</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-claveLugarParada"
                  type="text"
                  name="claveLugarParada"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="comunidadLabel" for="c-lugar-parada-comunidad">
                  <Translate contentKey="tordaviApp.cLugarParada.comunidad">Comunidad</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-comunidad"
                  type="text"
                  name="comunidad"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="ciudadLabel" for="c-lugar-parada-ciudad">
                  <Translate contentKey="tordaviApp.cLugarParada.ciudad">Ciudad</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-ciudad"
                  type="text"
                  name="ciudad"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estadoLabel" for="c-lugar-parada-estado">
                  <Translate contentKey="tordaviApp.cLugarParada.estado">Estado</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-estado"
                  type="text"
                  name="estado"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="paisLabel" for="c-lugar-parada-pais">
                  <Translate contentKey="tordaviApp.cLugarParada.pais">Pais</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-pais"
                  type="text"
                  name="pais"
                  validate={{
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-lugar-parada-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cLugarParada.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-lugar-parada-fechaCreacion">
                  <Translate contentKey="tordaviApp.cLugarParada.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-lugar-parada-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cLugarParadaEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-lugar-parada-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cLugarParada.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-lugar-parada-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cLugarParada.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-lugar-parada-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cLugarParadaEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-lugar-parada-notas">
                  <Translate contentKey="tordaviApp.cLugarParada.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-lugar-parada-estatus">
                  <Translate contentKey="tordaviApp.cLugarParada.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-estatus"
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
                <Label id="borradoLabel" for="c-lugar-parada-borrado">
                  <Translate contentKey="tordaviApp.cLugarParada.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-lugar-parada-borrado"
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
                <Label for="c-lugar-parada-cliente">
                  <Translate contentKey="tordaviApp.cLugarParada.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-lugar-parada-cliente" type="select" className="form-control" name="clienteId">
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
              <Button tag={Link} id="cancel-save" to="/c-lugar-parada" replace color="info">
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
  cLugarParadaEntity: storeState.cLugarParada.entity,
  loading: storeState.cLugarParada.loading,
  updating: storeState.cLugarParada.updating,
  updateSuccess: storeState.cLugarParada.updateSuccess
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

export default connect(mapStateToProps, mapDispatchToProps)(CLugarParadaUpdate);
