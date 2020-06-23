import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-reservacion.reducer';
import { ICReservacion } from 'app/shared/model/c-reservacion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICReservacionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CReservacionDetail = (props: ICReservacionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cReservacionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cReservacion.detail.title">CReservacion</Translate> [<b>{cReservacionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="claveReservacion">
              <Translate contentKey="tordaviApp.cReservacion.claveReservacion">Clave Reservacion</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.claveReservacion}</dd>
          <dt>
            <span id="precio">
              <Translate contentKey="tordaviApp.cReservacion.precio">Precio</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.precio}</dd>
          <dt>
            <span id="numPasajeros">
              <Translate contentKey="tordaviApp.cReservacion.numPasajeros">Num Pasajeros</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.numPasajeros}</dd>
          <dt>
            <span id="comentarios">
              <Translate contentKey="tordaviApp.cReservacion.comentarios">Comentarios</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.comentarios}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cReservacion.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cReservacion.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cReservacionEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cReservacion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cReservacion.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cReservacionEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cReservacion.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cReservacion.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cReservacion.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cReservacionEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cReservacion.cliente">Cliente</Translate>
          </dt>
          <dd>{cReservacionEntity.clienteCliente ? cReservacionEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cReservacion.pasajeroPrincipal">Pasajero Principal</Translate>
          </dt>
          <dd>{cReservacionEntity.pasajeroPrincipalNombreCompleto ? cReservacionEntity.pasajeroPrincipalNombreCompleto : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cReservacion.corrida">Corrida</Translate>
          </dt>
          <dd>{cReservacionEntity.corridaClaveCorrida ? cReservacionEntity.corridaClaveCorrida : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-reservacion" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-reservacion/${cReservacionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cReservacion }: IRootState) => ({
  cReservacionEntity: cReservacion.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CReservacionDetail);
