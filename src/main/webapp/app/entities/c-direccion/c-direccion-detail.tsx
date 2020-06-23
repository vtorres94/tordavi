import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-direccion.reducer';
import { ICDireccion } from 'app/shared/model/c-direccion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICDireccionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDireccionDetail = (props: ICDireccionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cDireccionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cDireccion.detail.title">CDireccion</Translate> [<b>{cDireccionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="direccionComplete">
              <Translate contentKey="tordaviApp.cDireccion.direccionComplete">Direccion Complete</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.direccionComplete}</dd>
          <dt>
            <span id="direccion">
              <Translate contentKey="tordaviApp.cDireccion.direccion">Direccion</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.direccion}</dd>
          <dt>
            <span id="numExterior">
              <Translate contentKey="tordaviApp.cDireccion.numExterior">Num Exterior</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.numExterior}</dd>
          <dt>
            <span id="numInterior">
              <Translate contentKey="tordaviApp.cDireccion.numInterior">Num Interior</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.numInterior}</dd>
          <dt>
            <span id="codigoPostal">
              <Translate contentKey="tordaviApp.cDireccion.codigoPostal">Codigo Postal</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.codigoPostal}</dd>
          <dt>
            <span id="ciudad">
              <Translate contentKey="tordaviApp.cDireccion.ciudad">Ciudad</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.ciudad}</dd>
          <dt>
            <span id="estado">
              <Translate contentKey="tordaviApp.cDireccion.estado">Estado</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.estado}</dd>
          <dt>
            <span id="pais">
              <Translate contentKey="tordaviApp.cDireccion.pais">Pais</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.pais}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cDireccion.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cDireccion.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDireccionEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cDireccion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cDireccion.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDireccionEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cDireccion.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cDireccion.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cDireccion.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cDireccionEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDireccion.cliente">Cliente</Translate>
          </dt>
          <dd>{cDireccionEntity.clienteCliente ? cDireccionEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDireccion.pasajero">Pasajero</Translate>
          </dt>
          <dd>{cDireccionEntity.pasajeroNombreCompleto ? cDireccionEntity.pasajeroNombreCompleto : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-direccion" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-direccion/${cDireccionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cDireccion }: IRootState) => ({
  cDireccionEntity: cDireccion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDireccionDetail);
