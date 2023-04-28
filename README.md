# BookQuest App Java's back-end

## Salesforce

### Authorization
- get a `access_token` making a request like Postman's `Authorization` example.
- `access_token` works for 2 hours. It's necessary generate another one after this time.
### Endpoints
All endpoints uses `Bearer {access_token}` as a `header` to connect.

**to get one record of a object:**
>GET: `https://bookquest-dev-ed.develop.my.salesforce.com/services/data/v57.0/sobjects/{OBJECT_NAME}/{id}` 

or 

>GET: `https://bookquest-dev-ed.develop.my.salesforce.com/services/data/v57.0/sobjects/{OBJECT_NAME}/{external_id_field}/{external_id_value}`

**to get multiple records**
>GET: `https://bookquest-dev-ed.develop.my.salesforce.com/services/data/v57.0/query?q={query}`
>
>[Click here](https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/sforce_api_calls_soql_select_examples.htm) to learn more about SOQL queries.

See Postman for more examples of **insert/update (upsert)** or query multiple objects.