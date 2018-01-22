Run with dev or prod profiles:
    - By default, the active profile is prod, so just run java -jar big-data-{version}.jar
    - To activate dev profile, add -D option java -jar -Dspring.profiles.active=dev big-data-{version}.jar

*Note* - The "Hello" APIs are only activated in dev profile

API Usage
    Step #1 - Get the token with client id and client secret
        curl -H "Accept: application/json" client_id:client_secret@localhost:8080/oauth/token -d grant_type=client_credentials

    Step #2 - Use the token to access resources
        curl -H "Authorization: Bearer token" localhost:8080/...