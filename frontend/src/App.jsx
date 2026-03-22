import { useState } from "react";

export default function App() {
  const [messages, setMessages] = useState([]);
  const [input, setInput] = useState("");

  const sendMessage = async () => {
    if (!input.trim()) return;

    const newMessages = [...messages, { role: "user", text: input }];
    setMessages(newMessages);
    setInput("");

    try {
      const res = await fetch("http://localhost:3001/chat", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ messages: newMessages }),
      });

      const data = await res.json();

      setMessages((prev) => [
        ...prev,
        { role: "bot", text: data.reply },
      ]);
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        { role: "bot", text: "Error connecting to server" },
      ]);
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Support Chat</h2>

      <div
        style={{
          height: 300,
          overflowY: "auto",
          border: "1px solid #ccc",
          padding: 10,
          marginBottom: 10,
        }}
      >
        {messages.map((m, i) => (
          <div key={i} style={{ textAlign: m.role === "user" ? "right" : "left" }}>
            <p><b>{m.role}:</b> {m.text}</p>
          </div>
        ))}
      </div>

      <input
        value={input}
        onChange={(e) => setInput(e.target.value)}
        placeholder="Type message"
        style={{ marginRight: 10 }}
      />
      <button onClick={sendMessage}>Send</button>
    </div>
  );
}