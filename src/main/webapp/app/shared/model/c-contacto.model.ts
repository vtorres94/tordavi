import { Moment } from 'moment';

export interface ICContacto {
  id?: number;
  nombreContacto?: string;
  telefono?: string;
  area?: string;
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
}

export const defaultValue: Readonly<ICContacto> = {};
