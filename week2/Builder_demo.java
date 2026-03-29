
//Builder design pattern..

class Student{
    private String name; // mandatory    
    //optional
    private int age;
    private int id;
    private String address;
    private String email;

    private Student(StudentBuilder builder){
        this.name = builder.name;
        this.age = builder.age;
        this.id = builder.id;
        this.address = builder.address;
        this.email = builder.email;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", id=" + id + ", address=" + address + ", email=" + email
                + "]";
    }

    //if I design the constructor for students it will be a nightmare... constructor explosion problem will occour..
    // every combination u need to capture... 16
    static class StudentBuilder{
        private String name; // mandatory    
        //optional
        private int age;
        private int id;
        private String address;
        private String email;


        public StudentBuilder(String name){
            this.name = name;
        }

        public StudentBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public StudentBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public StudentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }    

}

public class Builder_demo {
    public static void main(String[] args) {
       Student.StudentBuilder builder = new Student.StudentBuilder("pradeep");
       Student student = builder.setAddress("bangalore").setEmail("dadad").build();
        System.out.println(student.toString());

        StringBuilder sb = new StringBuilder();

    }
}
