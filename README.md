# 🚀 Autocomplete Engine with Trie Data Structure

A high-performance autocomplete engine built with Spring Boot and Trie (Prefix Tree) data structure, featuring word frequency prioritization, fuzzy search capabilities, and a modern web interface.

## ✨ Features

### 🔥 Core Features
- **Lightning-Fast Autocomplete**: Trie-based prefix matching for instant suggestions
- **Word Frequency Prioritization**: Most common words appear first in suggestions
- **Fuzzy Search**: Typo-tolerant search using Levenshtein distance algorithm
- **Dynamic Word Addition**: Add new words with custom frequencies at runtime
- **RESTful API**: Clean, well-documented endpoints for integration
- **Modern Web UI**: Beautiful, responsive interface for testing all features

### 🛠️ Technical Features
- **Efficient Data Structure**: Trie (Prefix Tree) for O(k) prefix search complexity
- **Real-time Suggestions**: Instant feedback as user types
- **CORS Enabled**: Cross-origin requests supported for web applications
- **Error Handling**: Robust error handling and validation
- **Scalable Architecture**: Easy to extend and maintain

## 🛠️ Tech Stack

| Component | Technology | Version | Purpose |
|-----------|------------|---------|---------|
| **Backend** | Java | 17+ | Core programming language |
| **Framework** | Spring Boot | 3.2.5 | REST API and dependency injection |
| **Data Structure** | Trie (Custom) | - | Fast prefix matching |
| **Build Tool** | Maven | 3.6+ | Dependency management and build |
| **Frontend** | HTML/CSS/JS | - | Web interface |
| **Server** | Tomcat (Embedded) | 10.1.20 | Web server |

## 🚀 Quick Start

### Prerequisites
- **Java JDK**: 17 or higher
- **Maven**: 3.6 or higher
- **Browser**: Modern web browser for UI testing

### Installation & Setup

#### 1. **Clone/Download the Project**
```bash
cd "C:\Users\abhis\OneDrive\Desktop\new 2"
```

#### 2. **Verify Dependencies**
```bash
mvn dependency:resolve
```

#### 3. **Start the Backend**
```bash
mvn spring-boot:run
```

**Expected Output:**
```
Started AutocompleteApplication in 2.758 seconds
```

#### 4. **Open the Web Interface**
- Navigate to project folder
- Double-click `autocomplete.html`
- Or open in browser: `file:///path/to/autocomplete.html`

## 📡 API Documentation

### Base URL
```
http://localhost:8080/api
```

### Endpoints

#### 1. **Regular Autocomplete**
```http
GET /api/autocomplete?prefix={word}
```

**Parameters:**
- `prefix` (required): The word prefix to search for

**Response:**
```json
["well", "weather", "welcome", "wealth", "west"]
```

**Example:**
```bash
curl "http://localhost:8080/api/autocomplete?prefix=we"
```

#### 2. **Fuzzy Search (Typo Tolerant)**
```http
GET /api/autocomplete/fuzzy?prefix={word}&maxDistance={distance}
```

**Parameters:**
- `prefix` (required): The word to search for (can have typos)
- `maxDistance` (optional): Maximum Levenshtein distance (default: 1)

**Response:**
```json
["wealth"]
```

**Example:**
```bash
curl "http://localhost:8080/api/autocomplete/fuzzy?prefix=wealh&maxDistance=1"
```

#### 3. **Add New Word**
```http
POST /api/autocomplete/add?word={word}&frequency={frequency}
```

**Parameters:**
- `word` (required): The word to add
- `frequency` (optional): Word frequency (default: 1)

**Response:**
```
Word added: awesome (frequency: 50)
```

**Example:**
```bash
curl -X POST "http://localhost:8080/api/autocomplete/add?word=awesome&frequency=50"
```

## 🎯 Usage Examples

### Using the Web UI

#### **Autocomplete Search**
1. Type "we" in the first input box
2. See real-time suggestions: `["well", "weather", "welcome", ...]`
3. Click any suggestion to select it

#### **Fuzzy Search**
1. Type "wealh" (with typo) in the second input box
2. Set max distance to 1
3. Click "Search"
4. See correction: `["wealth"]`

#### **Add New Words**
1. Enter "fantastic" in the word field
2. Set frequency to 75
3. Click "Add Word"
4. See success message
5. Test with autocomplete: type "fa" → see "fantastic"

#### **API Testing**
1. Click "Test API Endpoints" button
2. See all tests pass with green checkmarks

### Using cURL Commands

```bash
# Regular autocomplete
curl "http://localhost:8080/api/autocomplete?prefix=we"

# Fuzzy search with typo
curl "http://localhost:8080/api/autocomplete/fuzzy?prefix=wealh&maxDistance=1"

# Add new word
curl -X POST "http://localhost:8080/api/autocomplete/add?word=amazing&frequency=60"

# Test fuzzy search with larger distance
curl "http://localhost:8080/api/autocomplete/fuzzy?prefix=wlecome&maxDistance=2"
```

