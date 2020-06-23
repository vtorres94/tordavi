import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './c-corrida.reducer';
import { ICCorrida } from 'app/shared/model/c-corrida.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICCorridaDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CCorridaDetail = (props: ICCorridaDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { cCorridaEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="tordaviApp.cCorrida.detail.title">CCorrida</Translate> [<b>{cCorridaEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="claveCorrida">
              <Translate contentKey="tordaviApp.cCorrida.claveCorrida">Clave Corrida</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.claveCorrida}</dd>
          <dt>
            <span id="horaSalida">
              <Translate contentKey="tordaviApp.cCorrida.horaSalida">Hora Salida</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.horaSalida}</dd>
          <dt>
            <span id="horaLllegada">
              <Translate contentKey="tordaviApp.cCorrida.horaLllegada">Hora Lllegada</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.horaLllegada}</dd>
          <dt>
            <span id="conexion">
              <Translate contentKey="tordaviApp.cCorrida.conexion">Conexion</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.conexion ? 'true' : 'false'}</dd>
          <dt>
            <span id="lugarConexion">
              <Translate contentKey="tordaviApp.cCorrida.lugarConexion">Lugar Conexion</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.lugarConexion}</dd>
          <dt>
            <span id="diasSalida">
              <Translate contentKey="tordaviApp.cCorrida.diasSalida">Dias Salida</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.diasSalida}</dd>
          <dt>
            <span id="comentarios">
              <Translate contentKey="tordaviApp.cCorrida.comentarios">Comentarios</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.comentarios}</dd>
          <dt>
            <span id="idUsuarioCreacion">
              <Translate contentKey="tordaviApp.cCorrida.idUsuarioCreacion">Id Usuario Creacion</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.idUsuarioCreacion}</dd>
          <dt>
            <span id="fechaCreacion">
              <Translate contentKey="tordaviApp.cCorrida.fechaCreacion">Fecha Creacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cCorridaEntity.fechaCreacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="idUsuarioActualizacion">
              <Translate contentKey="tordaviApp.cCorrida.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.idUsuarioActualizacion}</dd>
          <dt>
            <span id="fechaActualizacion">
              <Translate contentKey="tordaviApp.cCorrida.fechaActualizacion">Fecha Actualizacion</Translate>
            </span>
          </dt>
          <dd>
            <TextFormat value={cCorridaEntity.fechaActualizacion} type="date" format={APP_DATE_FORMAT} />
          </dd>
          <dt>
            <span id="notas">
              <Translate contentKey="tordaviApp.cCorrida.notas">Notas</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.notas}</dd>
          <dt>
            <span id="estatus">
              <Translate contentKey="tordaviApp.cCorrida.estatus">Estatus</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.estatus}</dd>
          <dt>
            <span id="borrado">
              <Translate contentKey="tordaviApp.cCorrida.borrado">Borrado</Translate>
            </span>
          </dt>
          <dd>{cCorridaEntity.borrado}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cCorrida.cliente">Cliente</Translate>
          </dt>
          <dd>{cCorridaEntity.clienteCliente ? cCorridaEntity.clienteCliente : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cCorrida.autobus">Autobus</Translate>
          </dt>
          <dd>{cCorridaEntity.autobusAutobus ? cCorridaEntity.autobusAutobus : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cCorrida.lugarSalida">Lugar Salida</Translate>
          </dt>
          <dd>{cCorridaEntity.lugarSalidaClaveLugarParada ? cCorridaEntity.lugarSalidaClaveLugarParada : ''}</dd>
          <dt>
            <Translate contentKey="tordaviApp.cCorrida.lugarLlegada">Lugar Llegada</Translate>
          </dt>
          <dd>{cCorridaEntity.lugarLlegadaClaveLugarParada ? cCorridaEntity.lugarLlegadaClaveLugarParada : ''}</dd>
        </dl>
        <Button tag={Link} to="/c-corrida" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/c-corrida/${cCorridaEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ cCorrida }: IRootState) => ({
  cCorridaEntity: cCorrida.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CCorridaDetail);
