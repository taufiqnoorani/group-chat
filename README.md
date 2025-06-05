# ThreadLine – Multithreaded Group Chat Application (JavaFX + Sockets)
A real-time JavaFX chat application with a multithreaded server built using Java sockets and Maven.

ThreadLine is a fully functional group chat application built using JavaFX and Java sockets. It consists of a multithreaded server and a JavaFX-based client GUI that allows multiple users to communicate with each other in real time. This project demonstrates key concepts of network programming, multithreading, GUI development, and Maven-based project management.

---

## Project Goals

- Build a simple, real-time group chat system using core Java.
- Learn socket programming and multithreading to handle multiple clients.
- Design an interactive UI using JavaFX.
- Structure and manage the project using Maven.

---

## Features

- Real-time messaging across multiple client windows
- Multithreaded server using `Thread` to handle concurrent clients
- JavaFX GUI for message input/output
- Uses Maven for dependency and build management
- Modular, readable Java code for learning or extension

---

## Tech Stack

| Technology     | Role                         |
|----------------|------------------------------|
| Java 17+       | Core language                |
| JavaFX         | Graphical user interface     |
| Sockets (TCP)  | Client-server communication  |
| Threads        | Concurrent client handling   |
| Maven          | Project structure & build    |

---

## Folder Structure

```
group-chat/
├── pom.xml                           
└── src/
  └── main/
    └── java/
      └── com/chatapp/
        ├── Main.java         # Launches JavaFX client
        ├── Client.java       # Client socket + GUI logic
        ├── Server.java       # Starts server socket
        └── ClientThread.java # Manages each connected client
```

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/taufiqnoorani/group-chat.git
cd group-chat
```

### 2. Start the server

```bash
mvn compile exec:java -Dexec.mainClass="com.chatapp.Server"
```
This starts the chat server on port 1234.

### 3. Start a Client (In Another Terminal)

```bash
mvn javafx:run -DmainArgs="localhost 1234"
```

---

## How It Works

### Server

- Listens on a fixed port.
- For every new client, spawns a new ClientThread.
- Broadcasts each message to all connected clients.

### Client

- Connects to the server using a Socket.
- Listens for messages on a background thread.
- Sends messages from the UI input to the server.

---

## Future Enhancements

- Address real-world platform issues like JavaFX GUI behaviour on macOS.
- Add support for usernames and display them in messages.
- Timestamps for chat history.
- Persistent storage using file or database.
- Scroll-to-bottom auto feature on new message.

---

## License

This project is open-source and available under the [MIT License](LICENSE).