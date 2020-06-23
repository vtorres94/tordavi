import { Moment } from 'moment';

export interface ICCorrida {
  id?: number;
  claveCorrida?: string;
  horaSalida?: string;
  horaLllegada?: string;
  conexion?: boolean;
  lugarConexion?: string;
  diasSalida?: string;
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
  autobusAutobus?: string;
  autobusId?: number;
  lugarSalidaClaveLugarParada?: string;
  lugarSalidaId?: number;
  lugarLlegadaClaveLugarParada?: string;
  lugarLlegadaId?: number;
}

export const defaultValue: Readonly<ICCorrida> = {
  conexion: false
};
