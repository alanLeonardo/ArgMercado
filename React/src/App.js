import React from 'react';
import './App.css';
import NavigationBar from "./components/NavigationBar.jsx"
import Home from "./components/Home.jsx"
import Login from "./components/Login.jsx"
import Register from "./components/Register.jsx"
import VenderProducto from "./components/VenderProducto.jsx"
import NotFoundPage from './components/notFound/NotFoundPage.jsx';
import { BrowserRouter, Switch, Route } from "react-router-dom";

function App() {

 return (
 	     <>
         	  <BrowserRouter>
         	   <>
             	<NavigationBar />
         		<Switch>
         		  	<Route exact path="/"> <Home/> </Route>
         		  	<Route exact path="/home"> <Home/> </Route>
         		  	<Route exact path="/login"> <Login/> </Route>
         		  	<Route exact path="/register"> <Register/> </Route>
         		  	<Route exact path="/publicarProducto"> <VenderProducto /> </Route>
         		</Switch>
         	   </>
         	  </BrowserRouter>
         </>
   );
}

export default App;
