import axios from "axios";
import React, { useState, useEffect} from "react";
import './form.css';
// import React from 'react';

function Medic() {
  const [filteredResults, setFilteredResults] = useState([]); // Adaugă această linie pentru a declara starea filteredResults
  const [showTable, setShowTable] = useState(false); // Adaugă această linie pentru a declara starea showTable
  const [pacienti, setPacienti] = useState([]); // Adăugat declararea stării pacienti
  
  const [filteredPacienti, setFilteredPacienti] = useState([]);
const [filterOption, setFilterOption] = useState("");
const [filterText, setFilterText] = useState("");
useEffect(() => {
    axios
      .post(`http://localhost:8080/asistent/medicid/${localStorage.getItem("Utid")}`)
      .then(response => {
        setFilteredPacienti(response.data);
        console.log(localStorage.getItem("Utid"));
        console.log(response.data)
        setShowTable(true); // Set showTable to true to display the table after getting the results
      })
      .catch(error => {
        console.log(error);
      });
}, []);

const handleFilterSubmit = (e) => {
  e.preventDefault();

  axios
    .post(`http://localhost:8080/medic/${filterOption}`, { [filterOption]: filterText })
    .then(response => {
      if (Array.isArray(response.data)) {
        setFilteredPacienti(response.data);
        setShowTable(true); // Set showTable to true to display the table after getting the results
      } else {
        // Date invalide returnate
        alert('Date invalide returnate de server.');
      }
    })
    .catch(error => {
      console.log(error);
    });
};





  return (
    <div><h1 className="c">Bine ai venit</h1>
    <hr></hr>
    <div>
      <h2>Lista pacientiilor!</h2>
      
  
      {showTable && (
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Id</th>
                <th>Nume</th>
                <th>Diagnostic</th>
                <th>Tratament</th>
                <th>Simptome</th>
              </tr>
            </thead>
            <tbody>
              {filteredPacienti.map((pacient) => (
                <tr key={pacient[0]}>
                  {pacient.map((value, index) => (
                    <td key={index}>{value}</td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
      <div>
      <form onSubmit={handleFilterSubmit} >
        <select value={filterOption} onChange={(e) => setFilterOption(e.target.value)}>
          <option value="">-- Alege o opțiune --</option>
          <option value="tratament">Tratament</option>
          <option value="nume">nume</option>
          <option value="diagnostic">Diagnostic</option>

        </select>
        <input
          type="text"
          value={filterText}
          onChange={(e) => setFilterText(e.target.value)}
          placeholder="Scrie ceva..."
        />
        <button type="submit">Trimite</button>
      </form>
      </div>
    </div>
    </div>
  );
  
  
  
}

export default Medic;