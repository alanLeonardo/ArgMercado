import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Card, CardImg, CardText, CardBody,CardTitle, Button} from 'reactstrap';

function ProductoCard({ file , titulo , descripcion}) {

        let url = ` data:${file.tipo};base64,${file.foto}`

        return (
          <div className="productoCard">
                 <Card body>
                   <img width="300" height="200" className="imagen" src={url}/>
                    <CardBody>
                      <CardTitle>{titulo}.</CardTitle>
                      <CardText>{descripcion}.</CardText>
                      <Button>Comprar</Button>
                    </CardBody>
                  </Card>
          </div>
        );
}

export default ProductoCard;