import { IPais } from "./IPais";

export interface IMarca {
    id:          number;
    nombreMarca: string;
    idPais:      number;
    pais:        IPais;
}



