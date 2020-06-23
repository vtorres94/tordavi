import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CLugarParada from './c-lugar-parada';
import CLugarParadaDetail from './c-lugar-parada-detail';
import CLugarParadaUpdate from './c-lugar-parada-update';
import CLugarParadaDeleteDialog from './c-lugar-parada-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CLugarParadaDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CLugarParadaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CLugarParadaUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CLugarParadaDetail} />
      <ErrorBoundaryRoute path={match.url} component={CLugarParada} />
    </Switch>
  </>
);

export default Routes;
