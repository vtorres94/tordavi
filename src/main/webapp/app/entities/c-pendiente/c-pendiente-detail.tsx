import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-pendiente.reducer';
import { ICPendiente } from 'app/shared/model/c-pendiente.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICPendienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CPendienteDetail = (props: ICPendienteDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cPendienteEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cPendiente.detail.title">CPendiente</Translate> [<b>{cPendienteEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="comentarios">
              <Translate contentKey="tordaviApp.cPendiente.comentarios">Comentarios</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.comentarios}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cPendiente.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cPendiente.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cPendienteEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cPendiente.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cPendiente.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cPendienteEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cPendiente.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cPendiente.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cPendiente.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cPendienteEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cPendiente.cliente">Cliente</Translate>
          </dt>
          <dd>{cPendienteEntity.clienteCliente ? cPendienteEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cPendiente.reservacion">Reservacion</Translate>
          </dt>
          <dd>{cPendienteEntity.reservacionClaveReservacion ? cPendienteEntity.reservacionClaveReservacion : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-pendiente" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-pendiente/${cPendienteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cPendiente }: IRootState) => ({
  cPendienteEntity: cPendiente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CPendienteDetail);
