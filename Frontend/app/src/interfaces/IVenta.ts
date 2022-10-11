import { IAuto } from "./IAuto";
import { ICliente } from "./ICliente";

export interface IVenta {
    id:         number;
    fechaVenta: string;
    auto:       IAuto;
    cliente:    ICliente;
}



