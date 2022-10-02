import { IModelo } from "./IModelo";

export interface IAuto {
    id:       number;
    precio:   number;
    idModelo: number;
    idMarca:  number;
    modelo:   IModelo;
}
