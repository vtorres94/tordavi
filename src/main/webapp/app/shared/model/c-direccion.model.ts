import { Moment } from 'moment';

export interface ICDireccion {
  id?: number;
  direccionComplete?: string;
  direccion?: string;
  numExterior?: string;
  numInterior?: string;
  codigoPostal?: number;
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
  pasajeroNombreCompleto?: string;
  pasajeroId?: number;
}

export const defaultValue: Readonly<ICDireccion> = {};
