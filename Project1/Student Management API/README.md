# Student Management API

Το **Student Management API** είναι μια RESTful εφαρμογή βασισμένη στο **Spring Boot**, η οποία επιτρέπει τη διαχείριση φοιτητών. Οι χρήστες μπορούν να δημιουργούν, να επεξεργάζονται και να διαγράφουν φοιτητές μέσω HTTP αιτημάτων.

## Τεχνολογίες
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Lombok
- Maven

## Αρχιτεκτονική & Σχεδιαστικές Αποφάσεις
- **Controller Layer:** Διαχειρίζεται τα HTTP αιτήματα και δρομολογεί τις κλήσεις στην επιχειρηματική λογική.
- **Service Layer:** Περιέχει την επιχειρηματική λογική και συντονίζει τις κλήσεις προς τη βάση δεδομένων.
- **Repository Layer:** Επικοινωνεί με τη βάση δεδομένων μέσω του Spring Data JPA.
- **Global Exception Handling:** Εξασφαλίζει ομοιόμορφη διαχείριση λαθών σε όλο το API.

## Οδηγίες Εγκατάστασης
```bash
git clone https://github.com/manoskoum/Spring-Boot.git
cd Spring-Boot/Project1/Student\ Management\ API
mvn clean install
mvn spring-boot:run

Αφού εκτελέσετε τις παραπάνω εντολές, η εφαρμογή θα είναι διαθέσιμη στη διεύθυνση:
 http://localhost:9090/api/students

API Endpoints
GET /api/students
Εμφάνιση όλων των φοιτητών

GET /api/students/{id}
Εμφάνιση δεδομένων φοιτητή με βάση το ID

POST /api/students
Δημιουργία νέας καταχώρησης φοιτητή

PUT /api/students/{id}
Ενημέρωση υπάρχουσας καταχώρησης φοιτητή

DELETE /api/students/{id}
Διαγραφή καταχώρησης φοιτητή

Παράδειγμα JSON για αίτημα POST:

{
 "firstname": "Manolis",
 "lastname": "Koumentakis",
 "age": 27
}

Ανάπτυξη & Δοκιμές

Postman: Μπορείτε να χρησιμοποιήσετε το Postman για να στείλετε αιτήματα στο API, π.χ. στο http://localhost:9090/api/students.

H2 Console: Για debugging και δοκιμές, η H2 κονσόλα είναι διαθέσιμη στο:
 http://localhost:9090/h2-console

Επικοινωνία:

Όνομα: Eμμανουηλ Κουμεντακης
Email: manoskoume@gmail.com
LinkedIn: 
GitHub: 
