import { Moment } from 'moment';

export interface ICLugarParada {
  id?: number;
  claveLugarParada?: string;
  comunidad?: string;
  ciudad?: string;
  estado?: string;
  pais?: string;
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

export const defaultValue: Readonly<ICLugarParada> = {};
