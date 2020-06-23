import { Moment } from 'moment';

export interface ICPasajero {
  id?: number;
  nombreCompleto?: string;
  nombre?: string;
  segundoNombre?: string;
  apellido?: string;
  segundoApellido?: string;
  edad?: number;
  curp?: string;
  ciudadania?: string;
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

export const defaultValue: Readonly<ICPasajero> = {};
