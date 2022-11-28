import { IAuto } from "./IAuto";
import { ICliente } from "./ICliente";
import { IVendedor } from "./IVendedor";

export interface IVenta {
    id:         number;
    fechaVenta: string;
    auto:       IAuto;
    cliente:    ICliente;
    vendedor: IVendedor
}



