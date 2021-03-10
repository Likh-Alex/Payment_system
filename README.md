## Payment system Application

### This application is used to manage `Client`, `Accounts` and `Payments`

### You can perform following operations in the application:

- `Create` a `Client`.
- `Get` all `Accounts` by `Client id`.
- `Create` a `Payment` between two `Accounts`.
- `Add` a group of `Payments` for processing between two `Accounts`.
- `Get` a list of `Payments` by different input parameters.

## In order to use the application

- Download the [source code](https://github.com/Likh-Alex/Payment_system)
- Download and install [Maven](https://maven.apache.org/install.html)
- Download and install [PostgreSQL](https://www.postgresql.org)
- Download and install any API testing tool. E.g [PostMan](https://www.postman.com/)
- Create database in `PostgreSQL`, keep your `username` and `password` - you would need it later.
- Open your IDE and `create new project` from the `source code`.
- In `src/main/resources/application.properties` `uncomment the contents` of the file and  change `username`, `password` and `database` url to your own
  credentials which you got after creating the `database` in `PostgreSQL`.
- Once you opened the project - run `PaymentSystemApplication.java` in order to start the application
- Go to Postman and test the `endpoints` described below.

### Endpoints description

`/accounts` 
- `Post` a `Client` and return new `Client` `Id` and `HttpStatus.CREATED`
- Example - `{
"first_name": "First Name", "last_name": "Last Name",
"accounts": [
{
"account_number": "1234",
"account_type": "CARD",
"balance": 1000.0 }
]
}`
  
`/accounts/{id}` 
- `Get` a list of all `Client` `Accounts` by given `Id`
- If `Client` is found in the database - application will return list of `Accounts` which belong to the `Client` and `HttpStatus.OK`.
- If `Client` is not found application will return `HttpStatus.NOT_FOUND`.

`/payments/one` 
- `Post` a `Payment` with supplied input.
- Example - `{
"source_acc_id": 1,
"dest_acc_id": 2,
"amount": 10.00,
"reason": "Payment reason"
}`.
- If the `source Account` does not
  have enough balance - `Payment` will be saved with `status` `ERROR` and returned by the application.
- If the `source Account` balance is sufficient to perform the payment - than `Payment` will be saved and returned with `status` `OK`.

`/payments/many` 
- `Post` a list of `Payments` with supplied input.
- Example - `[{
"source_acc_id": 1,
"dest_acc_id": 2,
"amount": 10.00,
"reason": "10 unit payment"
},
{
"source_acc_id": 1,
"dest_acc_id": 2,
"amount": 1000.00,
"reason": "1000 unit payment"
}]`
- Same validation rules apply to this method, 
  payments are processed in order as they are received. In the `second` if the `source Account` does not have enough balance - `Payment` will be saved with status `ERROR`. 
  
`/payments/parameters`
- `Get` a list of all `Payments` which are performed between `Accounts` or/and `Clients` parameters supplied.
- Example `{
  "payer_id": 1,
  "recipient_id": 2,
  "source_acc_id": 1,
  "dest_acc_id": 3
  }`
- Method will retrieve all `Payments` regardless of order of parameters or their amount and possible combinations.
Simply put - the user will get all `Payment` which include supplied parameters.


## Application is able to process both `Json` and `Xml` input formats.
