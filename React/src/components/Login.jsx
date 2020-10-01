import React, { useState, useEffect } from "react";
import { Form, Button } from 'react-bootstrap';
import { useHistory, Link } from "react-router-dom";
import Authentication from '../components/Authentication.jsx';
import style from '../components/style.css'


function Login() {

   const history = useHistory();
   const emailRegExp = "^[\\w.-]{1,20}@\\w{3,7}\\.[a-zA-Z]{2,3}$"
   const contraseñaRegExp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])"
   const [isValid, setIsValid] = useState(false);
   const [auth,setAuth] = useState(false);

   const [data, setData] = useState({
      email: "",
      contraseña: ""
   });

   const [errors, setErrors] = useState({
      emailError: "",
      passwordError: ""
   });

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
         emailError: validarEmail(),
         passwordError: validarContraseña()
      });
      return errors.emailError === undefined && errors.passwordError === undefined;
   }


   function handleChange(e) {
      setData({ ...data, [e.target.name]: e.target.value })

   }

   function irAHome(e) {
      setIsValid(validacionDeCampos());
      e.preventDefault();
      console.log(auth);
      if (isValid && auth ) {
         history.push({ pathname: '/home', state: { email: data.email } });
      }
   }

   useEffect(() => {
      if(isValid) {
        console.log("hola")
        setAuth(Authentication(data));
      }
    }, [isValid])


  return (
   <div>
      <Form className="container">
         <Form.Label className="titulo"> Iniciar sesión </Form.Label>
         <Form.Group className="label" controlId="formBasicEamil">
            <Form.Label> Email </Form.Label>
            <Form.Control type="email" placeholder="Ingrese un email" value={data.email} name="email" onChange={(e) => handleChange(e)} />
            <Form.Label className="text-danger"> {errors.emailError} </Form.Label>
         </Form.Group>
         <Form.Group className="label" controlId="formBasicContraseña">
            <Form.Label> Contraseña </Form.Label>
            <Form.Control type="password" placeholder="Ingrese una contraseña" value={data.contraseña} name="contraseña" onChange={(e) => handleChange(e)} />
            <Form.Label className="text-danger"> {errors.passwordError} </Form.Label>
            <Button className="link" className="link" type="submit" onClick={(e) => irAHome(e)} >
               Login
               </Button>
            <Link className="link" to="/register">Registrarse</Link>
         </Form.Group>
      </Form>
   </div>
  )

}


export default Login;
