import React from 'react';
import './App.css';
import NavigationBar from "./components/navigationBar/NavigationBar.jsx"
import Home from "./components/home/Home.jsx"
import VenderProducto from "./components/venderProducto/VenderProducto.jsx"
import NotFoundPage from './components/notFound/NotFoundPage.jsx';
import { BrowserRouter, Switch, Route } from "react-router-dom";

function App() {

 return (
 	      <div>
 	        <BrowserRouter>
 	          <>
 	           <NavigationBar/>
 			  	<Switch>
 			  	    <Route exact path="/"><Home/> </Route>
 			  		<Route exact path="/home"><Home/> </Route>
 			  		<Route exact path="/vender"><VenderProducto/> </Route>
 			  		<Route path="*"><NotFoundPage/> </Route>
 			  	</Switch>
 			  </>
 	         </BrowserRouter>
 	      </div>
   );
}

export default App;
