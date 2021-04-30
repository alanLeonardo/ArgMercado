import React, { useState, useEffect } from 'react';
import RenderProducto from '../components/RenderProducto.jsx';
import { getProductos } from '../components/Api.jsx';
import NavigationBar from './NavigationBar.jsx';

function Home(props) {

  const [productosRender, setProductosRender] = useState([]);
  const [productosFiltrados, setProductosFiltrados] = useState([]);
  const [estanFiltrados, setEstanFiltrados] = useState(false);
 

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

    setEstanFiltrados(productosFiltrados.length > 0);
 

  }, [productosFiltrados,estanFiltrados]);

  console.log(productosRender);
  console.log(productosFiltrados);
  console.log(estanFiltrados);


  return (
    <div className="colorGray">
      <NavigationBar updateProductos={setProductosFiltrados }/>
      <RenderProducto productos={productosRender} productosFiltrados={productosFiltrados} estanFiltrados={estanFiltrados} updateProductos={ setProductosFiltrados }/>
    </div>
  )
}

export default Home;