import axios from "axios";
import React, { useState, useEffect, useRef } from "react";
import { DataGrid } from '@mui/x-data-grid';
import { Button } from 'react-bootstrap';
import './form.css';
import { isEmpty } from "lodash";
import Chart from 'chart.js/auto';





function Asistent() {
  const [ageData, setAgeData] = useState(null);
  const [filteredResults, setFilteredResults] = useState([]); // Adaugă această linie pentru a declara starea filteredResults
  const [showTable, setShowTable] = useState(false); // Adaugă această linie pentru a declara starea showTable
  const [pacienti, setPacienti] = useState([]); // Adăugat declararea stării pacienti
  const handleDelete = (id) => {
    axios.post(`http://localhost:8080/asistent/delete/${id}`).then(response => {
      console.log(response.data);
      setPacienti(pacienti.filter(user => user.id !== id));
    });
  };

const [id, setId] = useState("");
const [cnp, setCnp] = useState("");
const [nume, setNume] = useState("");
const [varsta, setVarsta] = useState("");
const [selectedId, setSelectedId] = useState(null);

const [filteredPacienti, setFilteredPacienti] = useState([]);
const [filterOption, setFilterOption] = useState("");
const [filterText, setFilterText] = useState("");



const handleInsert = () => {
  axios.post("http://localhost:8080/asistent/addPatient", {
    cnp: cnp,
    nume: nume,
    varsta: varsta,
  })
  .then(response => {
    console.log(response.data);
    setPacienti([...pacienti, response.data]);
    alert('Pacient adaugat cu succes!');
  })
  .catch(error => {
    console.log(error);
    alert('Something went wrong!');
  });
};

const handleUpdate = () => {
  axios.post(`http://localhost:8080/asistent/update/${id}`, {
    id: id,
    cnp: cnp,
    nume: nume,
    varsta: varsta,
  })
  .then(response => {
    console.log(response.data);
    setPacienti(pacienti.map(pacient => {
      if (pacient.id === selectedId) {
        return response.data;
      } else {
        return pacient;
      }
    }));
    setSelectedId(null);
    window.location.reload(); // Actualizează pagina
  })
  .catch(error => {
    console.log(error);
    alert('A apărut o eroare la actualizare.');
  });
};


  useEffect(() => {
    axios.get("http://localhost:8080/asistent/getPatients")
      .then(response => {
        setPacienti(response.data);
        console.log(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);


  useEffect(() => {
    axios.get("http://localhost:8080/asistent/varste")
      .then(response => {
        // Prelucrarea datelor pentru a obține numărul de vârste distincte și frecvența acestora
        const data = response.data.reduce((acc, item) => {
          const ageGroup = item;
          const existingItem = acc.find(entry => entry.ageGroup === ageGroup);
          if (existingItem) {
            existingItem.percentage += 1; // Incrementați frecvența existentă
          } else {
            acc.push({ ageGroup: ageGroup, percentage: 1 }); // Adăugați o nouă intrare cu frecvența 1
          }
          return acc;
        }, []);
        
        setAgeData(data);
        console.log(data); // Verificați datele prelucrate
      })
      .catch(error => {
        console.log(error);
      });
  }, []);
  


  const AgeStatisticsChart = ({ ageData }) => {
    const chartRef = useRef(null);
  
    useEffect(() => {
      if (ageData && chartRef.current) {
        const labels = ageData.map((item) => item.ageGroup);
        const frequencies = ageData.map((item) => item.percentage);
  
        // Verificați dacă există deja un grafic pe elementul canvas și distrugeți-l înainte de a crea unul nou
        if (chartRef.current.chart) {
          chartRef.current.chart.destroy();
        }
  
        const ctx = chartRef.current.getContext('2d');
        chartRef.current.chart = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [
              {
                label: 'Procentaj',
                data: frequencies,
                backgroundColor: 'rgba(75, 192, 192, 0.6)',
              },
            ],
          },
          options: {
            responsive: true,
          },
        });
      }
    }, [ageData]);
  
    return (
      <canvas
        ref={chartRef}
        style={{ maxHeight: '400px' }} // Setează înălțimea maximă a canvas-ului
      ></canvas>
    );
  };
  
  
  const handleFilterSubmit = (e) => {
    e.preventDefault();

    axios
  .post(`http://localhost:8080/asistent/${filterOption}`, { [filterOption]: filterText })
  .then(response => {
    if (isEmpty(response.data)) {
      alert('Date invalide !'); // Display the alert when the response data is empty
    } else {
      setFilteredPacienti(response.data);
      setShowTable(true); // Set showTable to true to display the table after getting the results
    }
    console.log(response.data);
  })
  .catch(error => {
    console.log(error);
  });
  };

  const handleSaveFile = (e) => {
    e.preventDefault();
    console.log(pacienti);
  
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(pacienti)
    };
  
    fetch(`http://localhost:8080/saveFile/patients/${filterOption}`, requestOptions)
      .then(response => {
        if (response.ok) {
          console.log('Salvare cu succes');
        } else {
          console.log('Eroare la salvare');
        }
      })
      .catch(error => {
        console.log('Eroare la salvare:', error);
      });
  };
  
  
  
  
  
  
  
 
  
  

  
  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    
    {
      field: 'nume',
      headerName: 'Nume',
      width: 150,
      editable: true,
    },
    {
      field: 'varsta',
      headerName: 'Varsta',
      description: 'This column has a value getter and is not sortable.',
      sortable: false,
      width: 160,
      valueGetter: (params) =>
        `${params.row.firstName || ''} ${params.row.lastName || ''}`,valueGetter: (params) => params.row.varsta,

    },
    {
      field: 'cnp',
      headerName: 'CNP',
      width: 150,
      editable: true,
    },
    {
      field: 'delete',
      headerName: 'Delete',
      width: 90,
      renderCell: (params) => (
        <Button variant="contained" color="secondary" onClick={() => handleDelete(params.row.id)}>
          Sterge
        </Button>
      ),
    },
  ];

  

  return (
    <div><h1 className="c">Bine ai venit!</h1>
    <hr></hr>
    <div >
      <h2>Lista de pacienti:</h2>
      <DataGrid
        rows={pacienti}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        pageSizeOptions={[5]}
        checkboxSelection
        disableRowSelectionOnClick
      />
    <div>
      <form style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 0 }}>
    <label style={{ marginBottom: 5 }}>Id:</label>
    <input type="text" value={id} onChange={(e) => setId(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 0 }}>
    <label style={{ marginBottom: 5 }}>Nume:</label>
    <input type="text" value={nume} onChange={(e) => setNume(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 0 }}>
    <label style={{ marginBottom: 5 }}>CNP:</label>
    <input type="text" value={cnp} onChange={(e) => setCnp(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 10 }}>
    <label style={{ marginBottom: 5 }}>Varsta:</label>
    <input type="text" value={varsta} onChange={(e) => setVarsta(e.target.value)} />
  </div>
  <div style={{ display: 'flex', justifyContent: 'center', marginTop: 0 }}>
    <Button variant="contained" color="primary" style={{ marginRight: 10 }} onClick={handleInsert}>
      Adauga
    </Button>
    <Button variant="contained" color="primary" onClick={handleUpdate}>
      Actualizeaza
    </Button>
  </div>
</form>
<form onSubmit={handleFilterSubmit}>
        <select value={filterOption} onChange={(e) => setFilterOption(e.target.value)}>
          <option value="">-- Alege o opțiune --</option>
          <option value="Medic">Medic</option>
          <option value="diagnostic">Diagnostic</option>
          <option value="varsta">Varsta</option>
          <option value="nume">Nume</option>
        </select>
        <input
          type="text"
          value={filterText}
          onChange={(e) => setFilterText(e.target.value)}
          placeholder="Scrie ceva..."
        />
        <button type="submit">Trimite</button>
      </form>
      <form onSubmit={handleSaveFile}>
        <select value={filterOption} onChange={(e) => setFilterOption(e.target.value)}>
          <option value="">-- Alege o opțiune --</option>
          <option value="txt">TXT</option>
          <option value="json">JSON</option>

        </select>
        <button type="submit">Salveaza</button>
        </form>
        
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
      <div>   <AgeStatisticsChart ageData={ageData} />
 </div>
    </div>
  </div>
  </div>
  );
}

export default Asistent;
