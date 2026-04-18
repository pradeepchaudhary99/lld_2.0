import java.util.*;

// ========================= BASE CLASS =========================
abstract class FileSystemNode {
    protected String name;
    protected Directory parent;
    protected long createdAt;

    public FileSystemNode(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.createdAt = System.currentTimeMillis();
    }

    public String getName() {
        return name;
    }

    public abstract boolean isDirectory();
}

// ========================= FILE =========================
class FileNode extends FileSystemNode {
    private StringBuilder content;

    public FileNode(String name, Directory parent) {
        super(name, parent);
        this.content = new StringBuilder();
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    public void write(String data) {
        content.append(data);
    }

    public String read() {
        return content.toString();
    }
}

// ========================= DIRECTORY =========================
class Directory extends FileSystemNode {
    private Map<String, FileSystemNode> children;

    public Directory(String name, Directory parent) {
        super(name, parent);
        this.children = new HashMap<>();
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    public void addNode(FileSystemNode node) {
        if (children.containsKey(node.getName())) {
            throw new RuntimeException("Node already exists: " + node.getName());
        }
        children.put(node.getName(), node);
    }

    public FileSystemNode getNode(String name) {
        return children.get(name);
    }

    public void removeNode(String name) {
        if (!children.containsKey(name)) {
            throw new RuntimeException("Node not found: " + name);
        }
        children.remove(name);
    }

    public List<String> list() {
        return new ArrayList<>(children.keySet());
    }
}

// ========================= FILE SYSTEM =========================
class FileSystem {
    private Directory root;

    public FileSystem() {
        root = new Directory("/", null);
    }

    private String[] tokenize(String path) {
        return path.split("/");
    }

    // 🔥 Core Method
    private Directory traverseToParent(String path) {
        String[] parts = tokenize(path);
        Directory curr = root;

        for (int i = 1; i < parts.length - 1; i++) {
            FileSystemNode node = curr.getNode(parts[i]);

            if (node == null) {
                throw new RuntimeException("Path does not exist: " + parts[i]);
            }

            if (!node.isDirectory()) {
                throw new RuntimeException(parts[i] + " is not a directory");
            }

            curr = (Directory) node;
        }
        return curr;
    }

    private FileSystemNode getNode(String path) {
        String[] parts = tokenize(path);
        FileSystemNode curr = root;

        for (int i = 1; i < parts.length; i++) {
            if (!(curr instanceof Directory)) {
                throw new RuntimeException("Invalid path");
            }

            curr = ((Directory) curr).getNode(parts[i]);

            if (curr == null) {
                throw new RuntimeException("Path not found: " + parts[i]);
            }
        }
        return curr;
    }

    // ========================= OPERATIONS =========================

    public void createFile(String path) {
        Directory parent = traverseToParent(path);
        String fileName = path.substring(path.lastIndexOf("/") + 1);

        parent.addNode(new FileNode(fileName, parent));
    }

    public void createDirectory(String path) {
        Directory parent = traverseToParent(path);
        String dirName = path.substring(path.lastIndexOf("/") + 1);

        parent.addNode(new Directory(dirName, parent));
    }

    public void write(String path, String data) {
        FileSystemNode node = getNode(path);

        if (node.isDirectory()) {
            throw new RuntimeException("Cannot write to a directory");
        }

        ((FileNode) node).write(data);
    }

    public String read(String path) {
        FileSystemNode node = getNode(path);

        if (node.isDirectory()) {
            throw new RuntimeException("Cannot read a directory");
        }

        return ((FileNode) node).read();
    }

    public List<String> list(String path) {
        FileSystemNode node = getNode(path);

        if (!node.isDirectory()) {
            return List.of(node.getName());
        }

        return ((Directory) node).list();
    }

    public void delete(String path) {
        Directory parent = traverseToParent(path);
        String name = path.substring(path.lastIndexOf("/") + 1);

        parent.removeNode(name);
    }
}

// ========================= MAIN =========================
public class FileSystemDemo {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem();

        // Create directories
        fs.createDirectory("/a");
        fs.createDirectory("/a/b");
        fs.createDirectory("/a/b/c");

        // Create file
        fs.createFile("/a/b/c/file.txt");

        // Write content
        fs.write("/a/b/c/file.txt", "Hello ");
        fs.write("/a/b/c/file.txt", "World!");

        // Read file
        System.out.println(fs.read("/a/b/c/file.txt")); // Hello World!

        // List directory
        System.out.println(fs.list("/a/b/c")); // [file.txt]

        // Delete file
        fs.delete("/a/b/c/file.txt");

        System.out.println(fs.list("/a/b/c")); // []
    }
}