# Laboratory 03 & 04 ARSW

### Author: Daniel Patiño Mejia

## Objective:
Understand and implement different communication mechanisms between distributed Java applications, exploring different levels of abstraction:

Communication via TCP and UDP

Handling URLs and reading web resources

Implementation of client-server Sockets

Building a basic web server

Communication via Datagrams (UDP)

Remote method invocation with Java RMI

Implementation of RPC over sockets

Development of a P2P system with a central tracker

---
## Introduction to naming schemes, networks, clients, and services with Java
This laboratory offers a practical introduction to distributed programming in Java, covering the fundamentals of network communication, client-server models, remote method invocation (RMI), RPC over sockets, and P2P architectures.

The content is partially based on the official Oracle tutorials on Java network programming available at:
https://docs.oracle.com/javase/tutorial/networking/index.html

# Exercise 1

- Write a program in which you create a URL object and print to the screen each of the data returned by the 8 methods in the previous section.
The PrintURLData class was developed, which prompts the user for a URL via the console and creates a URL object from the java.net package.

From this object, the main components of the address are printed on the screen using the following methods:

getProtocol()

getAuthority()

getHost()

getPort()

getPath()

getQuery()

getFile()

getRef()

The program includes exception handling (MalformedURLException) to validate the format of the entered URL.

Through this exercise, we understood how to programmatically break down a URL into its fundamental parts in Java.
And we obtain the following results.

![Ejercicio 1.1](/Lab-03/img/exercise1.1.png)
![Ejercicio1.2](/Lab-03/img/exercise1.2.png)
![Ejercicio1.3](/Lab-03/img/exercise1.3.png)
# Exercise 2

In this exercise, the WebSiteReader class was developed, which implements a basic console browser.

The program performs the following actions:

It requests a URL from the user.

It creates a URL object using the entered address.

It opens an input stream with openStream().

It reads the page content line by line using BufferedReader.

It saves the content in a file called resultado.html using BufferedWriter.

It also displays the content in the console.

#### Execution Evidence

When running the program with a test HTML page, the content was correctly downloaded and stored in the file resultado.html.

Subsequently, when opening the file in the browser, the HTML structure was displayed correctly:
![image2.1](/Lab-03/img/exercise2.1.png)
![image2.2](/Lab-03/img/exercise2.2.png)
And we can verify here.
![Image2.3](/Lab-03/img/exercise2.3.png)
# Exercise 3
In this exercise, a client-server application was implemented using TCP sockets.
The server:

-Listens on port 35000.

-Receives a number sent by the client.

-Calculates the square of the number.

-Sends the result to the client.

-Handles errors in case the message is not a valid number.
##### Client (ClientSqrNumber)

The client:

-Connects to the server at 127.0.0.1:35000.

-Allows numbers to be entered via the console.

-Sends each number to the server.

-Displays the received response.

-Ends execution when the user types bye.

This exercise allowed for an understanding of the client-server model using Socket and ServerSocket, as well as data exchange through input and output streams.
### Execution Evidence

The following images show the communication between the client and the server.
![img point3.1](/Lab-03/img/exercise3.1.png)
![img point 3.2](/Lab-03/img/exercise3.2.png)

# Exercise 4 – Trigonometric TCP Calculator

## Description

This exercise implements a TCP client-server application that performs trigonometric calculations. The client sends numeric values to the server, and the server computes the selected trigonometric function.

The communication is handled through a simple text-based protocol over port `35000`.

---

## Server

- Uses a default function (sen recommended).
- Supports:
  - `fun:sen`
  - `fun:cos`
  - `fun:tan`
- Receives numbers and returns the calculated result.
- Handles invalid input.
- Ends the connection when receiving end.

---

## Client

- Sends numbers to calculate.
- Allows changing the function dynamically.
- Displays the server response.
- Ends the session with end.

---
### Evidence
![image4.1](/Lab-03/img/exercise4.1.png)
![image4.2](/Lab-03/img/exercise4.2.png)

# Exercise 5


## Description

This exercise implements a basic HTTP server using Java sockets.  
The server listens on port 35000 and serves static files from a local www directory.

---

## Features

- Supports GET requests only.
- Serves static files (HTML, CSS, JS, images, etc.).
- Returns appropriate HTTP status codes:
  - `200 OK`
  - `400 Bad Request`
  - `403 Forbidden`
  - `404 Not Found`
  - `405 Method Not Allowed`
- Prevents directory traversal attacks.
- Detects content type (MIME type) automatically.
- Handles one request per connection.

---

## How It Works

1. The server waits for client connections.
2. It reads the HTTP request line.
3. Validates the method and requested path.
4. Searches the file inside the www folder.
5. Sends a properly formatted HTTP response.

---
# Exercise 6 

## Description

This exercise implements a UDP client-server application that provides the current date and time.

The server listens on port 4445 and responds with the current system date whenever it receives a datagram request.

---

## Server

- Uses DatagramSocket on port 4445.
- Waits for incoming datagrams.
- Sends the current date and time back to the client.
- Runs continuously until the socket is closed.

---

## Client

- Sends a UDP request to 127.0.0.1:4445.
- Receives and displays the current date.
- Uses a timeout to avoid blocking.
- Repeats the request every 5 seconds.
- Keeps the last known time if no response is received.

---

## Evidence
![image 6](/Lab-03/img/exercise6.1.png)