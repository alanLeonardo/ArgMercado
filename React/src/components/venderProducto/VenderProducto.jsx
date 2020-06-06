import React, { useState  } from 'react';
import {Form,Button} from 'react-bootstrap';
import {crearProducto,crearFiles} from '../api/Api';
import {useHistory} from 'react-router-dom'
import Swal from 'sweetalert2';

function VenderProducto() {

   let history = useHistory();
   const [index,setIndex] = useState(0)
   const [formImagen,setFormImagen] = useState(new FormData())
   const [data, setData] = useState({
                                      categoria: "Celulares",
                                      titulo: "titulo",
                                      descripcion: "descripcion",
                                      precio: 1.0,
                                      stock: 0
                                     });

   function swalForError(error) {
       Swal.fire(`${error.code} ${error.type}`, error.message, 'error');
   }

  function handleSubmit(e) {
    e.preventDefault();

     const files = formImagen
         crearFiles(files)
             .then(response => {console.log(response)})
             .catch(error => {
                return Promise.reject(error);
             });

         crearProducto({  categoria: data.categoria,
                          titulo: data.titulo,
                          descripcion: data.descripcion,
                          precio: data.precio,
                          stock: data.stock
                      })
              	    .then((response) => { console.log(response)

                         })
        	       .catch(error => { swalForError(error)
            	          });

          history.push("/home")


  }

  function handleChange(e) {
    setData({...data,[e.target.name]: e.target.value})
  }

   function handleChangeFile(e) {

      for(let i = 0; i < e.target.files.length; i++) {
         setIndex(i)
      }

      formImagen.append("files", e.target.files[index]);

  }

   return (
   		<div>
   		 <Form className="container" method="post" multiple onSubmit={(e) => handleSubmit(e) }>
   		   <Form.Label className="label"> PublicarProducto </Form.Label>
   		    <Form.Group className="label" controlId="formBasicCategoria">
                <Form.Label> Categoria </Form.Label>
              <Form.Control type="categoria" placeholder="Categoria" value={data.categoria} name="categoria" multiple onChange={(e) => handleChange(e)}/>
                <Form.Label className="text-danger"> {data.categoria}</Form.Label>
            </Form.Group>
             <Form.Group className="label" controlId="formBasicImagenes">
                <Form.Label> Imagenes </Form.Label>
               <Form.Control type="file"  placeholder="Imagenes" name="imagenes" accept="image/*" onChange={(e) => handleChangeFile(e)}/>
              <Form.Label className="text-danger"> {}</Form.Label>
              </Form.Group>
   			 <Form.Group className="label" controlId="formBasicTitulo">
   			   <Form.Label> Titulo </Form.Label>
   			   <Form.Control type="titulo" placeholder="Titulo" value={data.titulo} name="titulo" onChange={(e) => handleChange(e)}/>
   			   <Form.Label className="text-danger"> {data.titulo}</Form.Label>
   			 </Form.Group>
   			 <Form.Group className="label" controlId="formBasicDescripcion">
   			   <Form.Label> Descripcion </Form.Label>
   			   <Form.Control type="descripcion" placeholder="Descripcion" value={data.descripcion} name="descripcion" onChange={(e) => handleChange(e)}/>
   			   <Form.Label className="text-danger"> {data.descripcion} </Form.Label>
   			 </Form.Group>
   			 <Form.Group  className="number" controlId="formBasicPrecio">
   			   <Form.Label> Precio  </Form.Label>
   			   <Form.Control type="number" placeholder="Precio" value={data.precio} name="precio" onChange={(e) => handleChange(e)}/>
   			   <Form.Label className="text-danger"> {data.precio} </Form.Label>
   			 </Form.Group>
   			 <Form.Group  className="number" controlId="formBasicStock">
           	   <Form.Label> Stock  </Form.Label>
               <Form.Control type="number" placeholder="Stock" value={data.stock} name="stock" onChange={(e) => handleChange(e)}/>
               <Form.Label className="text-danger">{data.stock} </Form.Label>
             </Form.Group>
   			 <Button className="register" variant="btn btn-dark" type="submit" >
   			   publicarProducto
   			 </Button>
   		   </Form>
   		 </div>
   		)
}

export default VenderProducto;