import { Moment } from 'moment';

export interface ICCliente {
  id?: number;
  claveCliente?: string;
  cliente?: string;
  logo?: string;
  idUsuarioCreacion?: number;
  fechaCreacion?: Moment;
  idUsuarioActualizacion?: number;
  fechaActualizacion?: Moment;
  notas?: string;
  estatus?: number;
  borrado?: number;
}

export const defaultValue: Readonly<ICCliente> = {};
