import React, { useState, useEffect } from 'react';
import CardRender from "../components/CardRender.jsx";
import Carousel from 'react-elastic-carousel';
import { Row, Col, Container } from 'reactstrap';
import NavBarOrden from '../components/NavBarOrden.jsx';
import '../components/style.css';
import CardHorizontal from '../components/CardHorizontal.jsx';

function RenderProducto(props) {

   const [productos, setProductos] = useState([]);
   const [productosFiltrados,setProductoFiltrados]= useState([]);
   //let productosFiltrados = props.productosFiltrados;
   const [estanFiltrados,setEstanFiltrados] = useState(false);

   useEffect(() => {
      setProductos(props.productos);
      setProductoFiltrados(props.productosFiltrados);
      setEstanFiltrados(props.estanFiltrados);
   }, [productosFiltrados,props]);

   console.log(productos);
   console.log(productosFiltrados);
   console.log(estanFiltrados);


   function obtenerCategoriasDeProductos() {
      let categorias = new Set([]);
      productos.forEach(p => categorias.add(p.categoria.name));
      return Array.from(categorias);
   }

   function esCategoria(unaCategoria, otraCategoria) {
      return unaCategoria === otraCategoria;
   }

   function obtenerProductosDeUnaCategoria(categoria) {
      let productosFiltrados = [];
      productosFiltrados = productos.filter((p) => esCategoria(categoria, p.categoria.name));
      return productosFiltrados;
   }

   function renderProductosVertical() {

      let categorias = obtenerCategoriasDeProductos()

      return categorias.map((i, index) => {
         return <div>
            <Carousel>
               {
                  obtenerProductosDeUnaCategoria(categorias[index]).map((p, index) => {

                     return (
                        <CardRender producto={p} />
                     )
                  })
               }
            </Carousel>
         </div>
      })

   }


   function renderProductosHorizontal() {
      return <Container fluid>
         <Row>
            <div>
               <NavBarOrden updateProductos={props.updateProductos} productos={productosFiltrados} />
            </div>
            <Col sm="6">
               {productosFiltrados.map((p, index) => {
                  return (
                     <CardHorizontal producto={p} />
                  )
               })
               }
            </Col>
         </Row>
      </Container>

   }

   const renderProductos = () => {

      if (estanFiltrados) {
         return renderProductosHorizontal();
      } else {
         return renderProductosVertical();
      }
   }

   return (renderProductos());
}

export default RenderProducto;