import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-contacto.reducer';
import { ICContacto } from 'app/shared/model/c-contacto.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICContactoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CContactoDetail = (props: ICContactoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cContactoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cContacto.detail.title">CContacto</Translate> [<b>{cContactoEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="nombreContacto">
              <Translate contentKey="tordaviApp.cContacto.nombreContacto">Nombre Contacto</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.nombreContacto}</dd>
          <dt>
            <span id="telefono">
              <Translate contentKey="tordaviApp.cContacto.telefono">Telefono</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.telefono}</dd>
          <dt>
            <span id="area">
              <Translate contentKey="tordaviApp.cContacto.area">Area</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.area}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cContacto.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cContacto.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cContactoEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cContacto.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cContacto.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cContactoEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cContacto.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cContacto.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cContacto.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cContactoEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cContacto.cliente">Cliente</Translate>
          </dt>
          <dd>{cContactoEntity.clienteCliente ? cContactoEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cContacto.pasajero">Pasajero</Translate>
          </dt>
          <dd>{cContactoEntity.pasajeroNombreCompleto ? cContactoEntity.pasajeroNombreCompleto : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-contacto" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-contacto/${cContactoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cContacto }: IRootState) => ({
  cContactoEntity: cContacto.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CContactoDetail);
