🛒 E-Commerce Backend (Spring Boot)
📌 Overview
This project is a backend service for an e-commerce application built with Java Spring Boot. It provides secure authentication, product catalog management, and order handling APIs to support an online shopping platform.
🚀 Features
- Authentication: User registration & login with JWT-based security (can be enabled for secured APIs)
- Product Management: CRUD operations for products
- Order Management: Place, view, update, and cancel customer orders
- Stock Management: Automatically updates stock when orders are placed or cancelled 
- RESTful APIs: Well-structured endpoints for easy integration
- Database Integration: Works with MySQL/PostgreSQL (configurable)
🛠️ Tech Stack
- Language: Java
- Framework: Spring Boot
- Database: MySQL / PostgreSQL
- Authentication: Spring Security with JWT
- Build Tool: Maven / Gradle
📂 Project Structure
├── src
│   ├── main
│   │   ├── java/com/dev/ecommerce
│   │   │   ├── controller
│   │   │   ├── model
│   │   │   ├── repository
│   │   │   ├── service
│   │   │   └── security
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   └── test
└── README.md


⚙️ Installation & Setup
# Clone the repository
git clone https://github.com/devyaniwadbude/ecommerce-backend.git

# Navigate into the project
cd ecommerce-backend

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run


🔑 Configuration
Update application.properties with your database and JWT secret:
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

jwt.secret=your_secret_key
server.port=8080


📡 API Endpoints
Method   	Endpoint	           Description
POST	   /auth/register	      Register new user
POST	   /auth/login	        Login user & get JWT
GET	     /products	          Get all products
POST	   /products	          Add new product
PUT	     /products/{id}	      Update product
DELETE	 /products/{id}	      Delete product
POST	   /orders	            Create new order
GET	     /orders/{userId}	    Get user orders
PUT    	 /orders/{id}/status	Update order status
DELETE 	 /orders/{id}	        Cancel order


📬 Testing with Postman
- Use Postman to test APIs
- Base URL: http://localhost:8080
- For secured APIs, add header:
  Authorization: Bearer <your_token>
  
🧪 Testing
mvn test

📦 Deployment
- Package the app: mvn package
- Run using: java -jar target/ecommerce-backend.jar

🔮 Future Enhancements
- Payment gateway integration (Razorpay/Stripe)
- Role-based access (Admin/User)
- Cart system implementation
- Frontend integration (React/Angular)

🤝 Contributing
Pull requests are welcome. For major changes, open an issue first to discuss what you’d like to change.
📜 License
MIT License
