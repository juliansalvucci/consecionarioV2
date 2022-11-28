export interface IBusquedaAvanzadaV2Request {
    fechaDesde:   string;
    fechaHasta:   string;
    montoInicial: number;
    montoFinal:   number;
    idMarca:      number;
    idModelo:     number;
    idCiente:     number;
    idVendedor:   number;
    idCategoria:  number;
}
