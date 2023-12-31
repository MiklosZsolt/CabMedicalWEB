import i18n from "i18next";
import { initReactI18next } from "react-i18next";

// import ro from "./locals/ro.json";
import en from "../Components/en.json";
// import fr from "./locals/fr.json";
// import es from "./locals/es.json";

i18n
    .use(initReactI18next)
    .init({
        resources: {
            // ro: {
            //     translation: ro
            // },
            en: {
                translation: en
            },
            // fr: {
            //     translation: fr
            // },
            // es: {
            //     translation: es
            // }
        },
        // lng: "ro", 
        // fallbackLng: "ro", 
        // interpolation: {
        //     escapeValue: false
        // }
    });

export default i18n;



