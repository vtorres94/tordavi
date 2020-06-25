import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-corrida.reducer';
import { ICCorrida } from 'app/shared/model/c-corrida.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICCorridaProps extends StateProps, DispatchProps, RouteComponentProps<{ url?: string }> {}

export const CCorrida = (props: ICCorridaProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities('', 0, 1000, 'id&asc');
  };

  const sortEntities = () => {
    getAllEntities();
    props.history.push(
      `${props.location.pathname}?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`
    );
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage
    });

  const { cCorridaList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-corrida-heading">
        <Translate contentKey="tordaviApp.cCorrida.home.title">C Corridas</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cCorrida.home.createLabel">Create new C Corrida</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cCorridaList && cCorridaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('claveCorrida')}>
                  <Translate contentKey="tordaviApp.cCorrida.claveCorrida">Clave Corrida</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('horaSalida')}>
                  <Translate contentKey="tordaviApp.cCorrida.horaSalida">Hora Salida</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('horaLllegada')}>
                  <Translate contentKey="tordaviApp.cCorrida.horaLllegada">Hora Lllegada</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('conexion')}>
                  <Translate contentKey="tordaviApp.cCorrida.conexion">Conexion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('lugarConexion')}>
                  <Translate contentKey="tordaviApp.cCorrida.lugarConexion">Lugar Conexion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('diasSalida')}>
                  <Translate contentKey="tordaviApp.cCorrida.diasSalida">Dias Salida</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comentarios')}>
                  <Translate contentKey="tordaviApp.cCorrida.comentarios">Comentarios</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cCorrida.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cCorrida.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cCorrida.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cCorrida.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cCorrida.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cCorrida.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cCorrida.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cCorrida.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cCorrida.autobus">Autobus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cCorrida.lugarSalida">Lugar Salida</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cCorrida.lugarLlegada">Lugar Llegada</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cCorridaList.map((cCorrida, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cCorrida.id}`} color="link" size="sm">
                      {cCorrida.id}
                    </Button>
                  </td>
                  <td>{cCorrida.claveCorrida}</td>
                  <td>{cCorrida.horaSalida}</td>
                  <td>{cCorrida.horaLllegada}</td>
                  <td>{cCorrida.conexion ? 'true' : 'false'}</td>
                  <td>{cCorrida.lugarConexion}</td>
                  <td>{cCorrida.diasSalida}</td>
                  <td>{cCorrida.comentarios}</td>
                  <td>{cCorrida.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cCorrida.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cCorrida.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cCorrida.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cCorrida.notas}</td>
                  <td>{cCorrida.estatus}</td>
                  <td>{cCorrida.borrado}</td>
                  <td>{cCorrida.clienteCliente ? <Link to={`c-cliente/${cCorrida.clienteId}`}>{cCorrida.clienteCliente}</Link> : ''}</td>
                  <td>{cCorrida.autobusAutobus ? <Link to={`c-autobus/${cCorrida.autobusId}`}>{cCorrida.autobusAutobus}</Link> : ''}</td>
                  <td>
                    {cCorrida.lugarSalidaClaveLugarParada ? (
                      <Link to={`c-lugar-parada/${cCorrida.lugarSalidaId}`}>{cCorrida.lugarSalidaClaveLugarParada}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {cCorrida.lugarLlegadaClaveLugarParada ? (
                      <Link to={`c-lugar-parada/${cCorrida.lugarLlegadaId}`}>{cCorrida.lugarLlegadaClaveLugarParada}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cCorrida.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cCorrida.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cCorrida.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="danger"
                        size="sm"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tordaviApp.cCorrida.home.notFound">No C Corridas found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cCorridaList && cCorridaList.length > 0 ? '' : 'd-none'}>
        <Row className="justify-content-center">
          <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
        </Row>
        <Row className="justify-content-center">
          <JhiPagination
            activePage={paginationState.activePage}
            onSelect={handlePagination}
            maxButtons={5}
            itemsPerPage={paginationState.itemsPerPage}
            totalItems={props.totalItems}
          />
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = ({ cCorrida }: IRootState) => ({
  cCorridaList: cCorrida.entities,
  loading: cCorrida.loading,
  totalItems: cCorrida.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CCorrida);
