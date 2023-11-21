// import { useEffect, useRef } from 'react';
// import { Chart } from 'chart.js';

// const AgeStatisticsChart = ({ ageData }) => {
//   const chartRef = useRef(null);

//   useEffect(() => {
//     if (ageData && chartRef.current) {    
//       const pacientiVarste = ageData.map(pacient => pacient.varsta);
//       const totalPacienti = pacientiVarste.length;

//       // Calculăm numărul de pacienți în fiecare grupă de vârstă
//       const group0_18 = pacientiVarste.filter(age => age >= 0 && age <= 18).length;
//       const group18_50 = pacientiVarste.filter(age => age > 18 && age <= 50).length;
//       const group50_100 = pacientiVarste.filter(age => age > 50 && age <= 100).length;

//       // Calculăm procentele pentru fiecare grupă de vârstă
//       const percentage0_18 = ((group0_18 / totalPacienti) * 100).toFixed(2);
//       const percentage18_50 = ((group18_50 / totalPacienti) * 100).toFixed(2);
//       const percentage50_100 = ((group50_100 / totalPacienti) * 100).toFixed(2);

//       const ctx = chartRef.current.getContext('2d');
//       new Chart(ctx, {
//         type: 'pie',
//         data: {
//           labels: ['0-18 ani', '18-50 ani', '50-100 ani'],
//           datasets: [{
//             data: [percentage0_18, percentage18_50, percentage50_100],
//             backgroundColor: ['rgba(75, 192, 192, 0.6)', 'rgba(255, 99, 132, 0.6)', 'rgba(54, 162, 235, 0.6)'],
//           }],
//         },
//         options: {
//           responsive: true,
//         },
//       });
//     }
//   }, [ageData]);

//   return <canvas ref={chartRef}></canvas>;
// };

// export default AgeStatisticsChart;
import { Chart } from 'chart.js';

const AgeStatisticsChart = ({ ageData }) => {
  const chartRef = useRef(null);

  useEffect(() => {
    if (ageData && chartRef.current) {
      const labels = ageData.map(item => item.ageGroup);
      const percentages = ageData.map(item => item.percentage);

      const ctx = chartRef.current.getContext('2d');
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: labels,
          datasets: [{
            label: 'Procentaj',
            data: percentages,
            backgroundColor: 'rgba(75, 192, 192, 0.6)',
          }],
        },
        options: {
          responsive: true,
        },
      });
    }
  }, [ageData]);

  return <canvas ref={chartRef}></canvas>;
};
