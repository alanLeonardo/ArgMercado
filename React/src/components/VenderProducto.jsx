import React, { useState, useEffect } from 'react';
import { Form, Button, Col } from 'react-bootstrap';
import { crearProducto, crearFiles, getCategorias } from '../components/Api.jsx';
import { useHistory } from 'react-router-dom'
import Swal from 'sweetalert2';

function VenderProducto() {

  const history = useHistory();
  const [index, setIndex] = useState(0);
  const [formImagen, setFormImagen] = useState(new FormData());
  const [data, setData] = useState({
    categoria: "",
    titulo: "",
    descripcion: "",
    precio: 0.0,
    stock: 1
  });
  const [categorias, setCategorias] = useState([]);
  const [errors, setErrors] = useState({
    categoriaError: "",
    tituloError: "",
    descripcionError: "",
    imagenesError: "",
    precioError: "",
    stockError: ""
  });
  const [isLoad, setLoad] = useState(false)

  function validarCategoria() {

    if (!data.categoria) {
      return "Debe seleccionar una Categoria."
    }
  }

  function validarTitulo() {

    if (!data.titulo) {
      return "Titulo no puede ser un campo en blanco."
    }
  }

  function validarDescripcion() {

    if (!data.descripcion) {
      return "Descripcion no puede ser un campo en blanco."
    }

  }

  function validarImagenes() {

    if (formImagen.entries().next().value === undefined) {
      return "Debe seleccionar una o mas imagenes."
    }
  }

  function validarPrecio() {
    if (data.precio > 0.0) {
      return "El precio tiene que se un valor superior a 0."
    }
  }

  function validarStock() {
    if (data.stock > 0) {
      return "El stock tiene que se un valor superior a 0."
    }
  }

  function validacionDeCampos() {

    setErrors({
      categoriaError: validarCategoria(),
      tituloError: validarTitulo(),
      descripcionError: validarDescripcion(),
      imagenesError: validarImagenes(),
      precioError: validarPrecio(),
      stockError: validarStock()
    });
    console.log(!(errors.categoriaError || errors.tituloError || errors.descripcionError || errors.imagenesError))

    return !(errors.categoriaError || errors.tituloError || errors.descripcionError || errors.imagenesError);
  }

  function swalForError(error) {
    Swal.fire(`${error.code} ${error.type}`, error.message, 'error');
  }

  function handleSubmit(e) {
    e.preventDefault();
    setLoad(validacionDeCampos());
    console.log(isLoad);

    if (isLoad) {
      const files = formImagen;
      console.log(files.get("files"));
      crearFiles(files)
        .then(response => { console.log(response); })
        .catch(error => {
          return Promise.reject(error);
        });


      crearProducto({
        categoria: { name: data.categoria },
        titulo: data.titulo,
        descripcion: data.descripcion,
        precio: data.precio,
        stock: data.stock
      })
        .then((response) => {
          console.log(response)
        })
        .catch(error => {
          swalForError(error)
        });

      console.log(data);

      history.push("/home");
    }
  }

  function handleChange(e) {
    setData({ ...data, [e.target.name]: e.target.value })
  }

  function handleChangeFile(e) {

    for (let i = 0; i < e.target.files.length; i++) {
      setIndex(i);
    }

    formImagen.append("files", e.target.files[index]);

  }

  const fecthCategorias = async () => {
    const cs = await getCategorias()
      .then(response => {
        return response.data;
      })
      .catch(error => {
        return swalForError(error);
      })

    setCategorias(cs)
  }

  useEffect(() => {
    if (isLoad) {
      setLoad(validacionDeCampos());
    }
    fecthCategorias();
  }, [isLoad])


  function getNameCategorias() {
    let localCategoria = new Set([]);
    categorias.forEach(c => localCategoria.add(c.name));
    return Array.from(localCategoria);
  }

  function options() {
    return getNameCategorias().map((c, index) => {
      return <option>{c}</option>
    })
  }

  console.log("producto:", data);
  console.log(formImagen.get("files"));

  return (
    <div className="containerVenderProducto">
      <Form className="containerProducto" method="post" multiple onSubmit={(e) => handleSubmit(e)}>
        <Form.Label className="tituloPublicar"> Publicar producto </Form.Label>
        <Form.Group className="label" as={Col} controlId="formBasicCategoria">
          <Form.Label>Categoria</Form.Label>
          <Form.Control type="categoria" name="categoria" as="select" value={data.categoria} onChange={(e) => handleChange(e)} >
            <option>Seleccione una Categoria</option>
            {options()}
          </Form.Control>
          <Form.Label className="text-danger"> {errors.categoriaError}</Form.Label>
        </Form.Group>
        <Form.Group className="label" controlId="formBasicImagenes">
          <Form.Label> Imagenes </Form.Label>
          <Form.Control type="file" placeholder="Imagenes" name="imagenes" accept="image/*" onChange={(e) => handleChangeFile(e)} />
          <Form.Label className="text-danger"> {errors.imagenesError}</Form.Label>
        </Form.Group>
        <Form.Group className="label" controlId="formBasicTitulo">
          <Form.Label> Titulo </Form.Label>
          <Form.Control type="titulo" placeholder="Titulo" value={data.titulo} name="titulo" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> {errors.tituloError}</Form.Label>
        </Form.Group>
        <Form.Group className="label" controlId="formBasicDescripcion">
          <Form.Label> Descripcion </Form.Label>
          <Form.Control type="descripcion" placeholder="Descripcion" value={data.descripcion} name="descripcion" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> {errors.descripcionError} </Form.Label>
        </Form.Group>
        <Form.Group className="number" controlId="formBasicPrecio">
          <Form.Label> Precio  </Form.Label>
          <Form.Control type="number" placeholder="Precio" value={data.precio} name="precio" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger"> { } </Form.Label>
        </Form.Group>
        <Form.Group className="number" controlId="formBasicStock">
          <Form.Label> Stock  </Form.Label>
          <Form.Control type="number" placeholder="Stock" value={data.stock} name="stock" onChange={(e) => handleChange(e)} />
          <Form.Label className="text-danger">{ } </Form.Label>
          <Button className="botonPublicar" variant="btn btn-dark" type="submit" >
            Publicar producto
   			 </Button>
        </Form.Group>
      </Form>
    </div>
  )
}

export default VenderProducto;