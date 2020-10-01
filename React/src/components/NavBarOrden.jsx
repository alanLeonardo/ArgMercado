import React, { useState ,useEffect } from 'react';
import {getProductosOrdenadosDeMenorAMayor,getProductosOrdenadosDeMayorAMenor} from '../components/Api.jsx';
import {Navbar, Nav,NavDropdown} from 'react-bootstrap';
import style from '../components/style.css'

function NavBarOrden(props) {

  const [productosOrdenadosDeMenorAMayor, setProductosOrdenadosDeMenorAMayor] = useState([]);
  const [productosOrdenadosDeMayorAMenor, setProductosOrdenadosDeMayorAMenor] = useState([]);

  useEffect(() => {
        const fecthDataOrdenadaDeMenorAMayor = async () => {
        const response = await getProductosOrdenadosDeMenorAMayor()
                            .then(response => {
                               return Promise.resolve(response);
                            })
                            .catch(error => {
                               return Promise.reject(error);
                            })
                     setProductosOrdenadosDeMenorAMayor(response.data)
                    }
          fecthDataOrdenadaDeMenorAMayor();

          const fecthDataOrdenadaDeMayorAMenor = async () => {
                    const response = await getProductosOrdenadosDeMayorAMenor()
                                        .then(response => {
                                           return Promise.resolve(response);
                                        })
                                        .catch(error => {
                                           return Promise.reject(error);
                                        })
                                 setProductosOrdenadosDeMayorAMenor(response.data)
                    }
          fecthDataOrdenadaDeMayorAMenor();
  },[])

     function handleChangeMasRelevante(e) {
       e.preventDefault();
       props.updateProductos(productosOrdenadosDeMayorAMenor)

     }
     function handleChangeMenosRelevante(e) {
        e.preventDefault();
        props.updateProductos(productosOrdenadosDeMenorAMayor)


     }

return(
   <div className="navBarOrder" >
    <h6 className="paddingCero"> Ordenar publicaciones</h6>
     <Navbar className="paddingCero" collapseOnSelect expand="lg" variant="dark">
       <Navbar.Toggle aria-controls="responsive-navbar-nav" />
       <Navbar.Collapse id="responsive-navbar-nav">
         <Nav className="mr-auto">
           <NavDropdown title="Mas relevates" id="collasible-nav-dropdown">
             <NavDropdown.Item onClick={(e) => handleChangeMenosRelevante(e)}> Menor precio </NavDropdown.Item>
             <NavDropdown.Item onClick={(e) => handleChangeMasRelevante(e)}> Mayor precio </NavDropdown.Item>
           </NavDropdown>
         </Nav>
       </Navbar.Collapse>
     </Navbar>
   </div>
      )
}

export default NavBarOrden;