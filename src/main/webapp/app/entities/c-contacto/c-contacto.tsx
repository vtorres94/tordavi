import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './c-contacto.reducer';
import { ICContacto } from 'app/shared/model/c-contacto.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';

export interface ICContactoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const CContacto = (props: ICContactoProps) => {
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

  const { cContactoList, match, loading, totalItems } = props;
  return (
    <div>
      <h2 id="c-contacto-heading">
        <Translate contentKey="tordaviApp.cContacto.home.title">C Contactos</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="tordaviApp.cContacto.home.createLabel">Create new C Contacto</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {cContactoList && cContactoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('nombreContacto')}>
                  <Translate contentKey="tordaviApp.cContacto.nombreContacto">Nombre Contacto</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('telefono')}>
                  <Translate contentKey="tordaviApp.cContacto.telefono">Telefono</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('area')}>
                  <Translate contentKey="tordaviApp.cContacto.area">Area</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioCreacion')}>
                  <Translate contentKey="tordaviApp.cContacto.idUsuarioCreacion">Id Usuario Creacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaCreacion')}>
                  <Translate contentKey="tordaviApp.cContacto.fechaCreacion">Fecha Creacion</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('idUsuarioActualizacion')}>
                  <Translate contentKey="tordaviApp.cContacto.idUsuarioActualizacion">Id Usuario Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('fechaActualizacion')}>
                  <Translate contentKey="tordaviApp.cContacto.fechaActualizacion">Fecha Actualizacion</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('notas')}>
                  <Translate contentKey="tordaviApp.cContacto.notas">Notas</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('estatus')}>
                  <Translate contentKey="tordaviApp.cContacto.estatus">Estatus</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th className="hand" onClick={sort('borrado')}>
                  <Translate contentKey="tordaviApp.cContacto.borrado">Borrado</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cContacto.cliente">Cliente</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tordaviApp.cContacto.pasajero">Pasajero</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {cContactoList.map((cContacto, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${cContacto.id}`} color="link" size="sm">
                      {cContacto.id}
                    </Button>
                  </td>
                  <td>{cContacto.nombreContacto}</td>
                  <td>{cContacto.telefono}</td>
                  <td>{cContacto.area}</td>
                  <td>{cContacto.idUsuarioCreacion}</td>
                  <td>
                    <TextFormat type="date" value={cContacto.fechaCreacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cContacto.idUsuarioActualizacion}</td>
                  <td>
                    <TextFormat type="date" value={cContacto.fechaActualizacion} format={APP_DATE_FORMAT} />
                  </td>
                  <td>{cContacto.notas}</td>
                  <td>{cContacto.estatus}</td>
                  <td>{cContacto.borrado}</td>
                  <td>{cContacto.clienteCliente ? <Link to={`c-cliente/${cContacto.clienteId}`}>{cContacto.clienteCliente}</Link> : ''}</td>
                  <td>
                    {cContacto.pasajeroNombreCompleto ? (
                      <Link to={`c-pasajero/${cContacto.pasajeroId}`}>{cContacto.pasajeroNombreCompleto}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${cContacto.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${cContacto.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
                        to={`${match.url}/${cContacto.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
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
              <Translate contentKey="tordaviApp.cContacto.home.notFound">No C Contactos found</Translate>
            </div>
          )
        )}
      </div>
      <div className={cContactoList && cContactoList.length > 0 ? '' : 'd-none'}>
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

const mapStateToProps = ({ cContacto }: IRootState) => ({
  cContactoList: cContacto.entities,
  loading: cContacto.loading,
  totalItems: cContacto.totalItems
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CContacto);
