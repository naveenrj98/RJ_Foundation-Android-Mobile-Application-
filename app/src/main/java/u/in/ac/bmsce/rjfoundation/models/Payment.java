package u.in.ac.bmsce.rjfoundation.models;

public class Payment {

    private String email;
    private long phone;
    private String amount;
    private String purpose;
    private String buyerName;
    private String orderId;
    private String tokenId;
    private boolean status;

    public Payment() {
    }

    public Payment(String email, long phone, String amount, String purpose, String buyerName) {
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.purpose = purpose;
        this.buyerName = buyerName;
    }

    public Payment(String email, long phone, String amount, String purpose, String buyerName, String orderId, String tokenId, boolean status) {
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.purpose = purpose;
        this.buyerName = buyerName;
        this.orderId = orderId;
        this.tokenId = tokenId;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", amount='" + amount + '\'' +
                ", purpose='" + purpose + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", orderId='" + orderId + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", status=" + status +
                '}';
    }
}
