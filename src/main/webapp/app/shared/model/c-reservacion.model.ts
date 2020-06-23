import { Moment } from 'moment';

export interface ICReservacion {
  id?: number;
  claveReservacion?: string;
  precio?: number;
  numPasajeros?: number;
  comentarios?: string;
  idUsuarioCreacion?: number;
  fechaCreacion?: Moment;
  idUsuarioActualizacion?: number;
  fechaActualizacion?: Moment;
  notas?: string;
  estatus?: number;
  borrado?: number;
  clienteCliente?: string;
  clienteId?: number;
  pasajeroPrincipalNombreCompleto?: string;
  pasajeroPrincipalId?: number;
  corridaClaveCorrida?: string;
  corridaId?: number;
}

export const defaultValue: Readonly<ICReservacion> = {};
