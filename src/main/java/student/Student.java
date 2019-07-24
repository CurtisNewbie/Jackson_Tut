package student;

/**
 * This is like a Java bean
 */
public class Student {

    private String name;
    private String major;
    private int age;

    // follows convention, that it does not get any parameter
    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Get the properties of this object
     *
     * @return the properties of this object
     */
    public String toString() {
        return "student.Student:[ Name:" + name + " Major:" + major + " Age:" + age + "]";
    }
}
