import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-venta.reducer';
import { ICVenta } from 'app/shared/model/c-venta.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICVentaProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CVenta = (props: ICVentaProps) => {
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

  const { cVentaList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-venta-heading">
        <Translate contentKey="tordaviApp.cVenta.home.title">C Ventas</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cVenta.home.createLabel">Create new C Venta</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cVentaList && cVentaList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('vendedorId')}>
                  <Translate contentKey="tordaviApp.cVenta.vendedorId">Vendedor Id</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('precioTotal')}>
                  <Translate contentKey="tordaviApp.cVenta.precioTotal">Precio Total</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaVenta')}>
                  <Translate contentKey="tordaviApp.cVenta.fechaVenta">Fecha Venta</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cVenta.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cVenta.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cVenta.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cVenta.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cVenta.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cVenta.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cVenta.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cVenta.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cVenta.reservacion">Reservacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cVentaList.map((cVenta, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cVenta.id}`} color="link" size="sm">
                      {cVenta.id}
                    </Button>
                  </td>
                  <td>{cVenta.vendedorId}</td>
                  <td>{cVenta.precioTotal}</td>
                  <td>
                    <TextFormat type="date" value={cVenta.fechaVenta} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cVenta.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cVenta.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cVenta.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cVenta.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cVenta.notas}</td>
                  <td>{cVenta.estatus}</td>
                  <td>{cVenta.borrado}</td>
                  <td>{cVenta.clienteCliente ? <Link to={`c-cliente/${cVenta.clienteId}`}>{cVenta.clienteCliente}</Link> : ''}</td>
                  <td>
                    {cVenta.reservacionClaveReservacion ? (
                      <Link to={`c-reservacion/${cVenta.reservacionId}`}>{cVenta.reservacionClaveReservacion}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cVenta.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cVenta.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cVenta.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="tordaviApp.cVenta.home.notFound">No C Ventas found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cVentaList && cVentaList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cVenta }: IRootState) => ({
  cVentaList: cVenta.entities,
  loading: cVenta.loading,
  totalItems: cVenta.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CVenta);
