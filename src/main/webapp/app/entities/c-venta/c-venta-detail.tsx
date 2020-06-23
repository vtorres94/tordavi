import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-venta.reducer';
import { ICVenta } from 'app/shared/model/c-venta.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICVentaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CVentaDetail = (props: ICVentaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cVentaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cVenta.detail.title">CVenta</Translate> [<b>{cVentaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="vendedorId">
              <Translate contentKey="tordaviApp.cVenta.vendedorId">Vendedor Id</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.vendedorId}</dd>
          <dt>
            <span id="precioTotal">
              <Translate contentKey="tordaviApp.cVenta.precioTotal">Precio Total</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.precioTotal}</dd>
          <dt>
            <span id="fechaVenta">
              <Translate contentKey="tordaviApp.cVenta.fechaVenta">Fecha Venta</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cVentaEntity.fechaVenta} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cVenta.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cVenta.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cVentaEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cVenta.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cVenta.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cVentaEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cVenta.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cVenta.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cVenta.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cVentaEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cVenta.cliente">Cliente</Translate>
          </dt>
          <dd>{cVentaEntity.clienteCliente ? cVentaEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cVenta.reservacion">Reservacion</Translate>
          </dt>
          <dd>{cVentaEntity.reservacionClaveReservacion ? cVentaEntity.reservacionClaveReservacion : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-venta" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-venta/${cVentaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cVenta }: IRootState) => ({
  cVentaEntity: cVenta.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CVentaDetail);
