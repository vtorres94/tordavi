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
import { ICAutobus } from 'app/shared/model/c-autobus.model';
import { getEntities as getCAutobuses } from 'app/entities/c-autobus/c-autobus.reducer';
import { ICLugarParada } from 'app/shared/model/c-lugar-parada.model';
import { getEntities as getCLugarParadas } from 'app/entities/c-lugar-parada/c-lugar-parada.reducer';
import { getEntity, updateEntity, createEntity, reset } from './c-corrida.reducer';
import { ICCorrida } from 'app/shared/model/c-corrida.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICCorridaUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CCorridaUpdate = (props: ICCorridaUpdateProps) => {
  const [clienteId, setClienteId] = useState('0');
  const [autobusId, setAutobusId] = useState('0');
  const [lugarSalidaId, setLugarSalidaId] = useState('0');
  const [lugarLlegadaId, setLugarLlegadaId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { cCorridaEntity, cClientes, cAutobuses, cLugarParadas, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/c-corrida' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getCClientes();
    props.getCAutobuses();
    props.getCLugarParadas();
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
        ...cCorridaEntity,
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
          <h2 id="tordaviApp.cCorrida.home.createOrEditLabel">
            <Translate contentKey="tordaviApp.cCorrida.home.createOrEditLabel">Create or edit a CCorrida</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : cCorridaEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="c-corrida-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="c-corrida-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="claveCorridaLabel" for="c-corrida-claveCorrida">
                  <Translate contentKey="tordaviApp.cCorrida.claveCorrida">Clave Corrida</Translate>
                </Label>
                <AvField
                  id="c-corrida-claveCorrida"
                  type="text"
                  name="claveCorrida"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="horaSalidaLabel" for="c-corrida-horaSalida">
                  <Translate contentKey="tordaviApp.cCorrida.horaSalida">Hora Salida</Translate>
                </Label>
                <AvField
                  id="c-corrida-horaSalida"
                  type="text"
                  name="horaSalida"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 5, errorMessage: translate('entity.validation.maxlength', { max: 5 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="horaLllegadaLabel" for="c-corrida-horaLllegada">
                  <Translate contentKey="tordaviApp.cCorrida.horaLllegada">Hora Lllegada</Translate>
                </Label>
                <AvField
                  id="c-corrida-horaLllegada"
                  type="text"
                  name="horaLllegada"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 5, errorMessage: translate('entity.validation.maxlength', { max: 5 }) }
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="conexionLabel">
                  <AvInput id="c-corrida-conexion" type="checkbox" className="form-check-input" name="conexion" />
                  <Translate contentKey="tordaviApp.cCorrida.conexion">Conexion</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="lugarConexionLabel" for="c-corrida-lugarConexion">
                  <Translate contentKey="tordaviApp.cCorrida.lugarConexion">Lugar Conexion</Translate>
                </Label>
                <AvField
                  id="c-corrida-lugarConexion"
                  type="text"
                  name="lugarConexion"
                  validate={{
                    maxLength: { value: 30, errorMessage: translate('entity.validation.maxlength', { max: 30 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="diasSalidaLabel" for="c-corrida-diasSalida">
                  <Translate contentKey="tordaviApp.cCorrida.diasSalida">Dias Salida</Translate>
                </Label>
                <AvField
                  id="c-corrida-diasSalida"
                  type="text"
                  name="diasSalida"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="comentariosLabel" for="c-corrida-comentarios">
                  <Translate contentKey="tordaviApp.cCorrida.comentarios">Comentarios</Translate>
                </Label>
                <AvField
                  id="c-corrida-comentarios"
                  type="text"
                  name="comentarios"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioCreacionLabel" for="c-corrida-idUsuarioCreacion">
                  <Translate contentKey="tordaviApp.cCorrida.idUsuarioCreacion">Id Usuario Creacion</Translate>
                </Label>
                <AvField
                  id="c-corrida-idUsuarioCreacion"
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
                <Label id="fechaCreacionLabel" for="c-corrida-fechaCreacion">
                  <Translate contentKey="tordaviApp.cCorrida.fechaCreacion">Fecha Creacion</Translate>
                </Label>
                <AvInput
                  id="c-corrida-fechaCreacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaCreacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cCorridaEntity.fechaCreacion)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="idUsuarioActualizacionLabel" for="c-corrida-idUsuarioActualizacion">
                  <Translate contentKey="tordaviApp.cCorrida.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
                </Label>
                <AvField
                  id="c-corrida-idUsuarioActualizacion"
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
                <Label id="fechaActualizacionLabel" for="c-corrida-fechaActualizacion">
                  <Translate contentKey="tordaviApp.cCorrida.fechaActualizacion">Fecha Actualizacion</Translate>
                </Label>
                <AvInput
                  id="c-corrida-fechaActualizacion"
                  type="datetime-local"
                  className="form-control"
                  name="fechaActualizacion"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.cCorridaEntity.fechaActualizacion)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="notasLabel" for="c-corrida-notas">
                  <Translate contentKey="tordaviApp.cCorrida.notas">Notas</Translate>
                </Label>
                <AvField
                  id="c-corrida-notas"
                  type="text"
                  name="notas"
                  validate={{
                    maxLength: { value: 300, errorMessage: translate('entity.validation.maxlength', { max: 300 }) }
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="estatusLabel" for="c-corrida-estatus">
                  <Translate contentKey="tordaviApp.cCorrida.estatus">Estatus</Translate>
                </Label>
                <AvField
                  id="c-corrida-estatus"
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
                <Label id="borradoLabel" for="c-corrida-borrado">
                  <Translate contentKey="tordaviApp.cCorrida.borrado">Borrado</Translate>
                </Label>
                <AvField
                  id="c-corrida-borrado"
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
                <Label for="c-corrida-cliente">
                  <Translate contentKey="tordaviApp.cCorrida.cliente">Cliente</Translate>
                </Label>
                <AvInput id="c-corrida-cliente" type="select" className="form-control" name="clienteId">
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
                <Label for="c-corrida-autobus">
                  <Translate contentKey="tordaviApp.cCorrida.autobus">Autobus</Translate>
                </Label>
                <AvInput id="c-corrida-autobus" type="select" className="form-control" name="autobusId">
                  <option value="" key="0" />
                  {cAutobuses
                    ? cAutobuses.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.autobus}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="c-corrida-lugarSalida">
                  <Translate contentKey="tordaviApp.cCorrida.lugarSalida">Lugar Salida</Translate>
                </Label>
                <AvInput id="c-corrida-lugarSalida" type="select" className="form-control" name="lugarSalidaId">
                  <option value="" key="0" />
                  {cLugarParadas
                    ? cLugarParadas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.claveLugarParada}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="c-corrida-lugarLlegada">
                  <Translate contentKey="tordaviApp.cCorrida.lugarLlegada">Lugar Llegada</Translate>
                </Label>
                <AvInput id="c-corrida-lugarLlegada" type="select" className="form-control" name="lugarLlegadaId">
                  <option value="" key="0" />
                  {cLugarParadas
                    ? cLugarParadas.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.claveLugarParada}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/c-corrida" replace color="info">
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
  cAutobuses: storeState.cAutobus.entities,
  cLugarParadas: storeState.cLugarParada.entities,
  cCorridaEntity: storeState.cCorrida.entity,
  loading: storeState.cCorrida.loading,
  updating: storeState.cCorrida.updating,
  updateSuccess: storeState.cCorrida.updateSuccess
});

const mapDispatchToProps = {
  getCClientes,
  getCAutobuses,
  getCLugarParadas,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CCorridaUpdate);
