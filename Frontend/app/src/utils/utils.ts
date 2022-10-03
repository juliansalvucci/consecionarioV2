import { FormControl, FormGroup } from "@angular/forms";
import Swal, { SweetAlertIcon, SweetAlertResult } from "sweetalert2";
import swal from 'sweetalert2';

export const pattern = {
    letters:"[a-zA-Z ]{2,254}",
    numbers:"^\d+$",
    getErrorMessage(form?:FormGroup, campo?:string, control?:FormControl): string | null{
        if(form != undefined && campo !== undefined){
            if (form.get(campo)?.hasError('required')) return 'Campo requerido*';
            if (form.get(campo)?.hasError('pattern')) return  'Tipo de dato incorrecto*';
            if (form.get(campo)?.hasError('maxLength')) return  'Cantidad de caracteres excedida*';
        }else{
            if ((control)?.hasError('required')) return 'Campo requerido*';
            if ((control)?.hasError('pattern')) return  'Tipo de dato incorrecto*';
        }

        return null;
        // return this.form.get('nombre')?.hasError('email') ? 'Not a valid email' : '';
    },
}

export interface IInputResponse{
    isConfirmed:boolean,
    value:string | undefined
}

export const sweetalert = {
    Toast : Swal.mixin({ //Declaro el mixin de sweet alert 2
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer);
          toast.addEventListener('mouseleave', Swal.resumeTimer);
        }
    }),

    Alert(title: string, text: string, iconInput:SweetAlertIcon = "warning"):Promise<SweetAlertResult<any>> { 
        return Swal.fire({
            title: title,
            text: text,
            icon: iconInput,
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#28a745',
            showCancelButton:true,
            cancelButtonText: 'Cancelar',
            cancelButtonColor:'#dc3545',
        })
    },
    success(text:string = "Operacion exitosa"):Promise<SweetAlertResult<any>> {
        return sweetalert.Toast.fire({
            icon: 'success',
            title: text
        })
    },
    failure(text:string = "Operacion fallida"):Promise<SweetAlertResult<any>> {
        return sweetalert.Toast.fire({
            icon: 'error',
            title: text
        })
    },
    input():Promise<IInputResponse | SweetAlertResult<any>>{
        var result:IInputResponse;
        return Swal.fire({
            input:"text",
            allowEnterKey:true,
            title: "Agregar nuevo marcador",
            icon: "question",
            confirmButtonText: 'Aceptar',
            confirmButtonColor: '#28a745',
            showCancelButton:true,
            cancelButtonText: 'Cancelar',
            cancelButtonColor:'#dc3545',
        })
        .then(resultado => {
            if (resultado.isConfirmed) {
                if(resultado.value.trim()){
                    if(resultado.value.trim() == ""){
                        result = { isConfirmed:true , value:"No ingresar campos vacios" }
                    }
                    result = { isConfirmed:true , value:resultado.value }
                }
                else{
                    result = { isConfirmed:false, value:undefined }
                }
            }
            return result;
        });
        
    }
}