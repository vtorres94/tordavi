import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import cCliente, {
  CClienteState
} from 'app/entities/c-cliente/c-cliente.reducer';
// prettier-ignore
import cAutobus, {
  CAutobusState
} from 'app/entities/c-autobus/c-autobus.reducer';
// prettier-ignore
import cContacto, {
  CContactoState
} from 'app/entities/c-contacto/c-contacto.reducer';
// prettier-ignore
import cCorrida, {
  CCorridaState
} from 'app/entities/c-corrida/c-corrida.reducer';
// prettier-ignore
import cDetalleReservacion, {
  CDetalleReservacionState
} from 'app/entities/c-detalle-reservacion/c-detalle-reservacion.reducer';
// prettier-ignore
import cDireccion, {
  CDireccionState
} from 'app/entities/c-direccion/c-direccion.reducer';
// prettier-ignore
import cDocumentoViaje, {
  CDocumentoViajeState
} from 'app/entities/c-documento-viaje/c-documento-viaje.reducer';
// prettier-ignore
import cLugarParada, {
  CLugarParadaState
} from 'app/entities/c-lugar-parada/c-lugar-parada.reducer';
// prettier-ignore
import cPasajero, {
  CPasajeroState
} from 'app/entities/c-pasajero/c-pasajero.reducer';
// prettier-ignore
import cPendiente, {
  CPendienteState
} from 'app/entities/c-pendiente/c-pendiente.reducer';
// prettier-ignore
import cReservacion, {
  CReservacionState
} from 'app/entities/c-reservacion/c-reservacion.reducer';
// prettier-ignore
import cVenta, {
  CVentaState
} from 'app/entities/c-venta/c-venta.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly cCliente: CClienteState;
  readonly cAutobus: CAutobusState;
  readonly cContacto: CContactoState;
  readonly cCorrida: CCorridaState;
  readonly cDetalleReservacion: CDetalleReservacionState;
  readonly cDireccion: CDireccionState;
  readonly cDocumentoViaje: CDocumentoViajeState;
  readonly cLugarParada: CLugarParadaState;
  readonly cPasajero: CPasajeroState;
  readonly cPendiente: CPendienteState;
  readonly cReservacion: CReservacionState;
  readonly cVenta: CVentaState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  cCliente,
  cAutobus,
  cContacto,
  cCorrida,
  cDetalleReservacion,
  cDireccion,
  cDocumentoViaje,
  cLugarParada,
  cPasajero,
  cPendiente,
  cReservacion,
  cVenta,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
