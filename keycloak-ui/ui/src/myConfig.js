import { UserManager } from "oidc-client-ts";

const config = {
    authority: "http://localhost:8081/realms/myrealm",  
    client_id: "myapp",
    redirect_uri: "http://localhost:5173/callback",
    response_type: "code",
    scope: "openid profile email",
    post_logout_redirect_uri: "http://localhost:5173/",
};

export const userManager = new UserManager(config);
