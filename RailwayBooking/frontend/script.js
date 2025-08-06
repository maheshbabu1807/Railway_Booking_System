const API_BASE = window.location.hostname === "localhost" 
  ? "http://localhost:8080/api" 
  : "https://your-backend-url/api";

async function fetchData(endpoint) {
  const response = await fetch(`${API_BASE}/${endpoint}`);
  const data = await response.json();
  document.getElementById('output').textContent = JSON.stringify(data, null, 2);
}

function viewTrains() {
  fetchData('viewTrains');
}

function bookTicket() {
  fetchData('bookTicket');
}

function cancelTicket() {
  fetchData('cancelTicket');
}

function viewBookings() {
  fetchData('viewBookings');
}
