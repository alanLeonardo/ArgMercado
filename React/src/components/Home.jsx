import React, { useState ,useEffect } from 'react';
import {useHistory} from 'react-router-dom'
import RenderProducto from '../components/RenderProducto.jsx'
import {getProductos} from '../components/Api.jsx';


function Home(props) {

     let history = useHistory();
     const[productosRender, setProductosRender] = useState([]);
     let productosFiltrados = history.location.state;
     let estanFitrados = productosFiltrados.length > 0 && productosFiltrados.length < productosRender.length

     useEffect(() => {
          const fecthData = async () => {
          const response = await getProductos()
                               .then(response => {
                                  return Promise.resolve(response);
                                })
                               .catch(error => {
                                  return Promise.reject(error);
                                })

           setProductosRender(response.data)
          }
          fecthData();
     },[])

     console.log(props)

     return (
                <div className="colorGray">
                      <RenderProducto productos={productosRender} productosFiltrados={productosFiltrados} estanFitrados={estanFitrados} />
                </div>
     )
}

export default Home;