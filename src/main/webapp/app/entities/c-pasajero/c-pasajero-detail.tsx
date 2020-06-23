import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-pasajero.reducer';
import { ICPasajero } from 'app/shared/model/c-pasajero.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICPasajeroDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CPasajeroDetail = (props: ICPasajeroDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cPasajeroEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cPasajero.detail.title">CPasajero</Translate> [<b>{cPasajeroEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nombreCompleto">
              <Translate contentKey="tordaviApp.cPasajero.nombreCompleto">Nombre Completo</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.nombreCompleto}</dd>
          <dt>
            <span id="nombre">
              <Translate contentKey="tordaviApp.cPasajero.nombre">Nombre</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.nombre}</dd>
          <dt>
            <span id="segundoNombre">
              <Translate contentKey="tordaviApp.cPasajero.segundoNombre">Segundo Nombre</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.segundoNombre}</dd>
          <dt>
            <span id="apellido">
              <Translate contentKey="tordaviApp.cPasajero.apellido">Apellido</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.apellido}</dd>
          <dt>
            <span id="segundoApellido">
              <Translate contentKey="tordaviApp.cPasajero.segundoApellido">Segundo Apellido</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.segundoApellido}</dd>
          <dt>
            <span id="edad">
              <Translate contentKey="tordaviApp.cPasajero.edad">Edad</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.edad}</dd>
          <dt>
            <span id="curp">
              <Translate contentKey="tordaviApp.cPasajero.curp">Curp</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.curp}</dd>
          <dt>
            <span id="ciudadania">
              <Translate contentKey="tordaviApp.cPasajero.ciudadania">Ciudadania</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.ciudadania}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cPasajero.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cPasajero.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cPasajeroEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cPasajero.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cPasajero.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cPasajeroEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cPasajero.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cPasajero.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cPasajero.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cPasajeroEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cPasajero.cliente">Cliente</Translate>
          </dt>
          <dd>{cPasajeroEntity.clienteCliente ? cPasajeroEntity.clienteCliente : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-pasajero" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-pasajero/${cPasajeroEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cPasajero }: IRootState) => ({
  cPasajeroEntity: cPasajero.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CPasajeroDetail);
