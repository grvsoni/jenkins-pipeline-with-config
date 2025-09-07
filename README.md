# Jenkins Pipeline with Git and Ant Configuration

This project demonstrates a Jenkins pipeline that showcases the use of two key Jenkins pipeline configurations:
1. **Git Repository Configuration** - Repository URL setup
2. **Ant Build Configuration** - Build file path configuration

## Project Structure

```
jenkins-pipeline-with-config/
├── src/
│   └── HelloWorld.java          # Simple Java Hello World application
├── build.xml                    # Ant build file with comprehensive build targets
├── Jenkinsfile                  # Jenkins pipeline configuration
└── README.md                    # This documentation file
```

## Components

### 1. Hello World Application (`src/HelloWorld.java`)
A simple Java application that:
- Prints a greeting message
- Demonstrates basic functionality with a simple calculation
- Serves as the build target for the pipeline

### 2. Ant Build File (`build.xml`)
Comprehensive Ant build configuration with targets for:
- **init**: Initialize build directories
- **compile**: Compile Java source code
- **jar**: Create executable JAR file
- **test**: Run basic application tests
- **clean**: Clean build artifacts
- **all**: Complete build lifecycle

### 3. Jenkins Pipeline (`Jenkinsfile`)
Demonstrates Jenkins pipeline configurations:

#### Git Repository Configuration
- Repository URL configuration (configured in Jenkins SCM settings)
- Automatic checkout from version control
- Workspace verification and source file validation

#### Ant Build File Path Configuration
- Environment variable `ANT_BUILD_FILE` set to `build.xml`
- All Ant commands use the configured build file path: `ant -f ${ANT_BUILD_FILE} <target>`
- Build file existence verification

## Pipeline Stages

1. **Checkout**: Demonstrates Git repository URL configuration
2. **Environment Setup**: Verify Java/Ant availability and build file
3. **Clean**: Clean previous build artifacts
4. **Compile**: Compile Java source code using Ant
5. **Test**: Run application tests
6. **Package**: Create JAR file and archive artifacts
7. **Deploy**: Deployment simulation

## Jenkins Configuration Requirements

### Git Repository Configuration
In Jenkins job configuration:
1. Go to **Source Code Management** section
2. Select **Git**
3. Set **Repository URL** to your Git repository (e.g., `https://github.com/your-username/jenkins-pipeline-with-config.git`)
4. Configure credentials if required
5. Set branch specifier (e.g., `*/main` or `*/master`)

### Build Environment Requirements
- Java JDK (version 8 or higher)
- Apache Ant build tool
- Jenkins with Pipeline plugin

### Environment Variables Used
- `ANT_BUILD_FILE`: Path to the Ant build file (set to `build.xml`)
- `JAVA_HOME`: Java installation directory
- `PATH`: Updated to include Java binaries

## Running Locally

To test the build locally without Jenkins:

```bash
# Clean and compile
ant clean compile

# Run tests
ant test

# Create JAR
ant jar

# Run the application
ant run

# Complete build lifecycle
ant all
```

## Pipeline Features Demonstrated

### Git Configuration Features
- Automatic source code checkout
- Repository URL configuration
- Workspace management
- Source file verification

### Ant Build Configuration Features
- Build file path specification
- Environment variable usage
- Multiple build targets
- Artifact archiving
- Build lifecycle management

## Customization

### Modifying Git Repository
Update the repository URL in Jenkins job configuration under **Source Code Management**.

### Modifying Ant Build File Path
Change the `ANT_BUILD_FILE` environment variable in the Jenkinsfile:
```groovy
environment {
    ANT_BUILD_FILE = 'path/to/your/build.xml'
}
```

### Adding Build Parameters
You can extend the pipeline to accept parameters:
```groovy
parameters {
    string(name: 'BUILD_FILE', defaultValue: 'build.xml', description: 'Ant build file path')
    choice(name: 'BUILD_TYPE', choices: ['compile', 'test', 'jar', 'all'], description: 'Build target')
}
```

## Troubleshooting

### Common Issues
1. **Java not found**: Ensure `JAVA_HOME` is correctly set
2. **Ant not found**: Install Apache Ant and ensure it's in PATH
3. **Build file not found**: Verify `ANT_BUILD_FILE` path is correct
4. **Permission issues**: Ensure Jenkins has proper file system permissions

### Logs and Debugging
The pipeline includes comprehensive logging at each stage to help with debugging and monitoring the build process.
