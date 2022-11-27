import { IModelo } from "./IModelo";
import { IPais } from "./IPais";

export interface IAuto {
    id:      number;
    precio:  number;
    costo:   number;
    ganancia: number;
    vendido: boolean;
    modelo:  IModelo;
    pais:    IPais;
}




