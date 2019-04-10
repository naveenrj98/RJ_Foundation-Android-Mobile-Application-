package u.in.ac.bmsce.rjfoundation.models;

public class ContactUs {


    private String Cname;
    private String Cemail;
    private String Csubject;
    private String Cmessage;

    public  ContactUs()

    {

    }

        @Override
    public String toString() {
        return "ContactUs{" +
                "Cname='" + Cname + '\'' +
                ", Cemail='" + Cemail + '\'' +
                ", Csubject='" + Csubject + '\'' +
                ", Cmessage='" + Cmessage + '\'' +
                '}';
    }

    public ContactUs(String cname, String cemail, String csubject, String cmessage) {
        Cname = cname;
        Cemail = cemail;
        Csubject = csubject;
        Cmessage = cmessage;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCemail() {
        return Cemail;
    }

    public void setCemail(String cemail) {
        Cemail = cemail;
    }

    public String getCsubject() {
        return Csubject;
    }

    public void setCsubject(String csubject) {
        Csubject = csubject;
    }

    public String getCmessage() {
        return Cmessage;
    }

    public void setCmessage(String cmessage) {
        Cmessage = cmessage;
    }
}
