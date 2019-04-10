package u.in.ac.bmsce.rjfoundation.models;

public class TeachingSettings {

    private String tusername;
    private String age;



    private String days;
    private String hours;
    private String qualification;
    private String profession;

    public TeachingSettings()
    {

    }

    public String getTusername() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername = tusername;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }



    public TeachingSettings(String tusername, String age, String days, String hours, String qualification, String profession) {
        this.tusername = tusername;
        this.age = age;
        this.days = days;
        this.hours = hours;
        this.qualification = qualification;
        this.profession = profession;
    }
    @Override
    public String toString() {
        return "TeachingSettings{" +
                "tusername='" + tusername + '\'' +
                ", age=" + age +
                ", days='" + days + '\'' +
                ", hours='" + hours + '\'' +
                ", qualification='" + qualification + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }

}
