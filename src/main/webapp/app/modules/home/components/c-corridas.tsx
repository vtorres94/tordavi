import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat, getSortState, IPaginationBaseState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from 'app/entities/c-corrida/c-corrida.reducer';
import { ICCorrida } from 'app/shared/model/c-corrida.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import BootstrapTable from 'react-bootstrap-table-next';
import ToolkitProvider, { ColumnToggle } from 'react-bootstrap-table2-toolkit';
import paginationFactory from 'react-bootstrap-table2-paginator';
import { onRowSelect } from 'react-bootstrap-table';
import { Segment } from 'semantic-ui-react';

export interface ICCorridaProps extends StateProps, DispatchProps {
    lugarSalidaId?: number,
    lugarLlegadaId?: number,
    fecha?: string
}

export const CCorrida = (props: ICCorridaProps) => {
  const [paginationState, setPaginationState] = useState(ITEMS_PER_PAGE);

  useEffect(() => {
    console.log('********************************************************************************')
    getEntities('&lugarSalidaId.equals=' + props.lugarSalidaId, 0, 1000, 'id,asc');
    console.log(props.cCorridaList)
  }, []);

  const { cCorridaList, loading, totalItems } = props;

  const columns = [
    {
      align: 'center',
      dataField: 'id',
      text: 'ID',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '10%', textAlign: 'center' };
      }
    },
    {
      align: 'center',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '10%', textAlign: 'center' };
      },
      dataField: 'claveCorrida',
      text: 'Clave'
    },
    {
      align: 'center',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '10%', textAlign: 'center' };
      },
      dataField: 'lugarSalidaClaveLugarParada',
      text: 'Origen'
    },
    {
      align: 'center',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '10%', textAlign: 'center' };
      },
      dataField: 'lugarLlegadaClaveLugarParada',
      text: 'Destino'
    },
    {
      align: 'center',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '12%', textAlign: 'center' };
      },
      dataField: 'horaSalida',
      text: 'Hora salida'
    },
    {
      align: 'center',
      // tslint:disable-next-line:ter-arrow-body-style
      headerStyle: (colum: any, colIndex: any) => {
        return { width: '12%', textAlign: 'center' };
      },
      dataField: 'horaLlegada',
      text: 'Hora llegada'
    }
  ];
  const sizePerPageRenderer = ({ options, currSizePerPage, onSizePerPageChange }) => (
    <div className="btn-group" role="group">
      <Button.Group>
        {options.map((option: { page: any; text: {} }) => {
          const isSelect = currSizePerPage === `${option.page}`;
          return (
            // tslint:disable-next-line:jsx-key
            <Button
              replace
              size="tiny"
              // key={option.text}
              type="button"
              color={isSelect ? 'grey' : null}
              // tslint:disable-next-line:jsx-no-lambda
              onClick={() => onSizePerPageChange(option.page)}
              // className={ `btn ${isSelect ? 'btn-info' : 'btn-secondary'}` }
            >
              {option.text}
            </Button>
          );
        })}
      </Button.Group>
    </div>
  );
  const options = {
    page: 1, // which page you want to show as default
    sizePerPageList: [
      {
        text: '5',
        value: 5
      },
      {
        text: '10',
        value: 10
      }
    ], // you can change the dropdown list for size per page
    sizePerPage: 5, // which size per page you want to locate as default
    pageStartIndex: 1, // where to start counting the pages
    paginationSize: 10, // the pagination bar size.
    prePage: '<', // Previous page button text
    nextPage: '>', // Next page button text
    firstPage: '<<', // First page button text
    lastPage: '>>', // Last page button text
    noDataText: 'No existen registros',
    sizePerPageRenderer,
    /*   sizePerPageDropDown: this.renderSizePerPageDropDown, */
    paginationPosition: 'bottom' // default is bottom, top and both is all available
    // hideSizePerPage: true > You can hide the dropdown for sizePerPage
    // alwaysShowAllBtns: true // Always show next and previous button
    // withFirstAndLast: false > Hide the going to First and Last page button
  };

  const selectRowProp = {
    mode: 'radio',
    clickToSelect: true,
    onSelect: onRowSelect,
    bgColor: 'pink', // you should give a bgcolor, otherwise, you can't regonize which row has been selected
    hideSelectColumn: true // enable hide selection column.
  };

  return (
      <Segment>
          <ToolkitProvider
              className="table table-hover table-sm table-striped card"
              keyField="id"
              pagination={paginationFactory(options)}
              selectRow={selectRowProp}
              // data={isAdmin ? gabinetesList : gabinetesList.filter(row => row.proveedorId === user.proveedorId)}
              data={props.cCorridaList ? props.cCorridaList : null}
              columns={columns}
              columnToggle
          >
              {(props: {
                  columnToggleProps: JSX.IntrinsicAttributes & { columns: any; onColumnToggle: any; toggles: any };
                  baseProps: JSX.IntrinsicAttributes;
              }) => (
                      <div>
                          <BootstrapTable
                              {...props.baseProps}
                              noDataIndication="No existen registros"
                              selectRow={selectRowProp}
                              pagination={paginationFactory(options)}
                          />
                      </div>
                  )}
          </ToolkitProvider>
      </Segment>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  cCorridaList: storeState.cCorrida.entities,
  loading: storeState.cCorrida.loading,
  totalItems: storeState.cCorrida.totalItems
});

const mapDispatchToProps = {
  getEntities
};``
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CCorrida);
