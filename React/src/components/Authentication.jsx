
import React, { useState, useEffect } from "react";
import { useHistory } from "react-router-dom";
import { iniciarSeccion } from '../components/Api';
import Swal from 'sweetalert2';

function Authentication(data) {

  function swalForError(error) {
    const errorTitle = "No puede iniciarSeccion"
    try {
      Swal.fire(errorTitle, error.message, 'error');
    } catch (error) {
      Swal.fire(errorTitle, "Este Usuario no existe", 'error')
    }
  }

  const fecthToken = async () => {
    const auth = await iniciarSeccion(data.email, data.contraseña)
      .then(response => {
        return response.data;
      })
      .catch(error => {
        return swalForError(error);
      })

    return auth;
  }


  return (fecthToken());

}



export default Authentication;
