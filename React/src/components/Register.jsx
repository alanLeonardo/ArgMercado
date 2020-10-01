import React, { Fragment, useState, useEffect } from "react";
import { Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, useHistory } from "react-router-dom";
import Swal from 'sweetalert2';
import { registarUsuario } from '../components/Api.jsx';
import style from '../components/style.css'

function Register() {

  let history = useHistory();
  const emailRegExp = "^[\\w.-]{1,20}@\\w{3,7}\\.[a-zA-Z]{2,3}$"
  const contraseñaRegExp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])"
  const [isLoad, setLoad] = useState(false)

  const [data, setData] = useState({
    nombreCompleto: "",
    email: "",
    contraseña: ""
  });

  const [errors, setErrors] = useState({
    nombreCompletoError: "",
    emailError: "",
    contraseñaError: ""
  });


  function validarNombreCompleto() {

    if (!data.nombreCompleto) {
      return "NombreCompleto no puede ser un campo en blanco."
    }
  }

  function validarEmail() {

    if (!data.email) {
      return "Email no puede ser un campo en blanco."
    }
    if (!data.email.match(emailRegExp)) {
      return "Email debe tener un formato parecido a por ejemplo: (usuario@gmail.com)."
    }
  }

  function validarContraseña() {

    if (!data.contraseña) {
      return "Contraseña no puede ser un campo en blanco."
    }

    if (!data.contraseña.match(contraseñaRegExp)) {
      return "La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula. NO puede tener otros símbolos. Ejemplo: w3Unpocodet0d0"
    }
  }

  function validacionDeCampos() {

    setErrors({
      nombreCompletoError: validarNombreCompleto(),
      emailError: validarEmail(),
      contraseñaError: validarContraseña()
    });

    console.log(!(errors.emailError || errors.contraseñaError || errors.nombreCompletoError))

    return !(errors.emailError || errors.contraseñaError || errors.nombreCompletoError);
  }


  // Lanza un alerta de que hubo un error en registrar el usario con ese email.
  function swalForError(error) {
    if (error.message.includes('email registrado')) {
      Swal.fire(`${error.code} ${error.type}`,
        `El Email ${data.email} ya fue registrado por otro usuario.`, 'error');
    } else {
      Swal.fire(`${error.code} ${error.type}`, error.message, 'error');
    }
  }

  function handleChange(e) {
    setData({ ...data, [e.target.name]: e.target.value })
  }

  const fecthData = async () => {

    const login = await registarUsuario({
      nombreCompleto: data.nombreCompleto,
      email: data.email,
      contraseña: data.contraseña,
    })
      .then((response) => {
        console.log(response)
      })
      .catch(error => {
        swalForError(error)
      });

  }

  useEffect(() => {

    if (isLoad) {
      setLoad(validacionDeCampos())
    }

  }, [isLoad])

  function handleSubmit(e) {
    e.preventDefault();
    setLoad(validacionDeCampos())

    if (isLoad) {
      fecthData()
      history.push("/login");
    }
  }

  return (
    <div>
      <Form className="container" onSubmit={(e) => handleSubmit(e)}>
        <Form.Label className="titulo"> Registrarse </Form.Label>
        <Form.Group className="label" controlId="formBasicNombreCompleto">
          <Form.Label> Nombre completo </Form.Label>
          <Form.Control type="nombreCompleto" placeholder="Ingrese su nombre completo" name="nombreCompleto" value={data.nombreCompleto} onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> {errors.nombreCompletoError}  </Form.Label>
        </Form.Group>
        <Form.Group className="label" controlId="formBasicEamil">
          <Form.Label> Email </Form.Label>
          <Form.Control type="email" placeholder="Ingrese un email" value={data.email} name="email" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> {errors.emailError} </Form.Label>
        </Form.Group>
        <Form.Group className="label" controlId="formBasicContraseña">
          <Form.Label> Contraseña </Form.Label>
          <Form.Control type="contraseña" placeholder="Ingrese una contraseña" value={data.contraseña} name="contraseña" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> {errors.contraseñaError} </Form.Label>
        </Form.Group>
        <Button className="register" variant="btn btn-dark" type="submit" >
          Registrar
  			 </Button>
        <div>
          <Link to="/login"> Back</Link>
        </div>
      </Form>
    </div>
  )


}

export default Register;