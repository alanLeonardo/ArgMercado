// Componente React que representa a la Barra de Navegacion.
import React, { useState ,useEffect} from 'react';
import {Navbar, Nav, Button, Form, FormControl} from 'react-bootstrap';
import {useHistory} from 'react-router-dom'
import {getProductos} from '../components/Api.jsx';

function NavigationBar(props) {

      let history = useHistory();

      const [productos, setProductos] = useState([]);
      const [productosFiltrados, setProductosFiltrados] = useState([]);
      const [busqueda,setBusqueda] = useState("");
      history.location.state = productosFiltrados

      useEffect(() => {
                 const fecthData = async () => {
                 const response = await getProductos()
                                      .then(response => {
                                         return Promise.resolve(response);
                                       })
                                      .catch(error => {
                                         return Promise.reject(error);
                                       })
                   console.log(response.data)
                  setProductos(response.data)
                 }

                 fecthData();

      },[])

    function handleSubmit(e) {
       e.preventDefault();
       filtrarProductos()
    }

    function filtrarProductos() {
        let filtro = productos
        setProductosFiltrados(filtro.filter(p =>  p.titulo.toLowerCase().includes(busqueda.toLowerCase())));
        history.push({ pathname: '/home',  state: { productos: productosFiltrados} });
    }

    function handleChange(e) {
        setBusqueda(e.target.value)
    }

  console.log(productos)
	return (
          <div>
			<Navbar bg="primary" expand="lg" variant="dark">
				<Navbar.Brand>ArgMercado</Navbar.Brand>
				<Form inline onSubmit={(e) => handleSubmit(e)} >
                      <FormControl type="busqueda" value={busqueda} name="busqueda" onChange={(e) => handleChange(e)} placeholder="Busca el producto que necesitas" className="mr-sm-2" style={{width:'500px'}}/>
                      <Button className="btn-dark" variant="outline-info" type="submit" onClick={() => filtrarProductos } >
                         Buscar
                      </Button>
                </Form>
				<Navbar.Toggle aria-controls="responsive-navbar-nav" />
				<Navbar.Collapse id="responsive-navbar-nav">
					<Nav className="mr-auto">
          				<ul className="navbar-nav">
            				<li className="nav-item">
							</li>
         				</ul>
					</Nav>
				</Navbar.Collapse>
			</Navbar>
            <Navbar bg="primary" expand="lg" variant="dark">
              <Navbar.Collapse id="basic-navbar-nav">
                  <Nav className="mr-auto">
                    <Nav.Link onClick={() => history.push("/login")} >Iniciar seccion</Nav.Link>
                    <Nav.Link onClick={() => history.push("/publicarProducto")}>Vender</Nav.Link>
                    <Nav.Link>Mis compras</Nav.Link>
                  </Nav>
              </Navbar.Collapse>
            </Navbar>
          </div>
	)
}
export default NavigationBar;