import axios from "axios";
import React, { useState, useEffect } from "react";
import { DataGrid } from '@mui/x-data-grid';
import { Button } from 'react-bootstrap';
import { fromPairs } from "lodash";
import './LoginPage.css';
import {I18nextProvider, useTranslation} from "react-i18next";
import i18n from './i18n';






function Admin() {
  const [utilizatori, setUtilizatori] = useState([]);
  const handleDelete = (id) => {
    axios.post(`http://localhost:8080/admin/delete/${id}`).then(response => {
      console.log(response.data);
      setUtilizatori(utilizatori.filter(user => user.id !== id));
    });
  };
const [id, setId] = useState("");
const [cnp, setCnp] = useState("");
const [nume, setNume] = useState("");
const [parola, setParola] = useState("");
const [rol, setRol] = useState("");
const [specialitate, setSpecialitate] = useState("");
const [filteredPacienti, setFilteredPacienti] = useState([]);
const [showTable, setShowTable] = useState(false); // Adaugă această linie pentru a declara starea showTable
const [filterOption, setFilterOption] = useState("");
const [filterText, setFilterText] = useState("");
const { t } = useTranslation();




  const [selectedId, setSelectedId] = useState(null);


const handleInsert = () => {
  axios.post("http://localhost:8080/admin/add", {
    cnp: cnp,
    nume: nume,
    parola: parola,
    rol: rol,
    specialitate: specialitate,
  })
  .then(response => {
    console.log(response.data);
    setUtilizatori([...utilizatori, response.data]);
    
  })
  .catch(error => {
    console.log(error);
    alert('Something is wrong!');
  });

};

const handleUpdate = () => {
  axios
    .post(`http://localhost:8080/admin/updatenew/${id}`, {
      id: id,
      cnp: cnp,
      nume: nume,
      parola: parola,
      rol: rol,
      specialitate: specialitate,
    })
    .then(response => {
      console.log(response.data);
      setUtilizatori(utilizatori.map(user => {
        if (user.id === selectedId) {
          return response.data;
        } else {
          return user;
        }
      }));
      setSelectedId(null);
      window.location.reload(); // Reîncarcă pagina
    })
    .catch(error => {
      console.log(error);
      window.alert('A apărut o eroare la actualizarea utilizatorului.'); // Afișează un alert cu mesajul de eroare
    });
};



const handleFilterSubmit = (e) => {
  e.preventDefault();

  axios
    .post(`http://localhost:8080/admin/by-rol/${filterOption}`)
    .then(response => {
      setFilteredPacienti(response.data);
      console.log(response.data)
      setShowTable(true); // Set showTable to true to display the table after getting the results
    })
    .catch(error => {
      console.log(error);
    });
};


  
  
  

  useEffect(() => {
    axios.get("http://localhost:8080/admin/getUsers").then(response =>{
      setUtilizatori(response.data)
      console.log(response.data);

    })


  }, []);
  const columns = [
    { field: 'id', headerName: 'ID', width: 90 },
    {
      field: 'cnp',
      headerName: 'CNP',
      width: 150,
      editable: true,
    },
    {
      field: 'nume',
      headerName: 'Nume',
      width: 150,
      editable: true,
    },
    {
      field: 'rol',
      headerName: 'Rol',
      type: 'number',
      width: 110,
      editable: true,
    },
    {
      field: 'specialitate',
      headerName: 'Specializare',
      description: 'This column has a value getter and is not sortable.',
      sortable: false,
      width: 160,
      valueGetter: (params) =>
        `${params.row.firstName || ''} ${params.row.lastName || ''}`,
    },
    {
      field: 'delete',
      headerName: 'Delete',
      width: 90,
      renderCell: (params) => (
        <Button variant="contained" color="secondary" onClick={() => handleDelete(params.row.id)}>
          Delete
        </Button>
      ),
    },
 
  ];
  
    
  function handleLanguageChange(language) {
    i18n.changeLanguage(language);
  }

  return (
    <I18nextProvider i18n={i18n}>
      <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                <button onClick={() => { handleLanguageChange('en') }}>EN</button>
                
            </div>
  
      <div className="c">
        <h1>Bine ai venit!</h1>
        <hr></hr>
    <div className="l">
      <h2>Lista utilizatorilor:</h2>
  
  <DataGrid
        rows={utilizatori}
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
    <label style={{ marginBottom: 5 }}>Rol:</label>
    <input type="text" value={rol} onChange={(e) => setRol(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 0 }}>
    <label style={{ marginBottom: 5 }}>Parola:</label>
    <input type="text" value={parola} onChange={(e) => setParola(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 0 }}>
    <label style={{ marginBottom: 5 }}>Specializare:</label>
    <input type="text" value={specialitate} onChange={(e) => setSpecialitate(e.target.value)} />
  </div>
  <div style={{ display: 'flex', flexDirection: 'column', marginBottom: 10 }}>
    <label style={{ marginBottom: 5 }}>CNP:</label>
    <input type="text" value={cnp} onChange={(e) => setCnp(e.target.value)} />
  </div>
  <div style={{ display: 'flex', justifyContent: 'center', marginTop: 0 }}>
    <Button variant="contained" color="primary" style={{ marginRight: 10 }} onClick={handleInsert}>
      Insert
    </Button>
    <Button variant="contained" color="primary" onClick={handleUpdate}>
      Update
    </Button>
  </div>
</form>
<form onSubmit={handleFilterSubmit} >
<select value={filterOption} onChange={(e) => setFilterOption(e.target.value)}>
          <option value="">-- {t('Alege o opțiune --')}</option>
          <option value="medic">{t('Medic')}</option>
          <option value="asistent">Asistent</option>
          <option value="admin">admin</option>
        </select>
        <button type="submit">Trimite</button>
      </form>
      {showTable && (
  <div className="table-container">
    <table className="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>{t('Medic')}</th>
          <th>Rol</th>
          <th>Specializare</th>
          <th>CNP</th>
        </tr>
      </thead>
      <tbody>
        {filteredPacienti.map((utilizator) => (
          <tr key={utilizator.id}>
            <td>{utilizator.id}</td>
            <td>{utilizator.nume}</td>
            <td>{utilizator.rol}</td>
            <td>{utilizator.specialitate}</td>
            <td>{utilizator.cnp}</td>
          </tr>
        ))}
      </tbody>
    </table>
  </div>
)}

    </div>
    
  </div>
  </div>


  </I18nextProvider>
  );
}


export default Admin;
