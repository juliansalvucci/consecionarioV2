import { IModelo } from "./IModelo";

export interface IAuto {
    id:       number;
    precio:   number;
    costo:    number;
    idModelo: number;
    idMarca:  number;
    vendido:  boolean;
    modelo:   IModelo;
}
