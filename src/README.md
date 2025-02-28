# 🍿🎞️ Excercise Project Cinema Room

This tiny exercise project was completed following the "Introduction to Spring Boot with Java" course on Hyperskill.

This service is a RESTful service using JSON used to sell tickets for seats in a cinema room. It allows users to 
retrieve a list of available cinema seats, see their price, location and book them. Tickets can also be refunded using
a unique token provided during the purchase process. The service provides also very basic statistics regarding the
operations of the cinema.

## ⚠️ Disclaimers and comments on the project and code structure

- 🐣 I am new to Java (any recent versions of it) and Spring Boot.
- 🧑‍🏫 If I'd design the API, I'd put the token inside the ticket, rather than outside in the response of `GET /purchase`
  since for me a token represents a purchased ticket (at least in this case).
- 🚄 I did not spend too much time thinking about which pattern to use or how to structure things as my objective is to
  learn Java + Spring Boot, I am more than happy to discuss code architecture at the right place!
- 🎮 Please note that this does not reflect in any way the quality and attention I would put in a real project but serves
  only as a reference ✌️
- 🧠 I did not use AI to produce any of the steps (it's also not rocket science 🚀)

## Listing available seats

#### Request

`GET /seats` Returns the list of available seats.

#### Response

```json
{
  "rows": 9,
  "columns": 9,
  "seats": [
    {
      "row": 1,
      "column": 1,
      "price": 10
    },
    ...
  ]
}
```

## Purchasing a seat

#### Request

`POST /purchase` Purchase a seat in the given row and column.

Body:
```json
{
  "row": 1,
  "column": 2
}
```

#### Response

```json
{
  "token": "UUIDToken",
  "ticket": {
    "row": 1,
    "column": 2,
    "price": 10
  }
}
```

## Returning a seat

#### Request

`POST /return` Returns the ticket with a matching token.

Body:

```json
{
  "token": "UUIDToken"
}
```

Response:

```json
{
  "ticket": {
    "row": 1,
    "column": 2,
    "price": 10
  }
}
```

## Getting statistics

#### Request

`GET /stats?password={password}` Returns basic statistics for the cinema. (password is super_secret)

#### Response

```json
{
  "income": 0,
  "purchased": 0, 
  "available": 81
}
```