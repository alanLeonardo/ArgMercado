import React, { useState ,useEffect } from 'react';
import ProductoCard from "../productoCard/ProductoCard.jsx";
import {getProductos} from '../api/Api';
import {Row,Col,Container} from 'reactstrap';

function Home() {

     const [data, setData] = useState([]);

     useEffect(() => {
          const fecthData = async () => {
          const response = await getProductos()
                               .then(response => {
                                  return Promise.resolve(response);
                                })
                               .catch(error => {
                                  return Promise.reject(error);
                                })

           setData(response.data)
          }
          fecthData();
     },[])

   const renderProductos = () => {
           return data.map((p,index) => {
             return (
                renderImagenes(p)
             )
           })
   }

   const renderImagenes = (p) => {
       return p.imagenes.map((i,index) => {

          return (
            <ProductoCard key={index} file={i} titulo={p.titulo} descripcion={p.descripcion}/>
          )
       })
     }

      return (
        <div className="home">
                 <Container fluid>
                   <Row>
                     <Col sm="6">
                    {  renderProductos() }
                     </Col>
                   </Row>
                 </Container>
        </div>
      )
}

export default Home;