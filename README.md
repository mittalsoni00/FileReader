# JAVA PdfReader API using LLM interaction

## 📂 Project Structure  

### 🔹 Important Files  
The main source files of this project are located in the source folder and include:  
- `pdfController.java` – Handles API requests.  
- `pdfService.java` – Implements business logic for PDF text extraction.  
- `pdfParserUtil.java` – Utility class for parsing PDFs.  
- `application.properties` – Configuration settings.
  the other files are also related to our API project: required dependencies, properties, libraries, etc. are relavant.

API can be accessed via Postman or curl command.(I have used Postman{instructions below👇})


### ❌ Files You Can Exclude  
The following files are not crucial to the core functionality:  
- `ApiReaderNewApplicationTests.java`  
- `Main.java`  

## 🧪 PdfReader.java (Testing Purpose)  
The `PdfReader.java` file is a **testing utility** that demonstrates how text extraction is performed from a PDF. It directly uses the **Apache PDFBox API** to extract text from any PDF file.  



## 🚀 Getting Started  

### **1️⃣ Clone the Repository**  
```bash
git clone https://github.com/mittalsoni00/FileReader.git
cd <your-project-folder>
git checkout master
```

### **2️⃣ Set Up the Project**  
Ensure you have **Java 8+** and **Maven** installed. Then, initialize the Spring Boot project:  
```bash
mvn clean install
```

### **3️⃣ Run the Application**  
Start the Spring Boot application:  
```bash
mvn spring-boot:run
```
OR  
```bash
java -jar target/<your-jar-file>.jar
```


#### ➤ **Example Curl Command**   
```bash
curl -X POST -F "file=@sample.pdf" http://localhost:8080/api/parse-pdf
```

### 🚀 Using Postman to Test the API  

#### **Step 1: Open Postman**  
Make sure you have **Postman** installed. If not, download it from [here](https://www.postman.com/downloads/).  

#### **Step 2: Create a New Request**  
1. Open **Postman** and click on **"New Request"**.  
2. Select **POST** as the request type.  
3. Enter the API endpoint:  
   ```
   http://localhost:8080/api/parse-pdf
   ```

#### **Step 3: Set Headers**  (very important🌟) 
When you check the form-data Postman will automatically set the content-type to multipart/form-data (you don't need to manually set that) 
I am just giving a quick-check if you encounter Error 415 "Unmatched file" please ensure Content-Type is automatically set to multipart/form-data 
Highlighting this point because had a lot of problems with this error(hope you don't get it) happy coding^.^
Go to the **Headers** tab and add:  
| Key             | Value                 |  
|----------------|-----------------------|  
| Content-Type   | multipart/form-data    |  

#### **Step 4: Upload a PDF File**  
1. Go to the **Body** tab.  
2. Select **form-data**.  
3. Add a new key named `"file"`.  
4. Click **"Select File"** and upload your PDF.  

#### **Step 5: Send the Request**  
Click on **"Send"** and wait for the response.  

#### **Step 6: View Response**  
- If successful, the response will contain the extracted text from the PDF.  
- If there's an error, check the console logs for debugging.  


