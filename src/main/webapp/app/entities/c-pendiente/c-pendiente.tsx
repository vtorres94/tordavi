import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-pendiente.reducer';
import { ICPendiente } from 'app/shared/model/c-pendiente.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICPendienteProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CPendiente = (props: ICPendienteProps) => {
  const [paginationState, setPaginationState] = useState(getSortState(props.location, ITEMS_PER_PAGE));

  const getAllEntities = () => {
    props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
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

  const { cPendienteList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-pendiente-heading">
        <Translate contentKey="tordaviApp.cPendiente.home.title">C Pendientes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cPendiente.home.createLabel">Create new C Pendiente</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cPendienteList && cPendienteList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comentarios')}>
                  <Translate contentKey="tordaviApp.cPendiente.comentarios">Comentarios</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cPendiente.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cPendiente.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cPendiente.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cPendiente.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cPendiente.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cPendiente.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cPendiente.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cPendiente.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cPendiente.reservacion">Reservacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cPendienteList.map((cPendiente, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cPendiente.id}`} color="link" size="sm">
                      {cPendiente.id}
                    </Button>
                  </td>
                  <td>{cPendiente.comentarios}</td>
                  <td>{cPendiente.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cPendiente.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cPendiente.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cPendiente.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cPendiente.notas}</td>
                  <td>{cPendiente.estatus}</td>
                  <td>{cPendiente.borrado}</td>
                  <td>
                    {cPendiente.clienteCliente ? <Link to={`c-cliente/${cPendiente.clienteId}`}>{cPendiente.clienteCliente}</Link> : ''}
                  </td>
                  <td>
                    {cPendiente.reservacionClaveReservacion ? (
                      <Link to={`c-reservacion/${cPendiente.reservacionId}`}>{cPendiente.reservacionClaveReservacion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cPendiente.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cPendiente.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cPendiente.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="tordaviApp.cPendiente.home.notFound">No C Pendientes found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cPendienteList && cPendienteList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cPendiente }: IRootState) => ({
  cPendienteList: cPendiente.entities,
  loading: cPendiente.loading,
  totalItems: cPendiente.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CPendiente);
