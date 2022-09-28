import { environment as ENV } from "../environments/environment";

export const API_ROUTES = {
    AUTH: {
        LOGIN:`${ENV.apiUrl}`,
        REGISTER:`${ENV.apiUrl}`,     
    },

    PAIS: {
        ALTAPAIS:`${ENV.apiUrl}`,
        BAJAPAIS:`${ENV.apiUrl}`,
        MODIFICACIONPAIS: `${ENV.apiUrl}`,
        CONSULTARPAISES:`${ENV.apiUrl}`,
        CONSULTARPAIS:"http://localhost:8080/pais",
    }

    
}