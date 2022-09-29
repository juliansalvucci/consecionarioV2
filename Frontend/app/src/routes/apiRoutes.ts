import { environment as ENV } from "../environments/environment";

export const API_ROUTES = {
    AUTH: {
        LOGIN:`${ENV.apiUrl}`,
        REGISTER:`${ENV.apiUrl}`,     
    },

    PAIS: {
        ALTAPAIS:`${ENV.apiUrl}pais`,
        BAJAPAIS:`${ENV.apiUrl}pais/`,
        MODIFICACIONPAIS: `${ENV.apiUrl}pais/`,
        CONSULTARPAISES:`${ENV.apiUrl}pais`,
        CONSULTARPAIS:`${ENV.apiUrl}pais/`,
    }

    
}