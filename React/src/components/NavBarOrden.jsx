import React, { useState, useEffect } from 'react';
import { Navbar, Nav, NavDropdown } from 'react-bootstrap';
import '../components/style.css'

function NavBarOrden(props) {

   const [productos, setProductos] = useState([]);
   const [orderState, setOrderState] = useState("");

   useEffect(() => {
      setProductos(props.productos);

      if(orderState === "MasRelevante") {
         setProductos(sortMasRelevante());
         props.updateProductos(productos);
         setOrderState("");
      }

      if(orderState === "MenosRelevante") {
         setProductos(sortMenosRelevante());
         props.updateProductos(productos);
         setOrderState("");
      }  
   }, [orderState,props.updateProductos,productos]);

   function handleChangeMasRelevante(e) {
      e.preventDefault();
      setOrderState("MasRelevante");
   }

   function handleChangeMenosRelevante(e) {
      e.preventDefault();
      setOrderState("MenosRelevante");
   }

   function sortMasRelevante() {
      
      productos.sort(function (a, b) {
         if (a.precio < b.precio) {
            return 1;
         }
         if (a.precio > b.precio) {
            return -1;
         }
         return 0;
      });
      return productos;
   }


   function sortMenosRelevante() {

      productos.sort(function (a, b) {
         if (a.precio > b.precio) {
            return 1;
         }
         if (a.precio < b.precio) {
            return -1;
         }

         return 0;
      });
      return productos;
   }

   console.log(productos);
   console.log(orderState);

   return (
      <div className="navOrder" >
         <Navbar className="paddingCero" collapseOnSelect expand="lg" variant="dark">
            <Navbar.Brand > Ordenar por </Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
               <Nav className="mr-auto">
                  <NavDropdown title="Mas relevates" id="collasible-nav-dropdown">
                     <NavDropdown.Item onClick={(e) => handleChangeMenosRelevante(e)}>  Menor precio </NavDropdown.Item>
                     <NavDropdown.Item onClick={(e) => handleChangeMasRelevante(e)}> Mayor precio </NavDropdown.Item>
                  </NavDropdown>
               </Nav>
            </Navbar.Collapse>
         </Navbar>
      </div>
   )
}

export default NavBarOrden;