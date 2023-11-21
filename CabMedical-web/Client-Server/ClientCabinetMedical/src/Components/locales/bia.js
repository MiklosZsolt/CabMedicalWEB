import { useState,  useEffect } from "react";
import api from "../Api";
import "./AdaugareUtilizator.css";
import {I18nextProvider, useTranslation} from "react-i18next";
import i18n from '../i18n';

export default function ActualizareCarte() {
    const { t } = useTranslation();
    const [carti, setCarti] = useState([]);
    const [libraryId, setLibraryId] = useState(null);

  useEffect(() => {
    const storedLibraryId = localStorage.getItem('libraryId');
    setLibraryId(storedLibraryId);
  }, []);

  useEffect(() => {
    async function fetchCarti() {
      try {
        const response = await api.get("http://localhost:8080/getCarti");
        setCarti(response.data);
      } catch (error) {
        console.error(error);
      }
    }
    fetchCarti();
  }, []);
  const [carteData, setCarteData] = useState({
    idCarteLibrarie: "",
    pret: "",
    disponibil: "",
    editura: "",
    domeniu: "",
    titlu: "",
    autor: "",
    idLibrarie: "",
  });

  const handleSubmit = async () => {
    try {
      const response = await api.put("http://localhost:8080/updateBook", {
        idCarteLibrarie: carteData.idCarteLibrarie,
        pret: carteData.pret,
        disponibil: carteData.disponibil,
        editura: carteData.editura,
        domeniu: carteData.domeniu,
        titlu: carteData.titlu,
        autor: carteData.autor,
        idLibrarie: carteData.idLibrarie,
      });
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  function handleLanguageChange(language) {
    i18n.changeLanguage(language);
  }

  return (
    <I18nextProvider i18n={i18n}>
    <div id="adminPage">
      <div className="text" style={{ width: "50%" }}>
        <h2 className='titlu'>{t('Actualizare carte')}</h2>
      </div>
      <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                <button onClick={() => { handleLanguageChange('en') }}>EN</button>
                <button onClick={() => { handleLanguageChange('ro') }} style={{ backgroundColor: 'yellow'}} > RO</button>
                <button onClick={() => { handleLanguageChange('es') }} style={{ backgroundColor: 'pink'}}>ES</button>
                <button onClick={() => { handleLanguageChange('fr') }} style={{ backgroundColor: 'red'}}>FR</button>
            </div>
      <div className="card" id="addCarteCard" style={{ width: "18rem" }}>
        <ul className="list-group list-group-flush">
          <div className="form-floating mb-3">
            <select
              className="form-select"
              id="idCarteLibrarie"
              value={carteData.idCarteLibrarie}
              onChange={(e) =>
                setCarteData({
                  ...carteData,
                  idCarteLibrarie: parseInt(e.target.value),
                })
              }
            >
              <option value="">{t('Select carte')}</option>
              {carti.map((carte) => (
                <option key={carte.idCarteLibrarie} value={carte.idCarteLibrarie}>
                  {carte.idCarteLibrarie}
                </option>
              ))}
            </select>
            <label htmlFor="idCarteLibrarie">
              <strong>{t('ID Carte Librarie')}</strong>
            </label>
          </div>

          <div className="form-floating mb-3">
            <input
              type="number"
              className="form-control"
              id="pret"
              value={carteData.pret}
              onChange={(e) =>
                setCarteData({ ...carteData, pret: e.target.value })
              }
              required={true}
            />
            <label htmlFor="pret">
              <strong>{t('Pret')}</strong>
            </label>
          </div>

          <div className="form-floating mb-3">
            <input
              type="number"
              className="form-control"
              id="disponibil"
              value={carteData.disponibil}
              onChange={(e) =>
                setCarteData({ ...carteData, disponibil: e.target.value })
              }
              required={true}
            />
            <label htmlFor="disponibil">
              <strong>{t('Disponibil')}</strong>
            </label>
          </div>

          <div className="form-floating mb-3">
            <input
              type="text"
              className="form-control"
              id="editura"
              value={carteData.editura}
              onChange={(e) =>
                setCarteData({ ...carteData, editura: e.target.value })
              }
                required={true}
                />
                <label htmlFor="Editura">
                  <strong>{t('Editura')}</strong>
                </label>
              </div>
              <div className="form-floating mb-3">
        <input
          type="text"
          className="form-control"
          id="domeniu"
          value={carteData.domeniu}
          onChange={(e) =>
            setCarteData({ ...carteData, domeniu: e.target.value })
          }
          required={true}
        />
        <label htmlFor="domeniu">
          <strong>{t('Domeniu')}</strong>
        </label>
      </div>

      <div className="form-floating mb-3">
        <input
          type="text"
          className="form-control"
          id="titlu"
          value={carteData.titlu}
          onChange={(e) =>
            setCarteData({ ...carteData, titlu: e.target.value })
          }
          required={true}
        />
        <label htmlFor="titlu">
          <strong>{t('Titlu')}</strong>
        </label>
      </div>

      <div className="form-floating mb-3">
        <input
          type="text"
          className="form-control"
          id="autor"
          value={carteData.autor}
          onChange={(e) =>
            setCarteData({ ...carteData, autor: e.target.value })
          }
          required={true}
        />
        <label htmlFor="autor">
          <strong>{t('Autor')}</strong>
        </label>
      </div>

      <div className="form-floating mb-3">
        <input
          type="text"
          className="form-control"
          id="autor"
          value={libraryId} disabled
          onChange={(e) =>
            setCarteData({ ...carteData, autor: e.target.value })
          }
          required={true}
        />
            <label htmlFor="idLibrarie">
              <strong>{t('ID Librarie')}</strong>
            </label>
      </div>
    </ul>
    <button className="btn btn-success" onClick={handleSubmit}>{t('Actualizare carte')}</button>
  </div>
  <button className="adaugare" onClick={() => { window.location.href = '/Angajat' }}>{t('inapoiButton')}</button>
</div>
</I18nextProvider>
);
}