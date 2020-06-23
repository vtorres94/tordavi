import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-cliente.reducer';
import { ICCliente } from 'app/shared/model/c-cliente.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICClienteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CClienteDetail = (props: ICClienteDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cClienteEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cCliente.detail.title">CCliente</Translate> [<b>{cClienteEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="claveCliente">
              <Translate contentKey="tordaviApp.cCliente.claveCliente">Clave Cliente</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.claveCliente}</dd>
          <dt>
            <span id="cliente">
              <Translate contentKey="tordaviApp.cCliente.cliente">Cliente</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.cliente}</dd>
          <dt>
            <span id="logo">
              <Translate contentKey="tordaviApp.cCliente.logo">Logo</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.logo}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cCliente.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cCliente.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cClienteEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cCliente.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cCliente.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cClienteEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cCliente.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cCliente.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cCliente.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cClienteEntity.borrado}</dd>
        </dl>
        <Button tag={Link} to="/c-cliente" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-cliente/${cClienteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cCliente }: IRootState) => ({
  cClienteEntity: cCliente.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CClienteDetail);
