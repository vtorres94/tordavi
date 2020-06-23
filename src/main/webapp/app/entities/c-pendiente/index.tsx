import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CPendiente from './c-pendiente';
import CPendienteDetail from './c-pendiente-detail';
import CPendienteUpdate from './c-pendiente-update';
import CPendienteDeleteDialog from './c-pendiente-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CPendienteDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CPendienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CPendienteUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CPendienteDetail} />
      <ErrorBoundaryRoute path={match.url} component={CPendiente} />
    </Switch>
  </>
);

export default Routes;