## 📁 Project Structure

```
autocomplete-engine/
├── src/
│   ├── main/
│   │   ├── java/com/example/autocomplete/
│   │   │   ├── AutocompleteApplication.java    # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   └── AutocompleteController.java # REST API endpoints
│   │   │   ├── service/
│   │   │   │   └── TrieService.java           # Business logic and word management
│   │   │   └── trie/
│   │   │       ├── Trie.java                  # Trie data structure implementation
│   │   │       └── TrieNode.java              # Trie node structure
│   │   └── resources/
│   │       ├── words.txt                      # Word dictionary with frequencies
│   │       └── application.properties         # Spring Boot configuration
│   └── test/
│       └── java/com/example/autocomplete/
│           └── TrieServiceTest.java           # Unit tests
├── autocomplete.html                          # Web user interface
├── pom.xml                                   # Maven dependencies and build config
└── README.md                                 # This documentation
```

## 🔧 Customization

### Adding More Words
Edit `src/main/resources/words.txt`:
```
word1 100
word2 80
word3 60
```
**Format:** `word frequency`

### Modifying Fuzzy Search Distance
- Change the `maxDistance` parameter in fuzzy search
- Recommended range: 1-3
- Higher values allow more typos but may return irrelevant results

### Styling the Web UI
Edit the CSS in `autocomplete.html` to customize:
- Colors and themes
- Layout and spacing
- Fonts and typography
- Responsive design

### Adding New Features
The modular architecture makes it easy to add:
- Word deletion functionality
- Search history
- User preferences
- Database persistence
- Authentication and authorization

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Manual Testing
1. Start the backend: `mvn spring-boot:run`
2. Open `autocomplete.html` in browser
3. Test all features through the UI
4. Use the "Test API Endpoints" button

### API Testing with Postman
Import these requests to Postman:

```json
{
  "name": "Autocomplete API Tests",
  "requests": [
    {
      "name": "Regular Autocomplete",
      "method": "GET",
      "url": "http://localhost:8080/api/autocomplete?prefix=we"
    },
    {
      "name": "Fuzzy Search",
      "method": "GET", 
      "url": "http://localhost:8080/api/autocomplete/fuzzy?prefix=wealh&maxDistance=1"
    },
    {
      "name": "Add Word",
      "method": "POST",
      "url": "http://localhost:8080/api/autocomplete/add?word=awesome&frequency=50"
    }
  ]
}
```

## 🚀 Deployment

### Local Development
```bash
mvn spring-boot:run
```

### Production Build
```bash
mvn clean package
java -jar target/autocomplete-engine-1.0.0.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/autocomplete-engine-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📊 Performance Characteristics

### Time Complexity
- **Prefix Search**: O(k) where k is the length of the prefix
- **Fuzzy Search**: O(n × m × d) where n is number of words, m is average word length, d is max distance
- **Word Insertion**: O(k) where k is the word length

### Space Complexity
- **Trie Storage**: O(ALPHABET_SIZE × N × M) where N is number of words, M is average word length

### Memory Usage
- **Base Dictionary**: ~50KB for 1000 words
- **Runtime**: ~10-20MB for Spring Boot application

## 🔍 Algorithm Details

### Trie Data Structure
- **Node Structure**: Each node contains a map of characters to child nodes
- **End-of-Word Flag**: Marks complete words in the trie
- **Frequency Storage**: Each word node stores its frequency for sorting

### Fuzzy Search Algorithm
- **Levenshtein Distance**: Calculates minimum edit distance between strings
- **Dynamic Programming**: Uses DP table for efficient computation
- **Early Termination**: Stops when distance exceeds threshold

### Frequency-Based Sorting
- **Heap Sort**: Maintains words sorted by frequency
- **Real-time Updates**: Re-sorts when new words are added
- **Stable Sorting**: Maintains alphabetical order for same frequencies

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes
4. Add tests for new functionality
5. Commit your changes: `git commit -m 'Add amazing feature'`
6. Push to the branch: `git push origin feature/amazing-feature`
7. Submit a pull request

## 📝 License

This project is open source and available under the MIT License.

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- The Trie data structure community
- All contributors and testers

---

## 🎯 Interview Preparation

This project demonstrates:
- **Data Structures**: Trie implementation and optimization
- **Algorithms**: Levenshtein distance, sorting, search algorithms
- **System Design**: RESTful API design, scalability considerations
- **Full-Stack Development**: Backend (Java/Spring) + Frontend (HTML/JS)
- **Problem Solving**: Efficient autocomplete with typo tolerance
- **Code Quality**: Clean architecture, proper separation of concerns

**Perfect for demonstrating your skills in data structures, algorithms, and full-stack development!**

---

**Happy Coding! 🎉** 