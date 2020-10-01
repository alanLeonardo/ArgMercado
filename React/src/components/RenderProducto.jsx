import React, {useState ,useEffect} from 'react';
import CardRender from "../components/CardRender.jsx";
import Carousel from 'react-elastic-carousel';
import {Row,Col,Container} from 'reactstrap';
import NavBarOrden from '../components/NavBarOrden.jsx'
import style from '../components/style.css'

function RenderProducto(props) {

   const[productos, setProductos] = useState([]);
   let productosFiltrados = props.productosFiltrados
   let estanFitrados = props.estanFitrados

   useEffect(() => {
      setProductos(props.productos)

   })

   function obtenerCategoriasDeProductos() {
      let categorias = new Set([]);
      productos.forEach(p => categorias.add(p.categoria))
      return Array.from(categorias)
   }

   function esCategoria(unaCategoria,otraCategoria) {
      return  unaCategoria === otraCategoria
   }

   function obtenerProductosDeUnaCategoria(categoria) {
        let productosFiltrados = []
        productosFiltrados = productos.filter((p) => esCategoria(categoria,p.categoria))
        return productosFiltrados
   }

   function renderProductosVertical() {

      let categorias = obtenerCategoriasDeProductos()

      return categorias.map((i,index) => {
                      return  <div>
                                  <Carousel>
                                 {
                                    obtenerProductosDeUnaCategoria(categorias[index]).map((p,index) => {

                                       return(
                                               <CardRender producto={p}/>
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
                      <div className="cardVertical" >
                        <NavBarOrden updateProductos={setProductos}/>
                      </div>
                       <Col sm="6" className="cardHorizonal">
                           {productosFiltrados.map((p,index) => {
                                              return (
                                                 <CardRender producto={p}/>
                                                      )
                                              })
                           }
                       </Col>
                   </Row>
               </Container>

  }

  const renderProductos = () => {

       if(estanFitrados) {
         return renderProductosHorizontal()
      } else {
         return renderProductosVertical()
      }
  }

   return (renderProductos())
}

export default RenderProducto;