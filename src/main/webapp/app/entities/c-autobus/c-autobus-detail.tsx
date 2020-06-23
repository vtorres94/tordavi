import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-autobus.reducer';
import { ICAutobus } from 'app/shared/model/c-autobus.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICAutobusDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CAutobusDetail = (props: ICAutobusDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cAutobusEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cAutobus.detail.title">CAutobus</Translate> [<b>{cAutobusEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="autobus">
              <Translate contentKey="tordaviApp.cAutobus.autobus">Autobus</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.autobus}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cAutobus.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cAutobus.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cAutobusEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cAutobus.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cAutobus.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cAutobusEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cAutobus.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cAutobus.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cAutobus.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cAutobusEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cAutobus.cliente">Cliente</Translate>
          </dt>
          <dd>{cAutobusEntity.clienteCliente ? cAutobusEntity.clienteCliente : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-autobus" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-autobus/${cAutobusEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cAutobus }: IRootState) => ({
  cAutobusEntity: cAutobus.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CAutobusDetail);
