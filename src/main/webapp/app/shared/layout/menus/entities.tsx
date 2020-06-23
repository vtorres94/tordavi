import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/c-cliente">
      <Translate contentKey="global.menu.entities.cCliente" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-autobus">
      <Translate contentKey="global.menu.entities.cAutobus" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-contacto">
      <Translate contentKey="global.menu.entities.cContacto" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-corrida">
      <Translate contentKey="global.menu.entities.cCorrida" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-detalle-reservacion">
      <Translate contentKey="global.menu.entities.cDetalleReservacion" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-direccion">
      <Translate contentKey="global.menu.entities.cDireccion" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-documento-viaje">
      <Translate contentKey="global.menu.entities.cDocumentoViaje" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-lugar-parada">
      <Translate contentKey="global.menu.entities.cLugarParada" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-pasajero">
      <Translate contentKey="global.menu.entities.cPasajero" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-pendiente">
      <Translate contentKey="global.menu.entities.cPendiente" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-reservacion">
      <Translate contentKey="global.menu.entities.cReservacion" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/c-venta">
      <Translate contentKey="global.menu.entities.cVenta" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
