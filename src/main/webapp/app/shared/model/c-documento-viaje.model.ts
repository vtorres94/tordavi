import { Moment } from 'moment';

export interface ICDocumentoViaje {
  id?: number;
  claveDocumento?: string;
  documento?: string;
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

export const defaultValue: Readonly<ICDocumentoViaje> = {};
