import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CReservacion from './c-reservacion';
import CReservacionDetail from './c-reservacion-detail';
import CReservacionUpdate from './c-reservacion-update';
import CReservacionDeleteDialog from './c-reservacion-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CReservacionDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CReservacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CReservacionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CReservacionDetail} />
      <ErrorBoundaryRoute path={match.url} component={CReservacion} />
    </Switch>
  </>
);

export default Routes;
