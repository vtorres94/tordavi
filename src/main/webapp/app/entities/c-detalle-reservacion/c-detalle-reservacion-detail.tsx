import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-detalle-reservacion.reducer';
import { ICDetalleReservacion } from 'app/shared/model/c-detalle-reservacion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICDetalleReservacionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDetalleReservacionDetail = (props: ICDetalleReservacionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cDetalleReservacionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cDetalleReservacion.detail.title">CDetalleReservacion</Translate> [
          <b>{cDetalleReservacionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="precio">
              <Translate contentKey="tordaviApp.cDetalleReservacion.precio">Precio</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.precio}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cDetalleReservacion.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cDetalleReservacion.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDetalleReservacionEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cDetalleReservacion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cDetalleReservacion.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDetalleReservacionEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cDetalleReservacion.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cDetalleReservacion.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cDetalleReservacion.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cDetalleReservacionEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDetalleReservacion.cliente">Cliente</Translate>
          </dt>
          <dd>{cDetalleReservacionEntity.clienteCliente ? cDetalleReservacionEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDetalleReservacion.pasajero">Pasajero</Translate>
          </dt>
          <dd>{cDetalleReservacionEntity.pasajeroNombreCompleto ? cDetalleReservacionEntity.pasajeroNombreCompleto : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDetalleReservacion.reservacion">Reservacion</Translate>
          </dt>
          <dd>{cDetalleReservacionEntity.reservacionClaveReservacion ? cDetalleReservacionEntity.reservacionClaveReservacion : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-detalle-reservacion" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-detalle-reservacion/${cDetalleReservacionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cDetalleReservacion }: IRootState) => ({
  cDetalleReservacionEntity: cDetalleReservacion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDetalleReservacionDetail);
