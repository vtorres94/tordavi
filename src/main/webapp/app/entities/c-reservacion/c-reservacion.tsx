import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-reservacion.reducer';
import { ICReservacion } from 'app/shared/model/c-reservacion.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICReservacionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CReservacion = (props: ICReservacionProps) => {
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

  const { cReservacionList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-reservacion-heading">
        <Translate contentKey="tordaviApp.cReservacion.home.title">C Reservacions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cReservacion.home.createLabel">Create new C Reservacion</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cReservacionList && cReservacionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('claveReservacion')}>
                  <Translate contentKey="tordaviApp.cReservacion.claveReservacion">Clave Reservacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('precio')}>
                  <Translate contentKey="tordaviApp.cReservacion.precio">Precio</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('numPasajeros')}>
                  <Translate contentKey="tordaviApp.cReservacion.numPasajeros">Num Pasajeros</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('comentarios')}>
                  <Translate contentKey="tordaviApp.cReservacion.comentarios">Comentarios</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cReservacion.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cReservacion.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cReservacion.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cReservacion.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cReservacion.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cReservacion.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cReservacion.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cReservacion.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cReservacion.pasajeroPrincipal">Pasajero Principal</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cReservacion.corrida">Corrida</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cReservacionList.map((cReservacion, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cReservacion.id}`} color="link" size="sm">
                      {cReservacion.id}
                    </Button>
                  </td>
                  <td>{cReservacion.claveReservacion}</td>
                  <td>{cReservacion.precio}</td>
                  <td>{cReservacion.numPasajeros}</td>
                  <td>{cReservacion.comentarios}</td>
                  <td>{cReservacion.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cReservacion.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cReservacion.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cReservacion.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cReservacion.notas}</td>
                  <td>{cReservacion.estatus}</td>
                  <td>{cReservacion.borrado}</td>
                  <td>
                    {cReservacion.clienteCliente ? (
                      <Link to={`c-cliente/${cReservacion.clienteId}`}>{cReservacion.clienteCliente}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {cReservacion.pasajeroPrincipalNombreCompleto ? (
                      <Link to={`c-pasajero/${cReservacion.pasajeroPrincipalId}`}>{cReservacion.pasajeroPrincipalNombreCompleto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {cReservacion.corridaClaveCorrida ? (
                      <Link to={`c-corrida/${cReservacion.corridaId}`}>{cReservacion.corridaClaveCorrida}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cReservacion.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cReservacion.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cReservacion.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="tordaviApp.cReservacion.home.notFound">No C Reservacions found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cReservacionList && cReservacionList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cReservacion }: IRootState) => ({
  cReservacionList: cReservacion.entities,
  loading: cReservacion.loading,
  totalItems: cReservacion.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CReservacion);
