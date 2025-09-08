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

## Jenkins Setup Instructions

### Prerequisites
Before setting up the pipeline in Jenkins, ensure you have:
- Jenkins server running (version 2.0 or higher)
- Pipeline plugin installed in Jenkins
- Java JDK (version 8 or higher) installed on Jenkins agents
- Apache Ant build tool installed on Jenkins agents
- Git plugin installed in Jenkins

### Step-by-Step Jenkins Configuration

#### Step 1: Create a New Pipeline Job
1. **Login to Jenkins** and navigate to the Jenkins dashboard
2. **Click "New Item"** in the left sidebar
3. **Enter job name**: `jenkins-pipeline-with-config` (or your preferred name)
4. **Select "Pipeline"** as the project type
5. **Click "OK"** to create the job

#### Step 2: Configure Git Repository (SCM)
1. **Scroll down to "Pipeline" section** in the job configuration
2. **Set "Definition"** to `Pipeline script from SCM`
3. **Select "Git"** from the SCM dropdown
4. **Enter Repository URL**: `https://github.com/grvsoni/jenkins-pipeline-with-config.git`
5. **Configure Credentials** (if repository is private):
   - Click "Add" next to Credentials
   - Select "Username with password" or "SSH Username with private key"
   - Enter your GitHub credentials
6. **Set Branch Specifier**: `*/main` (or `*/master` if using master branch)
7. **Set Script Path**: `Jenkinsfile` (this tells Jenkins where to find the pipeline script)

#### Step 3: Configure Build Environment
1. **Go to "Manage Jenkins"** → **"Global Tool Configuration"**
2. **Configure JDK**:
   - Add JDK installation
   - Set name: `Java-11` (or your preferred name)
   - Set JAVA_HOME path or enable automatic installation
3. **Configure Ant**:
   - Add Ant installation
   - Set name: `Ant-1.10`
   - Set ANT_HOME path or enable automatic installation

#### Step 4: Configure Pipeline Environment Variables (Optional)
If you need to customize the build file path:
1. **In job configuration**, scroll to **"Pipeline"** section
2. **Click "Advanced"** (if available)
3. **Add environment variables**:
   - `ANT_BUILD_FILE=build.xml`
   - `JAVA_HOME=/path/to/java` (if not set globally)

#### Step 5: Save and Run the Pipeline
1. **Click "Save"** to save the job configuration
2. **Click "Build Now"** to trigger the first build
3. **Monitor the build** in the "Build History" section
4. **View console output** by clicking on the build number

### Pipeline Execution Flow
Once configured, the pipeline will automatically:
1. **Checkout** source code from the Git repository
2. **Verify** build environment (Java, Ant, build file)
3. **Clean** previous build artifacts
4. **Compile** Java source code using Ant
5. **Test** the application
6. **Package** into JAR file
7. **Archive** build artifacts
8. **Deploy** (simulation)

### Webhook Configuration (Optional)
To trigger builds automatically on Git commits:
1. **In job configuration**, check **"GitHub hook trigger for GITScm polling"**
2. **In GitHub repository settings**:
   - Go to Settings → Webhooks
   - Add webhook: `http://your-jenkins-server/github-webhook/`
   - Select "Just the push event"
   - Set Content type to `application/json`

### Build Environment Requirements
- **Java JDK** (version 8 or higher)
- **Apache Ant** build tool (version 1.9 or higher)
- **Jenkins Pipeline plugin**
- **Git plugin** for Jenkins

### Environment Variables Used in Pipeline
- `ANT_BUILD_FILE`: Path to the Ant build file (set to `build.xml`)
- `JAVA_HOME`: Java installation directory
- `PATH`: Updated to include Java binaries
- `WORKSPACE`: Jenkins workspace directory
- `GIT_URL`: Git repository URL (automatically set by Jenkins)

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
