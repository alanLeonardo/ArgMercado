import axios from 'axios';

//axios.defaults.baseURL = "http://localhost:8080/api";
axios.defaults.baseURL = "https://arg-mercado-backend-api-rest.herokuapp.com/api"
axios.defaults.timeout = 30000; // 20s
axios.defaults.headers.post['Content-Type','Accept','type'] = ['multipart/form-data','application/json','formData'];

const crearProducto = (producto) => {
  return axios.post('/publicarProducto', producto)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}

const crearImagen = ({nombre,tipo,foto,idOwner}) => {
  return axios.post('/imagen', {
    nombre: nombre,
    tipo: tipo,
    foto: foto,
    idOwner: idOwner

  })
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}


const crearImagenes = ({imagenes}) => {
  return axios.post('/publicarProducto', {
    imagenes: imagenes,
  })
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}

const getProducto = (idProducto) => {
  return axios.get(`/producto/${idProducto}`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

const getProductos = () => {
  return axios.get(`/productos`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

const crearFiles = (files) => {
  return axios.post('/newFiles',files)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}

const getProductosOrdenadosDeMenorAMayor = () => {
  return axios.get(`/productosOrdenadosDeMenorAMayor`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

const getProductosOrdenadosDeMayorAMenor = () => {
  return axios.get(`/productosOrdenadosDeMayorAMenor`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

const vender = (producto) => {
  return axios.put('/comprarProducto',producto)
  .then(response => {
     return Promise.resolve(response);
  })
  .catch(error => {
     return Promise.reject(error);
  });
}

const registarUsuario = ({nombreCompleto,email,contraseña}) => {
  return axios.post('/registrarUsuario', {
    nombreCompleto: nombreCompleto,
    email: email,
    contraseña: contraseña,
  })
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}


const iniciarSeccion = (email,contraseña) => {
  return axios.get(`/iniciarSeccion/${email}/${contraseña}`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

const getCategorias = () => {
  return axios.get(`/categorias`)
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

export {crearProducto,getProductos,crearImagenes,crearFiles,crearImagen,getProductosOrdenadosDeMenorAMayor,getProductosOrdenadosDeMayorAMenor,vender,registarUsuario,iniciarSeccion,getCategorias}