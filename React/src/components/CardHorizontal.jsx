import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import style from '../components/style.css';

function CardRender(props) {

    let producto = props.producto;
    let imagen = producto.imagenes[0];
    let url = ` data:${imagen.tipo};base64,${imagen.foto}`;

    return (
        <div className="container-fluid" style={{width: '60rem'}}>
            <div className="row">
                <div className="col-12 mt-3">
                    <div className="card">
                        <div className="card-horizontal">
                            <div className="img-square-wrapper">
                                <img src={url}/>
                    </div>
                                <div className="card-body">
                                    <h4 className="card-title">{producto.titulo}</h4>
                                    <p className="card-text">Descripcion: {producto.descripcion}.</p>
                                    <p className="card-text">Precio: {producto.precio}.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    );
}

export default CardRender;




