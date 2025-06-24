import React, { useEffect, useState } from "react";
import { userManager } from "./myConfig";



function App() {
  const [user, setUser] = useState(null);
  const [data, setData] = useState("");

  useEffect(() => {
    userManager.getUser().then((user) => {
      if (user && !user.expired) {
        setUser(user);
      }
    });

    // Handle OIDC redirect after login
    if (window.location.pathname === "/callback") {
      userManager.signinRedirectCallback().then((user) => {
        setUser(user);
        window.history.replaceState({}, document.title, "/");
      });
    }
  }, []);

  const login = () => userManager.signinRedirect();
  const logout = () => userManager.signoutRedirect();

  const callApi = async () => {
    const res = await fetch("http://localhost:8080/api/secure-data", {
      headers: {
        headers: {
          'Authorization': `Bearer ${user.access_token}`,
          'Content-Type': 'application/json',
        },
        credentials: 'include',
      },
    });
    const text = await res.text();
    setData(text);
  };

  return (
    <div style={{ padding: "2rem" }}>
      <h1>React + Keycloak SPA</h1>

      {!user && <button onClick={login}>Login</button>}
      {user && (
        <>
          <p>👤 Logged in as: {user.profile.preferred_username}</p>
          <button onClick={logout}>Logout</button>
          <hr />
          <button onClick={callApi}>Call Secured API</button>
          <p>🔐 API Response: {data}</p>
        </>
      )}
    </div>
  );
}

export default App;
