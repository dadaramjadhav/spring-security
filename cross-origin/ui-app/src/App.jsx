import { useState } from 'react'

import axios from 'axios'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  const fetchUser = () => {
    axios
      .get('http://localhost:8080/api/user')
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error('Error fetching user:', error);
      }
      );
  }
  const fetchAdmin = () => {
    axios
      .get('http://localhost:8080/api/admin')
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error('Error fetching user:', error);
      }
      );
  }

  return (
    <>
      <div>
        <button onClick={fetchUser}>Fetch User</button>
        <button onClick={fetchAdmin}>Fetch ADmin</button>
      </div>

    </>
  )
}

export default App
