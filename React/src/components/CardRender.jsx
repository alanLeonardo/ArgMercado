import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Card, CardText, CardBody,CardTitle, Button} from 'reactstrap';
import {vender} from '../components/Api';
import {useHistory} from 'react-router-dom'
import style from '../components/style.css'

function CardRender(props) {

     let history = useHistory();
     let producto = props.producto
     let imagen = props.producto.imagenes[0]
     let url = ` data:${imagen.tipo};base64,${imagen.foto}`

     function handleComprar(e) {
        e.preventDefault();
        producto.stock = producto.stock - 1

        vender({ imagen })
               .then((response) => { console.log(response)
               })
               .catch(error => error);
               history.push("/home")
     }

     return (
                 <div className="containerCard">
                         <Card body className='card'>
                           <img className="img" src={url}/>
                            <CardBody className='cardBody'>
                              <CardTitle>{producto.titulo}.</CardTitle>
                              <CardText>Stock: {producto.stock}.</CardText>
                              <CardText style={{color:"blue"}} >Precio: {producto.precio}.</CardText>
                               {imagen.stock > 0
                                   ?  <CardText style={{ background:'red'}}>Producto Sin StocK por el Momento.</CardText>
                                   :  <Button onClick={(e) => handleComprar(e)}>Comprar</Button>
                               }
                            </CardBody>
                         </Card>
                 </div>
     )
}

export default CardRender;