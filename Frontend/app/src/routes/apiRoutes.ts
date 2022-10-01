import { environment as ENV } from "../environments/environment";

export const API_ROUTES = {
    AUTH: {
        LOGIN:`${ENV.apiUrl}`,
        REGISTER:`${ENV.apiUrl}`,     
    },

    PAIS: {
        ALTA:`${ENV.apiUrl}pais`,
        BAJA:`${ENV.apiUrl}pais/`,
        MODIFICACION: `${ENV.apiUrl}pais/`,
        CONSULTA:`${ENV.apiUrl}pais`,
        CONSULTAPORID:`${ENV.apiUrl}pais/`,
    },

    MARCA: {
        ALTA:`${ENV.apiUrl}marca`,
        BAJA:`${ENV.apiUrl}marca/`,
        MODIFICACION: `${ENV.apiUrl}marca/`,
        CONSULTA:`${ENV.apiUrl}marca`,
        CONSULTAPORID:`${ENV.apiUrl}marca/`,
    },

    MODELO: {
        ALTA:`${ENV.apiUrl}modelo`,
        BAJA:`${ENV.apiUrl}modelo/`,
        MODIFICACION: `${ENV.apiUrl}modelo/`,
        CONSULTA:`${ENV.apiUrl}modelo`,
        CONSULTAPORID:`${ENV.apiUrl}modelo/`,
    },

    AUTO: {
        ALTA:`${ENV.apiUrl}auto`,
        BAJA:`${ENV.apiUrl}auto/`,
        MODIFICACION: `${ENV.apiUrl}auto/`,
        CONSULTA:`${ENV.apiUrl}auto`,
        CONSULTAPORID:`${ENV.apiUrl}auto/`,
    }
    
}