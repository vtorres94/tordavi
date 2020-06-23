import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-lugar-parada.reducer';
import { ICLugarParada } from 'app/shared/model/c-lugar-parada.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICLugarParadaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CLugarParadaDetail = (props: ICLugarParadaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cLugarParadaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cLugarParada.detail.title">CLugarParada</Translate> [<b>{cLugarParadaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="claveLugarParada">
              <Translate contentKey="tordaviApp.cLugarParada.claveLugarParada">Clave Lugar Parada</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.claveLugarParada}</dd>
          <dt>
            <span id="comunidad">
              <Translate contentKey="tordaviApp.cLugarParada.comunidad">Comunidad</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.comunidad}</dd>
          <dt>
            <span id="ciudad">
              <Translate contentKey="tordaviApp.cLugarParada.ciudad">Ciudad</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.ciudad}</dd>
          <dt>
            <span id="estado">
              <Translate contentKey="tordaviApp.cLugarParada.estado">Estado</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.estado}</dd>
          <dt>
            <span id="pais">
              <Translate contentKey="tordaviApp.cLugarParada.pais">Pais</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.pais}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cLugarParada.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cLugarParada.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cLugarParadaEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cLugarParada.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cLugarParada.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cLugarParadaEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cLugarParada.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cLugarParada.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cLugarParada.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cLugarParadaEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cLugarParada.cliente">Cliente</Translate>
          </dt>
          <dd>{cLugarParadaEntity.clienteCliente ? cLugarParadaEntity.clienteCliente : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-lugar-parada" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-lugar-parada/${cLugarParadaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cLugarParada }: IRootState) => ({
  cLugarParadaEntity: cLugarParada.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CLugarParadaDetail);
