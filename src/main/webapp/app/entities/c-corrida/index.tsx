import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CCorrida from './c-corrida';
import CCorridaDetail from './c-corrida-detail';
import CCorridaUpdate from './c-corrida-update';
import CCorridaDeleteDialog from './c-corrida-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CCorridaDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CCorridaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CCorridaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CCorridaDetail} />
      <ErrorBoundaryRoute path={match.url} component={CCorrida} />
    </Switch>
  </>
);

export default Routes;
