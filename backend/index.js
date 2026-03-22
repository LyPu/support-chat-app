import express from "express";
import cors from "cors";
import OpenAI from "openai";
import dotenv from "dotenv";

dotenv.config();

if (!process.env.OPENAI_API_KEY) {
  console.error("❌ OPENAI_API_KEY is missing in .env");
  process.exit(1);
}

const app = express();
app.use(cors());
app.use(express.json());

const client = new OpenAI({
  apiKey: process.env.OPENAI_API_KEY,
});

app.get("/", (req, res) => {
  res.send("Backend is running");
});

app.post("/chat", async (req, res) => {
  const { messages } = req.body;

  console.log("Incoming request:", messages);

  try {
    const completion = await client.chat.completions.create({
      model: "gpt-4.1-mini",
      messages: [
        {
          role: "system",
          content: "You are a helpful customer support assistant.",
        },
        ...messages.map((m) => ({
          role: m.role,
          content: m.text,
        })),
      ],
    });

    const reply = completion.choices[0].message.content;

    console.log("LLM reply:", reply);

    res.json({ reply });
  } catch (err) {
    console.error("Error:", err);
    res.status(500).json({ error: err.message });
  }
});

app.listen(3001, () => {
  console.log("Backend running on 3001");
});