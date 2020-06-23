import { Moment } from 'moment';

export interface ICAutobus {
  id?: number;
  autobus?: string;
  idUsuarioCreacion?: number;
  fechaCreacion?: Moment;
  idUsuarioActualizacion?: number;
  fechaActualizacion?: Moment;
  notas?: string;
  estatus?: number;
  borrado?: number;
  clienteCliente?: string;
  clienteId?: number;
}

export const defaultValue: Readonly<ICAutobus> = {};
