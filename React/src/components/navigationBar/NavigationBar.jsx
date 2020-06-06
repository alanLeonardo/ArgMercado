// Componente React que representa a la Barra de Navegacion.
import React from 'react';
import {Navbar, Nav, Button} from 'react-bootstrap';
import {useHistory} from 'react-router-dom'


function NavigationBar() {

   		let history = useHistory();

	return (
		<div>
			<Navbar bg="dark" expand="lg" variant="dark">
				<Navbar.Brand>ArgMercado</Navbar.Brand>
				<Navbar.Toggle aria-controls="responsive-navbar-nav" />
				<Navbar.Collapse id="responsive-navbar-nav">
					<Nav className="mr-auto">
          				<ul className="navbar-nav">
            				<li className="nav-item">
							</li>
         				</ul>
					</Nav>
					<Button className="btn-primary" onClick={() => history.push("/vender")}>VenderProducto</Button>
				</Navbar.Collapse>
			</Navbar>
		</div>
	)
}
export default NavigationBar;