import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CDetalleReservacion from './c-detalle-reservacion';
import CDetalleReservacionDetail from './c-detalle-reservacion-detail';
import CDetalleReservacionUpdate from './c-detalle-reservacion-update';
import CDetalleReservacionDeleteDialog from './c-detalle-reservacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CDetalleReservacionDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CDetalleReservacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CDetalleReservacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CDetalleReservacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={CDetalleReservacion} />
    </Switch>
  </>
);

export default Routes;
