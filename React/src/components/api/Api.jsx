import axios from 'axios';

axios.defaults.baseURL = "http://localhost:8080/api";
axios.defaults.timeout = 30000; // 20s
axios.defaults.headers.post['Content-Type','Accept','type'] = ['multipart/form-data','application/json','formData'];

const createUser = ({email,firstName,lastName,idCard,password}) => {
  return axios.post('/users/', {
    email: email,
    firstName: firstName,
    lastName: lastName,
    idCard: idCard,
    password: password
  })
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  });
}

const loginUser = ({email, password}) => {
  return axios.post('/login',
  {
    email: email,
    password: password
  })
  .then(response => {
    return Promise.resolve(response);
  })
  .catch(error => {
    return Promise.reject(error);
  })
}

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

export {crearProducto,getProductos,crearImagenes,crearFiles,crearImagen}