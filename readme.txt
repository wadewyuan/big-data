Step #1 - Get the token with client id and client secret
    curl -H "Accept: application/json" user:password@localhost:8080/oauth/token -d grant_type=client_credentials

Step #2 - Use the token to access resources
    curl -H "Authorization: Bearer token" localhost:8080/...