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
    }
    
}