# Support Chat App

A full-stack AI-powered customer support chatbot.

## Tech Stack
- React (Frontend)
- Spring Boot (Backend - primary)
- Node.js + Express (Backend - prototype)
- OpenAI API

## Features
- Real-time chat UI
- AI-powered responses
- REST API integration

## How to run

### Backend (Spring Boot - recommended)
cd backend-java  
./gradlew bootRun  

### Backend (Express - optional)
cd backend  
npm install  
node index.js  

### Frontend
cd frontend  
npm install  
npm run dev  

## Notes
- Set your OpenAI API key as an environment variable:
  
  Windows (PowerShell):
  $env:OPENAI_API_KEY="your_api_key"

- API endpoint:
  POST http://localhost:8080/api/chat
  