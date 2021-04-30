import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Card, CardText, CardBody,CardTitle, Button} from 'reactstrap';
import style from '../components/style.css';

function CardRender(props) {

     let producto = props.producto;
     let imagen = producto.imagenes[0];
     let url = ` data:${imagen.tipo};base64,${imagen.foto}`;

     return (
                 <div className="containerCard">
                         <Card body className='cardStyle'>
                           <img src={url}/>
                            <CardBody className='cardBody'>
                              <CardTitle>{producto.titulo}.</CardTitle>
                            </CardBody>
                         </Card>
                 </div>
     )
}

export default CardRender;