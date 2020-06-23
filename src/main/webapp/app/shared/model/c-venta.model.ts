import { Moment } from 'moment';

export interface ICVenta {
  id?: number;
  vendedorId?: number;
  precioTotal?: number;
  fechaVenta?: Moment;
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

export const defaultValue: Readonly<ICVenta> = {};
