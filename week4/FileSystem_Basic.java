
import java.util.ArrayList;
import java.util.List;

// Files --> leaf
// Folder --> Folders, Files

interface FileSystemNode{
    int size();
    void ls();
}

class File implements FileSystemNode{
    private String name;
    private int size;
    public File(String name, int size){
        this.name = name;
        this.size = size;
    }

    @Override
    public int size(){
        return size;
    }
    @Override
    public void ls(){
        System.out.println("File: " +name);
    }
}


class Folder implements FileSystemNode{
    private String name;
    List<FileSystemNode> child;
    public Folder(String name){
        this.name = name;
        this.child = new ArrayList<>();
    }

    void addFileSystemNode(FileSystemNode node){
        this.child.add(node);
    }

    @Override
    public void ls(){
        System.out.println("Folder: "+name);
        for(FileSystemNode node : child){
            node.ls();
        }
    }

    @Override
    public int size(){
        int total = 0;
        for(FileSystemNode node : child){
            total += node.size();
        }
        System.out.println("Total size of :" + name + " size: "+total);
        return total;
    }


}

public class FileSystem_Basic {
    public static void main(String[] args) {
        Folder snehil = new Folder("snehil");
        snehil.addFileSystemNode(new File("LLD Notes", 10));
        snehil.addFileSystemNode(new Folder("HLD Notes"));

        Folder payal = new Folder("payal");
        payal.addFileSystemNode(new File("DSA", 20));
        payal.addFileSystemNode(new Folder("DDSAD Notes"));

        snehil.size();
        payal.size();
        System.out.println("LS");
        snehil.ls();
        payal.ls();

    }
}
