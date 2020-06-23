import { Moment } from 'moment';

export interface ICDetalleReservacion {
  id?: number;
  precio?: number;
  idUsuarioCreacion?: number;
  fechaCreacion?: Moment;
  idUsuarioActualizacion?: number;
  fechaActualizacion?: Moment;
  notas?: string;
  estatus?: number;
  borrado?: number;
  clienteCliente?: string;
  clienteId?: number;
  pasajeroNombreCompleto?: string;
  pasajeroId?: number;
  reservacionClaveReservacion?: string;
  reservacionId?: number;
}

export const defaultValue: Readonly<ICDetalleReservacion> = {};
