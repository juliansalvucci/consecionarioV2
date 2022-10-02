import { IMarca } from "./IMarca";

export interface IModelo {
    id:           number;
    nombreModelo: string;
    idMarca:      number;
    marca:        IMarca;
}


