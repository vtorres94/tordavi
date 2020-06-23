import { Moment } from 'moment';

export interface ICPendiente {
  id?: number;
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
  reservacionClaveReservacion?: string;
  reservacionId?: number;
}

export const defaultValue: Readonly<ICPendiente> = {};
