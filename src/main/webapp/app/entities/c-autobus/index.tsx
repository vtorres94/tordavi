import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CAutobus from './c-autobus';
import CAutobusDetail from './c-autobus-detail';
import CAutobusUpdate from './c-autobus-update';
import CAutobusDeleteDialog from './c-autobus-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CAutobusDeleteDialog} />
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CAutobusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CAutobusUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CAutobusDetail} />
      <ErrorBoundaryRoute path={match.url} component={CAutobus} />
    </Switch>
  </>
);

export default Routes;
