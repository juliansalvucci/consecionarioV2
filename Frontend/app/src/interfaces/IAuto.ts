import { IModelo } from "./IModelo";
import { IPais } from "./IPais";

export interface IAuto {
    id:      number;
    precio:  number;
    costo:   number;
    vendido: boolean;
    modelo:  IModelo;
    pais:    IPais;
}




