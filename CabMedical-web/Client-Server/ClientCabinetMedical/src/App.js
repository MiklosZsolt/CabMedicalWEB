
// import './App.css';
import LoginPage from './Components/LoginPage';
import Asistent from './Components/Asistent';
import Medic from './Components/Medic';
import './App.css';

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Admin from './Components/Admin';
import React from 'react';









function App() {
  return (
    <div className='body'>
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/asistent" element={<Asistent />} />
       <Route path="/admin" element={<Admin />} />
       <Route path="/medic" element={<Medic />} />

      </Routes>
    </Router> 
    </div> 
    );
}

export default App;
