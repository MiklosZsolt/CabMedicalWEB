
import React, { useState } from 'react';
import { useHistory } from 'react-router-dom'; // import pentru history
import './LoginPage.css';
import { isEmpty } from 'lodash'; // importați funcția isEmpty



function LoginPage() {
    const [nume, setNume] = useState('');
    const [parola, setParola] = useState('');
  const [error, setError] = useState(false);
  const [Utid, setUtid] = useState("");
  
    //dconst history = useHistory(); // definirea variabilei history
  
    const handleNumeChange = (event) => {
      setNume(event.target.value);
    };
  
    const handleParolaChange = (event) => {
      setParola(event.target.value);
    };
    
  
    const handleSubmit = async (event) => {
      
      event.preventDefault();
      const response = await fetch(`http://localhost:8080/utilizatori/getRol?nume=${nume}&parola=${parola}`);
      const data = await response.json();
      console.log(data);

      const utilizatorIdResponse = await fetch(`http://localhost:8080/utilizatori/id/${nume}/${parola}`, {
    method: 'POST'
});
      const utilizatorId = await utilizatorIdResponse.text();

      localStorage.setItem("Utid", utilizatorId);
      console.log(localStorage.getItem("Utid"));


      if (data === "asistent") {
        window.location.href = "/asistent";
      } else if (data === "admin") {
        window.location.href = "/admin";
      } else if (data === "medic") {
        window.location.href = "/medic";
      }

    };
  
  
    return (

        <div className='c'>
          <br></br>
          <h1>Bine ai venit!</h1>
          <br></br>
        
          <hr></hr>
          <br></br>
          <br></br>
        <div className="login-container login-page">
          <h2>Autentificare</h2>
          {error && <p>Invalid credentials. Please try again.</p>}
          <form onSubmit={handleSubmit}>
            <label>
              Nume:
              <input type="text" name="nume" value={nume} onChange={handleNumeChange} />
            </label>
            <br />
            <label>
              Parola:
              <input type="password" name="parola" value={parola} onChange={handleParolaChange} />
            </label>
            <br />
            <button type="submit">Login</button>
          </form>
        </div>
        </div>
      );
    }
    
  
  
  export default LoginPage;
  