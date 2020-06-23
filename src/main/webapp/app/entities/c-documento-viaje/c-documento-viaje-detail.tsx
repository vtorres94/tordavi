import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-documento-viaje.reducer';
import { ICDocumentoViaje } from 'app/shared/model/c-documento-viaje.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICDocumentoViajeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CDocumentoViajeDetail = (props: ICDocumentoViajeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cDocumentoViajeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cDocumentoViaje.detail.title">CDocumentoViaje</Translate> [<b>{cDocumentoViajeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="claveDocumento">
              <Translate contentKey="tordaviApp.cDocumentoViaje.claveDocumento">Clave Documento</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.claveDocumento}</dd>
          <dt>
            <span id="documento">
              <Translate contentKey="tordaviApp.cDocumentoViaje.documento">Documento</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.documento}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cDocumentoViaje.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDocumentoViajeEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cDocumentoViaje.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cDocumentoViaje.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cDocumentoViajeEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cDocumentoViaje.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cDocumentoViaje.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cDocumentoViaje.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cDocumentoViajeEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDocumentoViaje.cliente">Cliente</Translate>
          </dt>
          <dd>{cDocumentoViajeEntity.clienteCliente ? cDocumentoViajeEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cDocumentoViaje.pasajero">Pasajero</Translate>
          </dt>
          <dd>{cDocumentoViajeEntity.pasajeroNombreCompleto ? cDocumentoViajeEntity.pasajeroNombreCompleto : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-documento-viaje" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-documento-viaje/${cDocumentoViajeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cDocumentoViaje }: IRootState) => ({
  cDocumentoViajeEntity: cDocumentoViaje.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CDocumentoViajeDetail);
