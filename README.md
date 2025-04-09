# JAVA PdfReader API using LLM interaction
A Spring Boot application that allows users to upload a **CASA bank statement PDF** and receive extracted details such as **Name**, **Email**, **Opening Balance**, and **Closing Balance** using **OpenAI GPT-4o** model.
🔗 **Live Hosted API**: [https://pdfreader-fped.onrender.com/api/parse-pdf](https://pdfreader-fped.onrender.com/api/parse-pdf)

---
## 📌 Switch to Master Branch  
**Note:** Please switch to the `master` branch to access all the documentation and source files.  

## 📂 Project Structure

### 🔹 Key Files

| File | Description |
|------|-------------|
| `PdfController.java` | Main controller that handles file upload and calls service to process PDF |
| `PdfService.java` | Extracts text from the PDF and uses OpenAI API for intelligent data parsing |
| `OpenAiService.java` | Integrates with OpenAI GPT-4o via REST call using Spring's `RestTemplate` |
| `application.properties` | Stores server config and API key (linked via environment variable for security) |
| `Dockerfile` | Dockerized for public deployment on Render |
| `ChatController.java` | [Debug Endpoint] Allows sending prompt manually to OpenAI via JSON |
| `ChatPromptDTO.java` | DTO for accepting request body in JSON format |
| `PdfParserUtil.java` | Optional utility class to aid in raw PDF parsing |

API can be accessed via Postman or curl command.(I have used Postman{instructions below👇})


### ❌ Files You Can Exclude  
The following files are not crucial to the core functionality:  
- `ApiReaderNewApplicationTests.java`  
- `Main.java`  

## 🧪 PdfReader.java (Testing Purpose)  
The `PdfReader.java` file is a **testing utility** that demonstrates how text extraction is performed from a PDF. It directly uses the **Apache PDFBox API** to extract text from any PDF file.  

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Apache PDFBox
- OpenAI GPT-4o (via REST API)
- Maven
- Docker (for deployment)
- Render (hosting platform)
- Postman / curl for testing

---
## 💡 Features

- 🔍 Intelligent extraction using **OpenAI GPT-4o** API
- 📄 Accepts **PDF file** as multipart input
- 🧠 Analyzes content with **LLM prompt** to extract:
  - Name
  - Email
  - Opening Balance
  - Closing Balance
- 📬 JSON formatted output
- 🧪 Separate test/debug endpoint to interact with OpenAI
- 🌐 **Deployed publicly** using Docker and Render

---

## 🚀 Getting Started (Local Setup)

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/mittalsoni00/FileReader.git
cd FileReader
git checkout master
```

### 2️⃣ Add Your OpenAI API Key

Use an environment variable for security:
```bash
export OPENAI_API_KEY=your_secret_key_here
```

Or add it in `application.properties` (only for testing, not recommended for prod):
```properties
OPENAI_API_KEY=${OPENAI_API_KEY}
```

### 3️⃣ Build and Run

```bash
mvn clean install
java -jar target/Api_Reader_New-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Debug Endpoint (Optional)

To test prompt-only flow (without PDF), hit:

```
POST /api/chat
Body (JSON):
{
  "prompt": "Tell me a joke about Java developers"
}
```

This will return a direct OpenAI response.

---



## 📬 API Documentation

### ✅ Endpoint for PDF Parsing

```
POST /api/parse-pdf
```

### Request Type
`multipart/form-data`

### Form Key:
| Key | Value |
|-----|-------|
| `file` | [Select your PDF file] |

### 🔁 Response (Success)

```json
{
  "response": "Here are the extracted details from the bank statement:\n\n- Name: John Doe\n- Email: johndoe@example.com\n- Opening Balance: $5,000\n- Closing Balance: $6,500"
}
```

---

## 📬 Testing via Postman

### 📌 Steps

1. Open Postman → **New Request**
2. Choose **POST** → Enter URL:
   ```
   https://pdfreader-fped.onrender.com/api/parse-pdf
   ```
3. Go to **Body** tab → Select `form-data`
4. Add a key named `file` → Upload PDF file
5. Click **Send**

### ✅ Response
You’ll receive a JSON containing Name, Email, and balances extracted using OpenAI.

🟢 Make sure `Content-Type` is set to `multipart/form-data`. Postman sets this automatically if `form-data` is chosen.

---

## 🐳 Deployment Notes (on Render)

- Dockerized Spring Boot app using:
  ```dockerfile
  FROM openjdk:17
  WORKDIR /app
  COPY target/Api_Reader_New-0.0.1-SNAPSHOT.jar app.jar
  ENTRYPOINT ["java", "-jar", "app.jar"]
  ```
- Pushed to GitHub repo: [https://github.com/mittalsoni00/FileReader](https://github.com/mittalsoni00/FileReader)
- Environment variable `OPENAI_API_KEY` added via Render dashboard
- Health Check path: `/api/parse-pdf`



