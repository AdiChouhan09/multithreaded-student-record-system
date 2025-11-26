public class Student extends Person {
    private int rollNo;
    private double marks;

    public Student(int rollNo, String name, String email, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public int getRollNo() {
        return rollNo;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public void displayInfo() {
        System.out.println("Roll No : " + rollNo);
        System.out.println("Name    : " + name);
        System.out.println("Email   : " + email);
        System.out.println("Marks   : " + marks);
        System.out.println("-----------------------------");
    }

    public String toCsv() {
        return rollNo + "," + name + "," + email + "," + marks;
    }

    public static Student fromCsv(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            return null;
        }
        int roll = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String email = parts[2].trim();
        double marks = Double.parseDouble(parts[3].trim());
        return new Student(roll, name, email, marks);
    }
}
